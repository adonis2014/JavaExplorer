package name.cyl.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import name.cyl.model.entity.JpaTest;

import org.springframework.stereotype.Repository;

@Repository
public class JapTestDao {
	@PersistenceContext
	private EntityManager entityManager;
	
	public void persistJapTest(JpaTest jpaTest){
		entityManager.persist(jpaTest);
		entityManager.flush();
	}
}
