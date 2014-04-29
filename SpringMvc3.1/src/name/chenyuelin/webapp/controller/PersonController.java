/**
 * 
 */
package name.chenyuelin.webapp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import name.chenyuelin.entity.test.Person;
import name.chenyuelin.service.UserService;
import name.chenyuelin.webapp.command.PersonCommand;
import name.chenyuelin.webapp.command.PersonCommandListWrap;
import name.chenyuelin.webapp.command.PersonUploadInformation;
import name.chenyuelin.webapp.dto.ActionStatus;
import name.chenyuelin.webapp.dto.ActionStatusListWarp;
import name.chenyuelin.webapp.dto.PersonDto;
import name.chenyuelin.webapp.dto.PersonDtoListWrap;
import name.chenyuelin.webapp.transformer.PersonTransformer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author P1
 * @version 1.0 Jan 1, 2013
 */
@Controller
@RequestMapping("person")
public class PersonController {
    public static final Log LOG=LogFactory.getLog(PersonController.class);
	
    @Autowired
	private WebApplicationContext wac;
    
	private UserService userService;

	private Map<Class<?>, Validator> validatorMap;
	
	@InitBinder
    protected void initBinder(WebDataBinder binder,WebRequest webRequest,HttpServletRequest request){
	    Object target=binder.getTarget();
	    if(target!=null){
	    	Validator validator=validatorMap.get(target.getClass());
	    	if(validator!=null){
	    		binder.setValidator(validator);
	    	}
	    }
    }
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Transactional
	@RequestMapping(value="{id}",method=RequestMethod.GET)
	public ModelAndView getPerson(@PathVariable("id")int id){
	    Person p=userService.findPerson(id);
	    PersonDto person=PersonTransformer.toPersonDto(p);
	    ModelAndView mav=new ModelAndView("root","person",person);
		return mav;
	}
	
	@Transactional
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	@PostFilter("person.filterObject.id==principal.id or filterObject.id>10")
	public PersonDtoListWrap getAllPersons()throws Exception{
		Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
		System.out.println(authentication.getPrincipal());
		List<Person> persons=userService.getAllPersons();
		PersonDtoListWrap warp=new PersonDtoListWrap();
		warp.setPerson(PersonTransformer.toPersonDtoList(persons));
		return warp;
	}
	
	@Transactional
	@RequestMapping(value="{id}",method=RequestMethod.PUT)
	@ResponseBody
	public ActionStatus updatePerson(@PathVariable("id")byte id,@Validated @RequestBody PersonCommand person){
		ActionStatus actionStatus=new ActionStatus();
		boolean result=userService.updatePerson(id, person);
		actionStatus.setProcessSuccessfully(result);
		return actionStatus;
	}
	
	@Transactional
	@RequestMapping(method=RequestMethod.PUT)
	@ResponseBody
	public ActionStatusListWarp batchUpdatePerson(@Validated @RequestBody PersonCommandListWrap persons){
		ActionStatusListWarp listWarp=new ActionStatusListWarp();
		List<ActionStatus> actionStatusList=new ArrayList<ActionStatus>();
		listWarp.setActionStatus(actionStatusList);
		for(PersonCommand command:persons.getPersonCommands()){
			ActionStatus actionStatus=new ActionStatus();
			actionStatus.setProcessSuccessfully(userService.updatePerson(command.getId().byteValue(), command));
			actionStatusList.add(actionStatus);
		}
		return listWarp;
	}
	
	@Transactional
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
    public ActionStatus addPerson(@Validated @RequestBody PersonCommand person){
		ActionStatus actionStatus=new ActionStatus();
		byte id=userService.addPerson(person);
		actionStatus.setProcessSuccessfully(true);
		Map<String, Object> returnData=new HashMap<String, Object>();
		returnData.put("id", id);
		actionStatus.setReturnData(returnData);
	    return actionStatus;
    }
	
	@Transactional
	@RequestMapping(value="{id}",method=RequestMethod.DELETE)
	@ResponseBody
	@PreAuthorize("#id==principal.id")
	public ActionStatus deletePereson(@PathVariable("id")byte id){
		ActionStatus actionStatus=new ActionStatus();
		actionStatus.setProcessSuccessfully(userService.deletePerson(id));
	    return actionStatus;
    }

	@RequestMapping(value="personUpload",method=RequestMethod.GET)
	public String personInfoUpload(@ModelAttribute PersonUploadInformation personUploadInformation){
		return "personForm";
	}
	
	@Transactional
	@RequestMapping(value="personUpload",method=RequestMethod.POST)
	public String personInfoUpload(@Validated @ModelAttribute PersonUploadInformation personUploadInformation, RedirectAttributes redirectAttributes)throws IOException{
		for(MultipartFile multipartFilea:personUploadInformation.getUploadFile()){
			System.out.println("==================================");
			System.out.println(multipartFilea.getContentType());
			System.out.println(multipartFilea.getName());
			System.out.println(multipartFilea.getOriginalFilename());
			System.out.println(multipartFilea.getSize());
			byte[] data=multipartFilea.getBytes();
			System.out.println(DatatypeConverter.printBase64Binary(data).length());
			System.out.println(DigestUtils.md5DigestAsHex(data));
		}
		
		redirectAttributes.addAttribute("person","³ÂÔÀ÷ë");
		redirectAttributes.addFlashAttribute("message", "ÏûÏ¢");
		return "redirect:/formCommit.htm";
	}
	
	@RequestMapping(value="cacheable/{id}",method=RequestMethod.GET)
	@ResponseBody
	@Cacheable("cacheable")
	public Map<String, Object> cacheable(@PathVariable("id")String id){
		System.out.println("cacheable");
		Map<String, Object> result=new HashMap<String, Object>();
		result.put("id", id);
		result.put("randomNum", Math.random());
		return result;
	}
	
	@RequestMapping(value="cacheable/{id}",method=RequestMethod.POST)
	@ResponseBody
	@CacheEvict("cacheable")
	public Map<String, Object> cacheDelete(@PathVariable("id")String id){
		System.out.println("cacheDelete");
		Map<String, Object> result=new HashMap<String, Object>();
		result.put("id", id);
		result.put("randomNum", Math.random());
		return result;
	}
	
	@RequestMapping(value="cacheable",method=RequestMethod.POST)
	@ResponseBody
	@CacheEvict(value="cacheable",allEntries=true)
	public Map<String, Object> cacheDeleteAll(){
		System.out.println("cacheDeleteAll");
		Map<String, Object> result=new HashMap<String, Object>();
		result.put("randomNum", Math.random());
		return result;
	}
	
	public void setValidatorMap(Map<Class<?>, Validator> validatorMap) {
		this.validatorMap = validatorMap;
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
		return "°ó´íÁË¡£";
	}*/
	
}
