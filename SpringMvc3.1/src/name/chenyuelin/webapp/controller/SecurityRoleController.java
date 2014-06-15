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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("security/role")
@SessionAttributes("ssRole")
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
	@RequestMapping(value="create.htm", method=RequestMethod.POST)
	public ModelAndView create(@ModelAttribute("ssRole") @Validated SsRoleCommand ssRoleCommand, SessionStatus sessionStatus){
		service.createSsRole(ssRoleCommand);
		ModelAndView mav=new ModelAndView("ssrole/index");
		mav.addObject("ss_roles",service.getAllSsRole());
//		mav.addObject("ssRole", ssRoleCommand);
		sessionStatus.setComplete();
		return mav;
	}
	
	@Transactional
	@RequestMapping(value="delete/{id}")
	public ModelAndView delete(@PathVariable("id")byte id, RedirectAttributes redirectAttributes){
		service.deleteSsRole(id);
		return new ModelAndView("redirect:/security/role/index.htm");
	}
	
	@ExceptionHandler(BindException.class)
	public ModelAndView handleBindException(BindException ex,NativeWebRequest nativeWebRequest) {
		String referer=nativeWebRequest.getHeader("referer");
		System.out.println(nativeWebRequest.getContextPath());
		int last=referer.lastIndexOf(".");
		String viewName="ssrole/"+referer.substring(referer.lastIndexOf("/")+1,last);
		return new ModelAndView(viewName,ex.getModel());
	}

	public void setValidatorMap(Map<Class<?>, Validator> validatorMap) {
		this.validatorMap = validatorMap;
	}
}
