/**
 * 
 */
package name.chenyuelin.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import name.chenyuelin.entity.test.Person;

import org.springframework.stereotype.Repository;

/**
 * @author P1
 * @version 1.0 Jan 1, 2013
 */
@Repository
public class JpaPersonDao {
	@PersistenceContext
	private EntityManager entityManager;
	
	public Person findPersonById(byte id){
		return entityManager.find(Person.class, id);
	}
}
