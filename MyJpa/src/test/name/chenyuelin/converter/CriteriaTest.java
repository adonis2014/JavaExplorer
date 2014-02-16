package test.name.chenyuelin.converter;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import name.chenyuelin.entity.test.Customer;
import name.chenyuelin.entity.test.CustomerAddress;
import name.chenyuelin.entity.test.Person;
import name.chenyuelin.entity.test.Person_;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class CriteriaTest {
	
	private static EntityManagerFactory ENTITY_MANAGER_FACTORY;
	private static EntityManager ENTITY_MANAGER;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("testDB");
		ENTITY_MANAGER = ENTITY_MANAGER_FACTORY.createEntityManager();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		ENTITY_MANAGER.close();
		ENTITY_MANAGER_FACTORY.close();
	}
	
	@Test
	public void testSelectCriteriaQuery() {
		CriteriaBuilder cb = ENTITY_MANAGER.getCriteriaBuilder();
		CriteriaQuery cq = cb.createQuery();
		Root<Person> root=cq.from(Person.class);
		
		cq.select(root.get(Person_.name));
		
		Query query = ENTITY_MANAGER.createQuery(cq);
		List<?> result=query.getResultList();
		System.out.println(result);
	}

	@Test
	public void testMultiselectCriteriaQuery() {
		CriteriaBuilder cb = ENTITY_MANAGER.getCriteriaBuilder();
		CriteriaQuery<?> cq = cb.createQuery();
		Root<Person> root=cq.from(Person.class);
		
		cq.multiselect(root.get(Person_.id),root.get(Person_.name));
		
		Query query = ENTITY_MANAGER.createQuery(cq);
		List<?> result=query.getResultList();
		System.out.println(result);
	}
	
	@Test
	public void testFromtCriteriaQuery() {
		CriteriaBuilder cb = ENTITY_MANAGER.getCriteriaBuilder();
		CriteriaQuery<?> cq = cb.createQuery();
		Root<Customer> e = cq.from(Customer.class);
		Root<CustomerAddress> a = cq.from(CustomerAddress.class);
		cq.multiselect(e, a);
		cq.where(cb.equal(e.get("id"), a.get("customer")));
		Query query = ENTITY_MANAGER.createQuery(cq);
		List<?> result = query.getResultList();
		System.out.println(result);
	}
	
	@Test
	public void test() {
		CriteriaBuilder cb = ENTITY_MANAGER.getCriteriaBuilder();
		CriteriaQuery<?> cq = cb.createQuery();
		Root<?> e = cq.from(CustomerAddress.class);
		Query query = ENTITY_MANAGER.createQuery(cq);
		List<?> result = query.getResultList();
		System.out.println(result);
	}
}
