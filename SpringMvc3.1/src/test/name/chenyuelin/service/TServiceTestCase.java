/**
 * 
 */
package test.name.chenyuelin.service;


import name.chenyuelin.service.TService;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author U1
 * @version 1.0 2013-2-24
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:WebContent/WEB-INF/configuration/appConfig/applicationContext.xml",
				   "file:WebContent/WEB-INF/configuration/servletConfig/servletCoreContext.xml",
				   "file:WebContent/WEB-INF/configuration/servletConfig/dispatcherStaticServletContext.xml",
				   "file:WebContent/WEB-INF/configuration/servletConfig/validatorContext.xml"})
@ActiveProfiles(profiles={"dev","test"})
public class TServiceTestCase {
	
	@Autowired
	private TService tService;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
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
	public void test(){
		System.out.println(tService.listT5byT2Name("jpa%"));
	}

}
