package name.chenyuelin.webapp.controller;

import name.chenyuelin.service.AsyncService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.XmlWebApplicationContext;

@Controller
@RequestMapping("async")
public class AsyncController {
	@Autowired
	private AsyncService service;
	
	private BeanDefinitionRegistry reanDefinitionRegistry;
	
	private ApplicationContext ac;
	
	@RequestMapping(value = "randomnum", method = RequestMethod.GET)
	@ResponseBody
	public Integer reloadAuthority() throws Exception {
		service.printRandomNum();
		System.out.println("At the return setp.");
		return service.getRandomNum().get();
	}

//	@Autowired
	public void setReanDefinitionRegistry(BeanDefinitionRegistry reanDefinitionRegistry) {
		this.reanDefinitionRegistry = reanDefinitionRegistry;
	}

	@Autowired
	public void setAc(ApplicationContext ac) {
		System.out.println(ac instanceof BeanDefinitionRegistry);
		((XmlWebApplicationContext)ac).getBeanFactory();
		this.ac = ac;
	}
}
