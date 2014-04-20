/**
 * 
 */
package name.chenyuelin.custom;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import name.chenyuelin.webapp.dto.AjaxBindingErrorMessage;
import name.chenyuelin.webapp.dto.AjaxBindingErrorMessages;

import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 * @author P1
 * @version 1.0 Jan 20, 2013
 */
public class AjaxExceptionResolver extends AbstractHandlerExceptionResolver {

	private MessageSource messageSource;
	
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		if (ex instanceof MethodArgumentNotValidException) {
			return handleMethodArgumentNotValid(request, response, handler, (MethodArgumentNotValidException) ex);
		} else {
			return null;
		}
	}

	private ModelAndView handleMethodArgumentNotValid(HttpServletRequest request, HttpServletResponse response, Object handler, MethodArgumentNotValidException ex) {
		BindingResult bindingResult = ex.getBindingResult();
		AjaxBindingErrorMessages ajaxBindingErrorMessages=new AjaxBindingErrorMessages();
		Collection<AjaxBindingErrorMessage> ajaxBindingErrorMessageCollection=new ArrayList<AjaxBindingErrorMessage>();
		ajaxBindingErrorMessages.setErrorMessage(ajaxBindingErrorMessageCollection);
		List<ObjectError> errorList = bindingResult.getGlobalErrors();
		for (ObjectError objectError : errorList) {
			AjaxBindingErrorMessage ajaxBindingErrorMessage=new AjaxBindingErrorMessage();
			ajaxBindingErrorMessage.setObjectName(objectError.getObjectName());
			ajaxBindingErrorMessage.setMessage(messageSource.getMessage(objectError.getCode(), objectError.getArguments(), objectError.getDefaultMessage(), RequestContextUtils.getLocale(request)));
			ajaxBindingErrorMessageCollection.add(ajaxBindingErrorMessage);
		}
		List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
		for (FieldError fieldError : fieldErrorList) {
			AjaxBindingErrorMessage ajaxBindingErrorMessage=new AjaxBindingErrorMessage();
			ajaxBindingErrorMessage.setObjectName(fieldError.getObjectName());
			ajaxBindingErrorMessage.setFieldName(fieldError.getField());
			ajaxBindingErrorMessage.setRejectedValue(fieldError.getRejectedValue());
			ajaxBindingErrorMessage.setMessage(messageSource.getMessage(fieldError.getCode(), fieldError.getArguments(), fieldError.getDefaultMessage(), RequestContextUtils.getLocale(request)));
			ajaxBindingErrorMessageCollection.add(ajaxBindingErrorMessage);
		}
		ModelAndView mav=new ModelAndView("ajaxBindingErrorMessages","ajaxBindingErrorMessages",ajaxBindingErrorMessages);
		response.setContentType(request.getContentType());
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		return mav;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
}
