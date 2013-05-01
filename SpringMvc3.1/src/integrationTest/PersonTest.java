/**
 * 
 */
package integrationTest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author U1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration("src")
@ContextHierarchy({
		@ContextConfiguration("file:WebContent/WEB-INF/configuration/appConfig/applicationContext.xml"),
		@ContextConfiguration({ "file:WebContent/WEB-INF/configuration/servletConfig/servletCoreContext.xml",
				"file:WebContent/WEB-INF/configuration/servletConfig/dispatcherStaticServletContext.xml",
				"file:WebContent/WEB-INF/configuration/servletConfig/validatorContext.xml" }) })
@ActiveProfiles(profiles = { "dev", "test" })
public class PersonTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

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
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		System.out.println(123);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws Exception {
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/person/").accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"));

		MvcResult result = resultActions.andReturn();
		System.out.println(result.getAsyncResult());
		System.out.println(result.getHandler());
		System.out.println(result.getRequest().getContextPath());
		System.out.println(result.getResponse().getContentAsString());
	}

}
