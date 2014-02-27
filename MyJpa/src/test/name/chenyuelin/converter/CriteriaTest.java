package test.name.chenyuelin.converter;

import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import name.chenyuelin.entity.test.Agent;
import name.chenyuelin.entity.test.Agent_;
import name.chenyuelin.entity.test.Area;
import name.chenyuelin.entity.test.Customer;
import name.chenyuelin.entity.test.CustomerAddress;
import name.chenyuelin.entity.test.CustomerAddress_;
import name.chenyuelin.entity.test.Customer_;
import name.chenyuelin.entity.test.Person;
import name.chenyuelin.entity.test.Person_;
import name.chenyuelin.enums.Sex;

import org.eclipse.persistence.config.QueryHints;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class CriteriaTest {
	private static final Random RANDOM = new Random();

	private static EntityManagerFactory ENTITY_MANAGER_FACTORY;
	private static EntityManager ENTITY_MANAGER;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("testDB");
		ENTITY_MANAGER = ENTITY_MANAGER_FACTORY.createEntityManager();
		Query query = ENTITY_MANAGER.createNamedQuery("digestPassword");
		byte[] data = new byte[8];
		RANDOM.nextBytes(data);
		query.setParameter(1, data);
		System.out.println(query.getSingleResult());
		// insertCustomerAddress();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		ENTITY_MANAGER.close();
		ENTITY_MANAGER_FACTORY.close();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testSelectCriteriaQuery() {
		CriteriaBuilder cb = ENTITY_MANAGER.getCriteriaBuilder();
		CriteriaQuery cq = cb.createQuery();
		Root<Person> root = cq.from(Person.class);

		cq.select(root.get(Person_.name));

		Query query = ENTITY_MANAGER.createQuery(cq);
		List<?> result = query.getResultList();
		System.out.println(result);
	}

	@Test
	public void testMultiselectCriteriaQuery() {
		CriteriaBuilder cb = ENTITY_MANAGER.getCriteriaBuilder();
		CriteriaQuery<?> cq = cb.createQuery();
		Root<Person> root = cq.from(Person.class);

		cq.multiselect(root.get(Person_.id), root.get(Person_.name));

		Query query = ENTITY_MANAGER.createQuery(cq);
		List<?> result = query.getResultList();
		System.out.println(result);
	}

	@Test
	public void testFromtCriteriaQuery() {
		CriteriaBuilder cb = ENTITY_MANAGER.getCriteriaBuilder();
		CriteriaQuery<?> cq = cb.createQuery();
		Root<Customer> e = cq.from(Customer.class);
		Root<CustomerAddress> a = cq.from(CustomerAddress.class);
		cq.multiselect(e, a);
		cq.where(cb.equal(e, a.get("customer")));
		Query query = ENTITY_MANAGER.createQuery(cq);
		List<?> result = query.getResultList();
		System.out.println(result);
	}

	@Test
	public void testJoinCriteriaQuery() {
		CriteriaBuilder cb = ENTITY_MANAGER.getCriteriaBuilder();
		CriteriaQuery<CustomerAddress> cq = cb.createQuery(CustomerAddress.class);
		Root<CustomerAddress> customerAddress = cq.from(CustomerAddress.class);
		customerAddress.join(CustomerAddress_.customer);
		customerAddress.join(CustomerAddress_.area1, JoinType.LEFT);
		customerAddress.join(CustomerAddress_.area2, JoinType.LEFT);

		TypedQuery<CustomerAddress> query = ENTITY_MANAGER.createQuery(cq);
		query.setHint(QueryHints.FETCH, "c.customer");
		query.setHint(QueryHints.LEFT_FETCH, "c.area1");
		query.setHint(QueryHints.LEFT_FETCH, "c.area2");
		List<CustomerAddress> result = query.getResultList();
		System.out.println(result.get(0).getArea1());
	}

	@Test
	public void testConjunction() {
		CriteriaBuilder cb = ENTITY_MANAGER.getCriteriaBuilder();
		CriteriaQuery<Person> cq = cb.createQuery(Person.class);
		Root<Person> person = cq.from(Person.class);
		Predicate predicate1 = cb.equal(person.get(Person_.id), cb.parameter(Byte.class, "id"));
		Predicate predicate2 = cb.like(cb.lower(person.get(Person_.name)), cb.lower(cb.parameter(String.class, "name")));
		// Predicate predicate=cb.conjunction();
		// predicate=cb.and(predicate,predicate1,predicate2);
		cq.where(cb.and(predicate1, predicate2));
		TypedQuery<Person> query = ENTITY_MANAGER.createQuery(cq);
		query.setParameter("id", 12);
		query.setParameter("name", "%f");
		List<Person> result = query.getResultList();
		System.out.println(result);
	}

	@Test
	public void testTableInnerJoin() {
		CriteriaBuilder cb = ENTITY_MANAGER.getCriteriaBuilder();
		CriteriaQuery<?> cq = cb.createQuery();
		Root<Customer> customer = cq.from(Customer.class);
		Root<Person> person = cq.from(Person.class);
		cq.where(cb.equal(customer.get(Customer_.id), person.get(Person_.id)));
		cq.multiselect(customer, person);
		Query query = ENTITY_MANAGER.createQuery(cq);
		List<?> result = query.getResultList();
		System.out.println(result);

		String jpql = "select p from Person p left join Customer c on p.id=c.id";
		TypedQuery<Person> query2 = ENTITY_MANAGER.createQuery(jpql, Person.class);
		List<Person> result2 = query2.getResultList();
		System.out.println(result2);
	}

	@Test
	public void testUpdateTable() {
		String testName="test_update";
		byte entityId=2;
		CriteriaBuilder cb = ENTITY_MANAGER.getCriteriaBuilder();
		CriteriaUpdate<Customer> cu = cb.createCriteriaUpdate(Customer.class);
		Root<Customer> customer = cu.from(Customer.class);
		cu.set(Customer_.name, testName).set(Customer_.active, false);
		cu.where(cb.equal(customer.get(Customer_.id),cb.parameter(Byte.class,"id")));
		EntityTransaction et = ENTITY_MANAGER.getTransaction();
		et.begin();
		Query query=ENTITY_MANAGER.createQuery(cu);
		query.setParameter("id", entityId);
		query.executeUpdate();
		Customer customerEntity=ENTITY_MANAGER.find(Customer.class, entityId);
		et.commit();
		ENTITY_MANAGER.refresh(customerEntity);
		Assert.assertEquals(customerEntity.getName(), testName);
		Assert.assertFalse(customerEntity.getActive());
		
		String jpql="UPDATE Customer c SET c.name = '张三' WHERE c.id = :id";
		query=ENTITY_MANAGER.createQuery(jpql);
		query.setParameter("id", entityId);
		et = ENTITY_MANAGER.getTransaction();
		et.begin();
		query.executeUpdate();
		ENTITY_MANAGER.refresh(customerEntity);
		et.commit();
		Assert.assertEquals(customerEntity.getName(), "张三");
	}

	@Test
	public void testDeleteTable() {
		CriteriaBuilder cb = ENTITY_MANAGER.getCriteriaBuilder();
		CriteriaDelete<Agent> cu = cb.createCriteriaDelete(Agent.class);
		Root<Agent> agentRoot = cu.from(Agent.class);
		cu.where(cb.equal(agentRoot.get(Agent_.id), cb.parameter(Byte.class,"id")));
		Query query=ENTITY_MANAGER.createQuery(cu);
		query.setParameter("id", 15);
		
		String jpql="DELETE FROM Agent a WHERE a.id=:id";
		Query query2=ENTITY_MANAGER.createQuery(jpql);
		query2.setParameter("id", 14);
		
		EntityTransaction et = ENTITY_MANAGER.getTransaction();
		et.begin();
		query.executeUpdate();
		query2.executeUpdate();
		et.commit();
	}
	
	@Test
	public void testTupleQuery() {
		CriteriaBuilder cb = ENTITY_MANAGER.getCriteriaBuilder();
		CriteriaQuery<Tuple> cq = cb.createTupleQuery();
		Root<Customer> customerRoot = cq.from(Customer.class);
		cq.multiselect(customerRoot.get(Customer_.name).alias("n"), 
				customerRoot.get(Customer_.id).alias("i"),
				cb.function("password", String.class, customerRoot.get(Customer_.name)).alias("pwn"));
		TypedQuery<Tuple> typedQuery=ENTITY_MANAGER.createQuery(cq);
		List<Tuple> results=typedQuery.getResultList();
		for(Tuple t:results){
			System.out.println(t.get("i")+"\t"+t.get("n")+"\t"+t.get("pwn"));
		}
	}
	
	private static void insertCustomerAddress() {
		EntityTransaction et = ENTITY_MANAGER.getTransaction();
		et.begin();
		Query query = ENTITY_MANAGER.createNamedQuery("digestPassword");
		query.setParameter(1, "12345678");
		Customer customer = new Customer();
		customer.setName("王五");
		customer.setPassword((String) query.getSingleResult());
		customer.setSex(Sex.male);

		CustomerAddress customerAddress = new CustomerAddress();
		customerAddress.setCustomer(customer);
		customerAddress.setSubId((byte) 0);
		customerAddress.setArea1(ENTITY_MANAGER.getReference(Area.class, 1));
		customerAddress.setArea2(ENTITY_MANAGER.getReference(Area.class, 2));
		customerAddress.setStreet("test street");
		customerAddress.setActive(true);
		ENTITY_MANAGER.persist(customer);
		et.commit();
	}
}
