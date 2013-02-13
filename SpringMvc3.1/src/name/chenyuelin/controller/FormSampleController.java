package name.chenyuelin.controller;

import name.chenyuelin.command.PersonCommand;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @ClassName: FormSampleController
 * @Description: TODO(descript the function of this class)
 * @author peter.chen
 * @date 2013-1-10 上午11:54:53
 * 
 */
@Controller
@RequestMapping("form")
@SessionAttributes("person")
public class FormSampleController {
    public static final Log LOG=LogFactory.getLog(FormSampleController.class);
    
    
    @RequestMapping(method=RequestMethod.GET)
    @ModelAttribute
    public PersonCommand getForm(){
        boolean isLogDebug=LOG.isDebugEnabled();
        if(isLogDebug){
            LOG.debug("getForm start.");
        }
        PersonCommand result=new PersonCommand();
        if(isLogDebug){
            LOG.debug("getForm end. New person object has created.");
        }
        return result;
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public String processForm(@ModelAttribute PersonCommand person,SessionStatus sessionStatus){
        sessionStatus.setComplete();
        return "formCommit";
    }
    
    @RequestMapping(value="2",method=RequestMethod.POST)
    public String processForm(@ModelAttribute PersonCommand person,SessionStatus sessionStatus,RedirectAttributes redirectAttributes){
        sessionStatus.setComplete();
        redirectAttributes.addAttribute("name", person.getName()).addFlashAttribute("message", "Account created!");
        return "redirect:formCommit.htm";
    }
    
    @RequestMapping(value="formCommit",method=RequestMethod.GET)
    public String seccussForm(){
    	return "formCommit";
    }
    
//    全局统一处理
    @ExceptionHandler
	public String HttpSessionRequiredExceptionProcess(HttpSessionRequiredException e){
		return "sessionRequired";
	}
}
