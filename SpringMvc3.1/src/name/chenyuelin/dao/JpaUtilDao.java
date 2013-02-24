/**
 * 
 */
package name.chenyuelin.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

/**
 * @author P1
 * @version 1.0 Feb 15, 2013
 */
@Repository
public class JpaUtilDao {
	public static final Log LOG=LogFactory.getLog(JpaUtilDao.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Cacheable("name.chenyuelin.dao.JpaUtilDao.digestPassword")
	public String digestPassword(String password){
		return this.digestPassword((Object)password);
	}
	
	public String digestPassword(byte[] password){
		return this.digestPassword((Object)password);
	}
	
	private String digestPassword(Object password){
		Query query=entityManager.createNamedQuery("digestPassword");
		query.setParameter(1, password);
		return (String)query.getSingleResult();
	}
	
	@Cacheable("name.chenyuelin.dao.JpaUtilDao.digestOldPassword")
	public String digestOldPassword(String password){
		return digestOldPassword((Object)password);
	}
	
	public String digestOldPassword(byte[] password){
		return digestOldPassword((Object)password);
	}
	
	private String digestOldPassword(Object password){
		Query query=entityManager.createNamedQuery("digestOldPassword");
		query.setParameter(1, password);
		return (String)query.getSingleResult();
	}
	
}
