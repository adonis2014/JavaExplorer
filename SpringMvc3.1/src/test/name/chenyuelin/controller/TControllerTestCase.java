/**
 * 
 */
package test.name.chenyuelin.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import junit.framework.Assert;
import name.chenyuelin.controller.TController;
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
import org.springframework.context.MessageSource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author U1
 * @version 1.0 2013-2-25
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:WebContent/WEB-INF/configuration/appConfig/applicationContext.xml",
				   "file:WebContent/WEB-INF/configuration/servletConfig/servletCoreContext.xml",
				   "file:WebContent/WEB-INF/configuration/servletConfig/dispatcherStaticServletContext.xml",
				   "file:WebContent/WEB-INF/configuration/servletConfig/validatorContext.xml"})
@ActiveProfiles(profiles={"dev","test"})
public class TControllerTestCase {
	@Autowired
	private TController tController;

	@Autowired
	private MessageSource messageSource;
	
	@Mock
	private TService tService;
	
	private static TController TCONTROLLER;
	
	private static TService TSERVICE;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	  System.out.println("TControllerTestCase");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		TCONTROLLER.settService(TSERVICE);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		if(TSERVICE==null){
			TSERVICE=tService;
		}
		if(TCONTROLLER==null){
			TCONTROLLER=tController;
		}
		
		MockitoAnnotations.initMocks(this);
		
		tController.settService(tService);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		Mockito.reset(tService);
	}

	@Test
	public void test(){
		System.out.println(messageSource.getMessage("error.requirement.name", null, Locale.CHINESE));
		System.out.println(messageSource.getMessage("error.requirement.name", null, Locale.ENGLISH));
		//mock
		List<T5> list=new ArrayList<T5>();
		list.add(new T5());
		
		Mockito.when(tService.listT5byT2Name(Mockito.anyString())).thenReturn(list);
		
		Assert.assertEquals(1, tService.listT5byT2Name("jpa%").size());
		
		Mockito.verify(tService).listT5byT2Name("jpa%");
	}
}
