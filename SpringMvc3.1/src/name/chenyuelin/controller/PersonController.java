/**
 * 
 */
package name.chenyuelin.controller;

import name.chenyuelin.command.Person;
import name.chenyuelin.service.UserService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author P1
 * @version 1.0 Jan 1, 2013
 */
@Controller
@RequestMapping("/person")
public class PersonController {
	private static final Log LOG=LogFactory.getLog(PersonController.class);
	
	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public void getPerson(@PathVariable("id")int id){
		System.out.println(userService.findPerson(id));
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public void createPerson(@RequestBody()Person person){
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
		System.out.println(person);
	}
}
