package name.chenyuelin.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import name.chenyuelin.entity.test.Customer;

import org.springframework.stereotype.Repository;

/**
* @ClassName: JpaCustomerDao
* @Description: TODO(descript the function of this class)
* @author peter.chen
* @date 2013-1-4 обнГ5:12:20
* 
*/
@Repository
public class JpaCustomerDao {
    @PersistenceContext
    private EntityManager entityManager;
    
    public Customer findCustomerById(byte id){
        return entityManager.find(Customer.class, id);
    }
}
