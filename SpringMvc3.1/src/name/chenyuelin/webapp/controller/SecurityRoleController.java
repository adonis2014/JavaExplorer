package name.chenyuelin.webapp.controller;

import name.chenyuelin.service.SsRoleService;
import name.chenyuelin.webapp.command.SsRoleCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("security/role")
public class SecurityRoleController {
	@Autowired
	private SsRoleService service;
	
	@RequestMapping(value="index.htm", method=RequestMethod.GET)
	public ModelAndView show(){
		return new ModelAndView("ssrole/index","ss_roles",service.getAllSsRole());
	}
	
	@RequestMapping(value="create.htm", method=RequestMethod.GET)
	public ModelAndView create(){
		SsRoleCommand command=new SsRoleCommand();
		command.setEnabled(true);
		return new ModelAndView("ssrole/create","ssRole",command);
	}
	
	@Transactional
	@RequestMapping(value="create", method=RequestMethod.POST)
	public ModelAndView create(@ModelAttribute("ssRole") SsRoleCommand ssRoleCommand, RedirectAttributes redirectAttributes){
		service.createSsRole(ssRoleCommand);
		redirectAttributes.addFlashAttribute("newRoleName", ssRoleCommand.getName());
		return new ModelAndView("redirect:index.htm");
	}
}
