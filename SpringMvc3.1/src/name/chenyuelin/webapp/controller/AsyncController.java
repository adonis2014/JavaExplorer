package name.chenyuelin.webapp.controller;

import name.chenyuelin.service.AsyncService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("async")
public class AsyncController {
	@Autowired
	private AsyncService service;
	
	@RequestMapping(value = "randomnum", method = RequestMethod.GET)
	@ResponseBody
	public Integer reloadAuthority() throws Exception {
		service.printRandomNum();
		System.out.println("At the return setp.");
		return service.getRandomNum().get();
	}
}
