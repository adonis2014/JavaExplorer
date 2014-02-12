/**
 * 
 */
package test.name.chenyuelin.converter;

import static org.junit.Assert.fail;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import name.chenyuelin.entity.test.Person;

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
	public void test() {
		Person person = ENTITY_MANAGER.find(Person.class, (byte) 1);
		Assert.assertNotNull(person);
		System.out.println(person);
	}

}
