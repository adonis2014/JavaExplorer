package test.name.chenyuelin;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.name.chenyuelin.controller.PersonControllerTestCase;
import test.name.chenyuelin.service.TServiceTestCase;
import test.name.chenyuelin.transformer.PersonTransformerTestCase;

@RunWith(Suite.class)
//@formatter:off
@SuiteClasses({ 
	PersonControllerTestCase.class, 
	TServiceTestCase.class, 
	PersonTransformerTestCase.class })
//@formatter:on
public class AllTests {
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("TServiceTestCase");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("TServiceTestCase");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		System.out.println("TServiceTestCase");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		System.out.println("TServiceTestCase");
	}
}
