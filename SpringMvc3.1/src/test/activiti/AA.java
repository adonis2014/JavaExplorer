package test.activiti;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

public class AA {

	@Rule
	public ActivitiRule activitiRule = new ActivitiRule(ActivitiTestContentConstant.ACTIVITI_RULE_CONFIGURATION_PATH);
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	@Deployment(resources={"file://E:/GitHome/JavaExplorer/SpringMvc3.1/WebContent/WEB-INF/activities/ActivitySample.bpmn"})
	public void test() {
		RuntimeService runtimeService = activitiRule.getRuntimeService();
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("activitySample");
		System.out.println(processInstance);
	}

}
