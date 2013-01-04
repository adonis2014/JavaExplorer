/**
 * 
 */
package name.chenyuelin.controller;

import name.chenyuelin.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author P1
 * @version 1.0 Jan 1, 2013
 */
@Controller
@RequestMapping("/person")
public class PersonController {
	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public void getPerson(@PathVariable("id")int id){
		System.out.println(userService.findPerson(id));
	}
}
