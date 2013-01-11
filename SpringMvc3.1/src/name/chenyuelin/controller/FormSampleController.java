/* * @(#)
		$Id: FormSampleController.java, peter.chen Exp $
		*****************************************************************************
		* Copyright (c) 2013 - Owned by OverStock. All rights reserved.
		This software is the confidential and proprietary information of
		OverStock ("Confidential Information"). You shall not disclose such
		Confidential Information and shall use it only in accordance with
		the terms of the license agreement you entered into with OverStock.
		*
		*****************************************************************************
		*/
package name.chenyuelin.controller;

import name.chenyuelin.command.Person;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @ClassName: FormSampleController
 * @Description: TODO(descript the function of this class)
 * @author peter.chen
 * @date 2013-1-10 ÉÏÎç11:54:53
 * 
 */
@Controller
@RequestMapping("/form")
public class FormSampleController {
    private static final Log LOG=LogFactory.getLog(FormSampleController.class);
    
    
    @RequestMapping(method=RequestMethod.GET)
    @ModelAttribute
    public Person getForm(){
        boolean isLogDebug=LOG.isDebugEnabled();
        if(isLogDebug){
            LOG.debug("getForm start.");
        }
        Person result=new Person();
        if(isLogDebug){
            LOG.debug("getForm end. New person object has created.");
        }
        return result;
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public String processForm(@ModelAttribute Person person){
        boolean isLogDebug=LOG.isDebugEnabled();
        if(isLogDebug){
            StringBuilder debugInfo=new StringBuilder(50);
            debugInfo.append("processForm start.\n Request parameters:");
            debugInfo.append("name:").append(person.getName()).append("\t");
            debugInfo.append("sex:").append(person.getSex()).append("\t");
            debugInfo.append("birthday:").append(person.getBirthday()).append("\t");
            debugInfo.append("height:").append(person.getHeight()).append("\t");
            debugInfo.append("breakfastTime:").append(person.getBreakfastTime()).append("\t");
            debugInfo.append("createTime:").append(person.getCreateTime()).append("\t");
            debugInfo.append("salary:").append(person.getSalary()).append("\t");
            debugInfo.append("note:").append(person.getNote());
            LOG.debug(debugInfo);
        }
        if(isLogDebug){
            StringBuilder debugInfo=new StringBuilder();
            debugInfo.append("processForm end.\n");
            debugInfo.append("go page formCommit.");
            LOG.debug(debugInfo);
        }
        return "formCommit";
    }
}
