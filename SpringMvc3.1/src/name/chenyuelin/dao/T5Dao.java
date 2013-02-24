/**
 * 
 */
package name.chenyuelin.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import name.chenyuelin.entity.test.T2_;
import name.chenyuelin.entity.test.T5;
import name.chenyuelin.entity.test.T5_;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

/**
 * @author P1
 * @version 1.0 Feb 19, 2013
 */
@Repository
public class T5Dao {
	public static final Log LOG=LogFactory.getLog(T5Dao.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<T5> listT5byT2Name(String name){
		CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
		CriteriaQuery<T5> criteriaQuery=criteriaBuilder.createQuery(T5.class);
		Root<T5> root=criteriaQuery.from(T5.class);
		root.fetch(T5_.t2);
		ParameterExpression<String> par=criteriaBuilder.parameter(String.class);
		Path<String> path=root.get(T5_.t2).get(T2_.name);
		Predicate predicate=criteriaBuilder.like(path, par);
		criteriaQuery.select(root).where(predicate).distinct(true);
		
		TypedQuery<T5> typedQuery=entityManager.createQuery(criteriaQuery);
		typedQuery.setParameter(par, name);
		return typedQuery.getResultList();
	}
}
