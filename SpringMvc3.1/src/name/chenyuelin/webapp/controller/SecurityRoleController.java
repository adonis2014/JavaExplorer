package name.chenyuelin.webapp.controller;

import java.util.Map;

import name.chenyuelin.service.SsRoleService;
import name.chenyuelin.webapp.command.SsRoleCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindException;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("security/role")
public class SecurityRoleController {
	@Autowired
	private SsRoleService service;
	
	private Map<Class<?>, Validator> validatorMap;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder,NativeWebRequest nativeWebRequest) {
		Object target = binder.getTarget();
		if (target != null) {
			Class<?> targetClass=target.getClass();
			Validator validator = validatorMap.get(targetClass);
			if(validator!=null){
				binder.setValidator(validator);
			}
		}
	}
	
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
	public ModelAndView create(@ModelAttribute("ssRole") @Validated SsRoleCommand ssRoleCommand, RedirectAttributes redirectAttributes){
		service.createSsRole(ssRoleCommand);
		redirectAttributes.addFlashAttribute("newRoleName", ssRoleCommand.getName());
		return new ModelAndView("redirect:index.htm");
	}
	
	@ExceptionHandler(BindException.class)
	public ModelAndView handleIOException(BindException ex,NativeWebRequest nativeWebRequest) {
		String referer=nativeWebRequest.getHeader("referer");
		System.out.println(nativeWebRequest.getContextPath());
		int last=referer.lastIndexOf(".");
		String viewName;
		if(last<0){
			viewName="resourceType/"+referer.substring(referer.lastIndexOf("/")+1);
		}else{
			viewName="resourceType/"+referer.substring(referer.lastIndexOf("/")+1,last);
		}
		return new ModelAndView(viewName,ex.getModel());
	}

	public void setValidatorMap(Map<Class<?>, Validator> validatorMap) {
		this.validatorMap = validatorMap;
	}
}
