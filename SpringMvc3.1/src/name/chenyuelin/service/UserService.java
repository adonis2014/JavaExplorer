/**
 * 
 */
package name.chenyuelin.service;

import name.chenyuelin.dao.JpaPersonDao;
import name.chenyuelin.entity.test.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author P1
 * @version 1.0 Jan 1, 2013
 */
@Service
public class UserService {
	private JpaPersonDao jpaPersonDao;

	@Autowired
	public void setJpaPersonDao(JpaPersonDao jpaPersonDao) {
		this.jpaPersonDao = jpaPersonDao;
	}
	
	public Person findPerson(int id){
		return jpaPersonDao.findPersonById((byte)id);
	}
}
