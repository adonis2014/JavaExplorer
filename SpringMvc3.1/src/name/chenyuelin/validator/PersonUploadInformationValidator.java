/**
 * 
 */
package name.chenyuelin.validator;

import name.chenyuelin.command.PersonUploadInformation;

import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author U1
 * @version 1.0 2013-2-8
 */
@Component
public class PersonUploadInformationValidator implements Validator {

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		 return ClassUtils.isAssignable(PersonUploadInformation.class,clazz);
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "aa.bb","name is required!");
	}

}
