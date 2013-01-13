/**
 * 
 */
package name.chenyuelin.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import name.chenyuelin.command.Person;
import name.chenyuelin.service.UserService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author P1
 * @version 1.0 Jan 1, 2013
 */
@Controller
@RequestMapping("/person")
public class PersonController {
	private static final Log LOG=LogFactory.getLog(PersonController.class);
	
	private UserService userService;

	@ModelAttribute
	public Collection<String> getAa(){
	    return new ArrayList<String>();
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public void getPerson(@PathVariable("id")int id){
		System.out.println(userService.findPerson(id));
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Person createPerson(@RequestBody Person person){
		if(LOG.isTraceEnabled()){
			StringBuilder logInfo=new StringBuilder(30);
			logInfo.append("createPerson start. New person information:\n");
			logInfo.append("name:").append(person.getName()).append("\t");
			logInfo.append("sex:").append(person.getSex()).append("\t");
			logInfo.append("birthday:").append(person.getBirthday()).append("\t");
			logInfo.append("height:").append(person.getHeight()).append("\t");
			logInfo.append("breakfastTime:").append(person.getBreakfastTime()).append("\t");
			logInfo.append("salary:").append(person.getSalary()).append("\t");
			logInfo.append("note:").append(person.getNote());
			LOG.trace(logInfo);
		}
		//System.out.println(person.getCreateTime());
		ObjectMapper om=new ObjectMapper();
		try {
            om.writeValue(System.out, person);
        } catch (JsonGenerationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		return person;
	}
	
	@RequestMapping(value="formUrlencoded",method=RequestMethod.POST,consumes="application/x-www-form-urlencoded")
    public ModelAndView createPerson(@RequestBody MultiValueMap<String, Object> aa){
	    ModelAndView mav=new ModelAndView("aa",aa);
	    return mav;
    }
	
	@RequestMapping(value="formUrlencoded2",method=RequestMethod.POST)
	public ModelAndView createPerson2(Person p,ModelMap map){
	    Person person=(Person)map.get("person");
	    person.setName("aaaaaaa");
	    ModelAndView mav=new ModelAndView();
	    return mav;
    }
	
}
