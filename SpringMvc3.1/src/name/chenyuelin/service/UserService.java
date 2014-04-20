/**
 * 
 */
package name.chenyuelin.service;

import java.util.List;

import name.chenyuelin.dao.JpaCustomerAddressDao;
import name.chenyuelin.dao.JpaCustomerDao;
import name.chenyuelin.dao.JpaPersonDao;
import name.chenyuelin.entity.test.Customer;
import name.chenyuelin.entity.test.CustomerAddress;
import name.chenyuelin.entity.test.CustomerAddressPK;
import name.chenyuelin.entity.test.Person;
import name.chenyuelin.webapp.command.PersonCommand;
import name.chenyuelin.webapp.transformer.PersonTransformer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author P1
 * @version 1.0 Jan 1, 2013
 */
@Service
public class UserService {
    public static final Log LOG=LogFactory.getLog(UserService.class);
    
	private JpaPersonDao jpaPersonDao;
	private JpaCustomerDao jpaCustomerDao;
	private JpaCustomerAddressDao jpaCustomerAddressDao;

	@Autowired
	public void setJpaPersonDao(JpaPersonDao jpaPersonDao) {
		this.jpaPersonDao = jpaPersonDao;
	}
	
	@Autowired
    public void setJpaCustomerDao(JpaCustomerDao jpaCustomerDao) {
        this.jpaCustomerDao = jpaCustomerDao;
    }
	
	@Autowired
    public void setJpaCustomerAddressDao(JpaCustomerAddressDao jpaCustomerAddressDao) {
        this.jpaCustomerAddressDao = jpaCustomerAddressDao;
    }
	
	public Person findPerson(int id){
		return jpaPersonDao.findPersonById((byte)id);
	}
	
	public List<Person> getAllPersons(){
		return jpaPersonDao.getAllPersons();
	}
	
	public CustomerAddress findCustomerAddress(int customerId,int subId){
        return jpaCustomerAddressDao.findCustomerAddressById(new CustomerAddressPK((byte)customerId, (byte)subId));
    }
	
	public Customer findCustomer(int id){
        return jpaCustomerDao.findCustomerById((byte)id);
    }
	
	public boolean updatePerson(byte personId,PersonCommand personCommand){
		Person person=jpaPersonDao.findPersonById(personId);
		if(person!=null){
			PersonTransformer.transformerCommandtoEntity(personCommand, person);
			person=jpaPersonDao.updatePerson(person);
			return true;
		}
		return false;
		
	}
	
	public byte addPerson(PersonCommand personCommand){
		Person person=new Person();
		PersonTransformer.transformerCommandtoEntity(personCommand, person);
		jpaPersonDao.addPerson(person);
		return person.getId();
	}
	
	public boolean deletePerson(byte personId){
		return jpaPersonDao.deletePerson(personId);
	}
}
