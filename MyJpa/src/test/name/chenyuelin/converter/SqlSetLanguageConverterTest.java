/**
 * 
 */
package test.name.chenyuelin.converter;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import name.chenyuelin.entity.test.Customer;
import name.chenyuelin.entity.test.Person;
import name.chenyuelin.enums.Language;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author U1
 * @version 1.0 2014-2-3
 */
public class SqlSetLanguageConverterTest {
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

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPerson() {
		Person person = ENTITY_MANAGER.find(Person.class, (byte) 1);
		Assert.assertNotNull(person);
		Collection<Language> languages = person.getLanguages();
		Assert.assertEquals(2, languages.size());
		Assert.assertTrue(languages.contains(Language.english));
		Assert.assertTrue(languages.contains(Language.chinese));

		languages = person.getLanguages2();
		Assert.assertEquals(2, languages.size());
		Assert.assertTrue(languages.contains(Language.english));
		Assert.assertTrue(languages.contains(Language.chinese));
	}

	@Test
	public void testCustomer() {
		TypedQuery<Customer> query=ENTITY_MANAGER.createNamedQuery("getCustomer", Customer.class);
		query.setParameter("id",1);
		query.setParameter("password",123456);
//		query.setParameter(1,"*6A7A490FB9DC8C33C2B025A91737077A7E9CC5E5");
		Customer customer=query.getSingleResult();
		Assert.assertNotNull(customer);
	}
}
