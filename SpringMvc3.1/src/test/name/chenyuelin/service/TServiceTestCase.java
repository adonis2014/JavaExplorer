/**
 * 
 */
package test.name.chenyuelin.service;


import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import name.chenyuelin.dao.T5Dao;
import name.chenyuelin.entity.test.T5;
import name.chenyuelin.service.TService;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
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
	@Mock
	private T5Dao mockT5Dao;
	
	@Autowired
	private TService tService;

	@Autowired
	private T5Dao t5Dao;
	
	private static TService TSERVICE;

	private static T5Dao T5DAO;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	  System.out.println("TServiceTestCase");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		TSERVICE.setT5Dao(T5DAO);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		if(TSERVICE==null){
			TSERVICE=tService;
		}
		if(T5DAO==null){
			T5DAO=t5Dao;
		}
		
		MockitoAnnotations.initMocks(this);
		
		tService.setT5Dao(mockT5Dao);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		Mockito.reset(mockT5Dao);
	}
	
	@Test
	public void test(){
		//mock
		List<T5> list=new ArrayList<T5>();
		list.add(new T5());
		
		Mockito.when(mockT5Dao.listT5byT2Name(Mockito.anyString())).thenReturn(list);
		
		Assert.assertEquals(1, tService.listT5byT2Name("jpa%").size());
		
		Mockito.verify(mockT5Dao).listT5byT2Name("jpa%");
	}

}
