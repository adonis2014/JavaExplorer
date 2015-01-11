/**
 * 
 */
package integrationTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
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
@WebAppConfiguration("WebContent")
@ContextHierarchy({
		@ContextConfiguration(locations={"file:WebContent/WEB-INF/configuration/appConfig/applicationContext.xml",
				"file:WebContent/WEB-INF/configuration/appConfig/activiti.xml",
				"file:WebContent/WEB-INF/configuration/appConfig/security.xml",
				"file:WebContent/WEB-INF/configuration/appConfig/scheduled.xml",
		}),
		@ContextConfiguration(locations={ "file:WebContent/WEB-INF/configuration/servletConfig/servletCoreContext.xml",
				"file:WebContent/WEB-INF/configuration/servletConfig/dispatcherStaticServletContext.xml",
				"file:WebContent/WEB-INF/configuration/servletConfig/validatorContext.xml" }) })
@ActiveProfiles(profiles = {"test"})
public class PersonTest {
	
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	private MockHttpSession session;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Collection<GrantedAuthority> grantedAuthoritys=new ArrayList<>();
		User user=new User("user3", "*23AE809DDACAF96AF0FD78ED04B6A265E05AA257", grantedAuthoritys);
		PreAuthenticatedAuthenticationToken USERNAME_PASSWORD_AUTHENTICATION_TOKEN=new PreAuthenticatedAuthenticationToken(user,"123");
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
		System.out.println(wac.getServletContext().getRealPath("index.html"));
		
		mockMvc = MockMvcBuilders.webAppContextSetup(wac)
				.addFilters((FilterChainProxy)wac.getBean("springSecurityFilterChain"))
				.alwaysDo(MockMvcResultHandlers.print()).build();
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/j_spring_security_check")
				.param("j_username", "user3")
				.param("j_password", "123")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		
		session=(MockHttpSession)mvcResult.getRequest().getSession();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/j_spring_security_logout").accept(MediaType.APPLICATION_JSON));
	}

	@Test
	public void test() throws Exception {
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/person/").session(session).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"));
		
		MvcResult result = resultActions.andReturn();
//		System.out.println(result.getAsyncResult());
		System.out.println(result.getHandler());
		System.out.println(result.getRequest().getContextPath());
		System.out.println(result.getResponse().getContentAsString());
	}

}
