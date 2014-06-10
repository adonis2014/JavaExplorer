package name.chenyuelin.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import name.chenyuelin.entity.test.NamedQueryContent;
import name.chenyuelin.entity.test.SsRole;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

@Repository
public class SsRoleDao {
	public static final Log LOG = LogFactory.getLog(SsRoleDao.class);
	
	@PersistenceContext
    private EntityManager entityManager;
	
	public List<SsRole> getAllSsRole(){
		return entityManager.createNamedQuery(NamedQueryContent.SSROLE_GET_ALL_SSROLE,SsRole.class).getResultList();
	}
	
	public void insertSsRole(SsRole ssRole){
		entityManager.persist(ssRole);
	}
	
	public void deleteSsRole(byte id){
		SsRole ssRoleEntity=entityManager.find(SsRole.class, id);
		entityManager.remove(ssRoleEntity);
	}
}
