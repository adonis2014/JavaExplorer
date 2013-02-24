/**
 * 
 */
package name.chenyuelin.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import name.chenyuelin.entity.test.Person;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

/**
 * @author P1
 * @version 1.0 Jan 1, 2013
 */
@Repository
public class JpaPersonDao {
    public static final Log LOG=LogFactory.getLog(JpaPersonDao.class);
    
	@PersistenceContext
	private EntityManager entityManager;
	
	public Person findPersonById(byte id){
		return entityManager.find(Person.class, id);
	}
	
	public List<Person> getAllPersons(){
		TypedQuery<Person> query= entityManager.createNamedQuery("getAllPersons", Person.class);
		return query.getResultList();
	}
	
	public Person updatePerson(Person person){
		Person newperson=entityManager.merge(person);
		return newperson;
	}
	
	public boolean deletePerson(byte id){
		Person person=entityManager.find(Person.class, id);
		if(person!=null){
			entityManager.remove(person);
			return true;
		}else{
			return false;
		}
	}
	
}
