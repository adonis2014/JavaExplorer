package name.chenyuelin.webapp.controller;

import name.chenyuelin.service.SsRoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("security/role")
public class SecurityRoleController {
	@Autowired
	private SsRoleService service;
	
	@RequestMapping(value="index.htm", method=RequestMethod.GET)
	public ModelAndView show(){
		return new ModelAndView("ssrole/index","ss_roles",service.getAllSsRole());
	}
}
