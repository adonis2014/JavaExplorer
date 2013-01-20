/**
 * 
 */
package name.chenyuelin.validator;

import name.chenyuelin.command.Person;

import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author P1
 * @version 1.0 Jan 19, 2013
 */
@Component
public class PersonValidator implements Validator {

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return ClassUtils.isAssignable(Person.class,clazz);
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "aa.bb","²»ÄÜÎª¿Õ");

	}

}
