package integrationTest;

import javax.xml.transform.Source;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.ws.test.server.RequestCreators;
import org.springframework.ws.test.server.ResponseMatchers;
import org.springframework.xml.transform.StringSource;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration("WebContent")
@ContextHierarchy({
		@ContextConfiguration(locations = { "file:WebContent/WEB-INF/configuration/appConfig/applicationContext.xml",
				"file:WebContent/WEB-INF/configuration/appConfig/security.xml", "file:WebContent/WEB-INF/configuration/appConfig/scheduled.xml" }),
		@ContextConfiguration(locations = "file:WebContent/WEB-INF/configuration/ws/spring-ws.xml") })
@ActiveProfiles(profiles = { "dev", "test" })
public class SpringWSTest {
	@Autowired
	private WebApplicationContext applicationContext;

	private MockWebServiceClient mockWebServiceClient;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		mockWebServiceClient = MockWebServiceClient.createClient(applicationContext);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
//		Source requestPayload = new StringSource("<S:Envelope xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\"><S:Body><findPersonRequest xmlns=\"http://www.cyl.org/person/schemas\"><id>1</id></findPersonRequest></S:Body></S:Envelope>");
//		Source responsePayload = new StringSource("<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"><SOAP-ENV:Header/><SOAP-ENV:Body><ns2:findPersonResponse xmlns:ns2=\"http://www.cyl.org/person/schemas\"><ns2:id>1</ns2:id><ns2:name>陈岳麟</ns2:name><ns2:sex>male</ns2:sex><ns2:birthday>1983-03-11</ns2:birthday><ns2:height>1.78</ns2:height><ns2:breakfastTime>08:12:12</ns2:breakfastTime><ns2:createTime>2012-01-27T19:19:15.000-08:00</ns2:createTime><ns2:salary>9500</ns2:salary><ns2:note>自定义数据放的是一张帅哥jpg图片。</ns2:note><ns2:languages>chinese</ns2:languages><ns2:languages>english</ns2:languages><ns2:version>1</ns2:version><ns2:active>true</ns2:active></ns2:findPersonResponse></SOAP-ENV:Body></SOAP-ENV:Envelope>");
		
		Source requestPayload = new StringSource("<findPersonRequest xmlns=\"http://www.cyl.org/person/schemas\"><id>1</id></findPersonRequest>");
		Source responsePayload = new StringSource("<ns2:findPersonResponse xmlns:ns2=\"http://www.cyl.org/person/schemas\"><ns2:id>1</ns2:id><ns2:name>陈岳麟</ns2:name><ns2:sex>male</ns2:sex><ns2:birthday>1983-03-11</ns2:birthday><ns2:height>1.78</ns2:height><ns2:breakfastTime>08:12:12</ns2:breakfastTime><ns2:createTime>2012-01-27T19:19:15.000-08:00</ns2:createTime><ns2:salary>9500</ns2:salary><ns2:note>自定义数据放的是一张帅哥jpg图片。</ns2:note><ns2:languages>chinese</ns2:languages><ns2:languages>english</ns2:languages><ns2:version>1</ns2:version><ns2:active>true</ns2:active></ns2:findPersonResponse>");
		mockWebServiceClient.sendRequest(RequestCreators.withPayload(requestPayload)).andExpect(ResponseMatchers.payload(responsePayload));
	}

}
