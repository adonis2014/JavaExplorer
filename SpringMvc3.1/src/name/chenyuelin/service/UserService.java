/**
 * 
 */
package name.chenyuelin.service;

import name.chenyuelin.dao.JpaCustomerAddressDao;
import name.chenyuelin.dao.JpaCustomerDao;
import name.chenyuelin.dao.JpaPersonDao;
import name.chenyuelin.entity.test.Customer;
import name.chenyuelin.entity.test.CustomerAddress;
import name.chenyuelin.entity.test.CustomerAddressPK;
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
	
	public CustomerAddress findCustomerAddress(int customerId,int subId){
        return jpaCustomerAddressDao.findCustomerAddressById(new CustomerAddressPK((byte)customerId, (byte)subId));
    }
	
	public Customer findCustomer(int id){
        return jpaCustomerDao.findCustomerById((byte)id);
    }
}
