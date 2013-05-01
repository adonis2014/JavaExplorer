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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author U1
 * @version 1.0 2013-2-24
 */
@RunWith(MockitoJUnitRunner.class)
public class TServiceTestCase {
	@Mock
	private T5Dao mockT5Dao;

	@InjectMocks
	private TService tService = new TService();

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
		Mockito.reset(mockT5Dao);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		// mock
		List<T5> list = new ArrayList<T5>();
		list.add(new T5());

		Mockito.when(mockT5Dao.listT5byT2Name(Mockito.anyString())).thenReturn(list);

		Assert.assertEquals(1, tService.listT5byT2Name("jpa%").size());

		Mockito.verify(mockT5Dao).listT5byT2Name("jpa%");
	}

}
