/**
 * 
 */
package name.chenyuelin.controller;

import java.util.Iterator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import name.chenyuelin.command.Person;
import name.chenyuelin.service.UserService;

import org.apache.catalina.util.RequestUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.util.WebUtils;

/**
 * @author P1
 * @version 1.0 Jan 1, 2013
 */
@Controller
@RequestMapping("/person")
public class PersonController {
    public static final Log LOG=LogFactory.getLog(PersonController.class);
	
	private UserService userService;

	private Validator personValidator;
	
	@InitBinder
    protected void initBinder(WebDataBinder binder,WebRequest webRequest,HttpServletRequest request){
	    Object target=binder.getTarget();
	    if(target!=null){
	        if(personValidator.supports(target.getClass())){
	            binder.setValidator(personValidator);
	        }
	    }
    }
	
	/*@ModelAttribute
	public Collection<String> getAa(){
	    LOG.trace("Create ModelAttribute.");
	    return new ArrayList<String>();
	}*/
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ModelAndView getPerson(@PathVariable("id")int id,Person personPar){
	    name.chenyuelin.entity.test.Person p=userService.findPerson(id);
	    Person person=new Person();
	    person.setName(p.getName());
	    person.setSex(p.getSex());
	    ModelAndView mav=new ModelAndView("root","person",person);
		return mav;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Person createPerson(@Validated @RequestBody  Person person){
	    System.out.println("other request");
		return person;
	}
	
	@RequestMapping(value="formUrlencoded",method=RequestMethod.POST,consumes="application/x-www-form-urlencoded")
    public ModelAndView createPerson(@RequestBody MultiValueMap<String, Object> aa){
	    ModelAndView mav=new ModelAndView("aa",aa);
	    System.out.println("form request.");
	    return mav;
    }
	
	@RequestMapping(value="formUrlencoded2",method=RequestMethod.POST)
	public ModelAndView createPerson2(@Validated Person p, BindingResult result){
	    System.out.println("other request");
	    ModelAndView mav=new ModelAndView();
	    return mav;
    }

	@Resource(name="personValidator")
	public void setPersonValidator(Validator personValidator) {
		this.personValidator = personValidator;
	}
	
	/*@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public String bindException(MethodArgumentNotValidException e,HttpServletResponse response){
		BindingResult bindingResult =e.getBindingResult();
		List<ObjectError> errorList=bindingResult.getGlobalErrors();
		for(ObjectError objectError:errorList){
			System.out.println(objectError.getObjectName());
			System.out.println(objectError.getCode());
			System.out.println(objectError.getDefaultMessage());
		}
		List<FieldError> fieldErrorList=bindingResult.getFieldErrors();
		for(FieldError objectError:fieldErrorList){
			System.out.println(objectError.getObjectName());
			System.out.println(objectError.getField());
			System.out.println(objectError.getRejectedValue());
		}
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		return "∞Û¥Ì¡À°£";
	}*/
	
}
