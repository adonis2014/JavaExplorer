/**
 * 
 */
package name.chenyuelin.controller;

import java.io.UnsupportedEncodingException;

import name.chenyuelin.dao.JpaUtilDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author P1
 * @version 1.0 Feb 15, 2013
 */
@Controller
@RequestMapping("sqlUtil")
public class SqlUtilController {
	private JpaUtilDao jpaUtilDao;

	@Autowired
	public void setJpaUtilDao(JpaUtilDao jpaUtilDao) {
		this.jpaUtilDao = jpaUtilDao;
	}
	
	@Transactional
	@RequestMapping(value="digestPassword/{password}",method=RequestMethod.GET)
	@ResponseBody
	public String digestPassword(@PathVariable("password")String password,@RequestHeader("Accept-Encoding") String acceptEncoding)throws UnsupportedEncodingException{
		return jpaUtilDao.digestPassword(password);
	}
	
	@Transactional
	@RequestMapping(value="digestOldPassword/{password}",method=RequestMethod.GET)
	@ResponseBody
	public String digestOldPassword(@PathVariable("password")String password,@RequestHeader("Accept-Encoding") String acceptEncoding)throws UnsupportedEncodingException{
		return jpaUtilDao.digestOldPassword(password);
	}
}
