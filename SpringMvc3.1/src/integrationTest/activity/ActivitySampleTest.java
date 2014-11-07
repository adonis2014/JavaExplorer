package integrationTest.activity;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration("WebContent")
@ContextHierarchy({
		@ContextConfiguration(locations={"file:WebContent/WEB-INF/configuration/appConfig/applicationContext.xml"
				,"file:WebContent/WEB-INF/configuration/appConfig/activiti.xml"
				,"file:WebContent/WEB-INF/configuration/appConfig/security.xml"
				,"file:WebContent/WEB-INF/configuration/appConfig/scheduled.xml"
		})/*,
		@ContextConfiguration(locations={ "file:WebContent/WEB-INF/configuration/servletConfig/servletCoreContext.xml",
				"file:WebContent/WEB-INF/configuration/servletConfig/dispatcherStaticServletContext.xml",
				"file:WebContent/WEB-INF/configuration/servletConfig/validatorContext.xml" })*/ })
@ActiveProfiles(profiles = { "test" })
@TransactionConfiguration(transactionManager="jpaTransactionManager",defaultRollback=false)
@Transactional
public class ActivitySampleTest {
	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	private IdentityService identityService;

	@Autowired
	private FormService formService;

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private HistoryService historyService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private ManagementService managementService;
	
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
	
	@Rollback(true)
	@Test
	public void test() throws Exception{
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("activitySample");
		
		Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
		
		System.out.println(taskService.getIdentityLinksForTask(task.getId()));
		
		taskService.complete(task.getId());
		
		HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery().finished().processInstanceId(processInstance.getId()).singleResult();

		Assert.assertNotNull(historicProcessInstance);
	}
}
