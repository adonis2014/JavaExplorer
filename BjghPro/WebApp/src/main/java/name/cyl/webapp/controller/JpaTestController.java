package name.cyl.webapp.controller;

import java.util.HashMap;
import java.util.Map;

import name.cyl.dao.JapTestDao;
import name.cyl.model.entity.JpaTest;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("japtest")
public class JpaTestController {
	@Autowired
	private JapTestDao japTestDao;
	
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

	@RequestMapping("{op}/{pid}")
	@Transactional
	@ResponseBody
	public int phase1(@PathVariable("op") int op,@PathVariable("pid")String pid) {
		switch (op) {
			case 1:
				ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey("sample").latestVersion().singleResult();
				Map<String, Object> param = new HashMap<String, Object>();
				JpaTest jpaTest=new JpaTest();
				japTestDao.persistJapTest(jpaTest);
				param.put("japTest", jpaTest);
				runtimeService.startProcessInstanceById(processDefinition.getId(), param);
			case 2:
				jpaTest=(JpaTest)taskService.getVariable(taskService.createTaskQuery().processInstanceId(pid).singleResult().getId(), "japTest");
				jpaTest.setName("abc");
				break;
			case 3:

				break;
		}
		return op;
	}
}
