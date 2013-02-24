/**
 * 
 */
package name.chenyuelin.controller;

import java.util.Collection;

import name.chenyuelin.dto.T5Dto;
import name.chenyuelin.dto.T5DtoCollectionWarp;
import name.chenyuelin.service.TService;
import name.chenyuelin.transformer.TTransformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author P1
 * @version 1.0 Feb 19, 2013
 */
@Controller
@RequestMapping("t")
public class TController {
	private TService tService;
	
	@Autowired
	public void settService(TService tService) {
		this.tService = tService;
	}

	@Transactional
	@RequestMapping(value="t5/listByT2Name/{t2Name}",method=RequestMethod.GET)
	public ModelAndView digestOldPassword(@PathVariable("t2Name")String t2Name){
		Collection<T5Dto> dtos=TTransformer.T5EntityCollectionToT5DtoCollection(tService.listT5byT2Name(t2Name));
		T5DtoCollectionWarp dtoWarp=new T5DtoCollectionWarp();
		dtoWarp.setT5Dtos(dtos);
		ModelAndView mav=new ModelAndView("t5","t5s",dtoWarp);
		return mav;
	}
}
