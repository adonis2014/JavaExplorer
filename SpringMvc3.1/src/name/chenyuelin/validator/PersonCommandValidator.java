/**
 * 
 */
package name.chenyuelin.validator;

import name.chenyuelin.command.PersonCommand;

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
public class PersonCommandValidator implements Validator {

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return ClassUtils.isAssignable(PersonCommand.class,clazz);
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {
		PersonCommand command=(PersonCommand)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.requirement.name","name is required!");
		
		if(command.getSalary()<0){
			errors.rejectValue("salary", "error.positive.salary", "salary must positive!");
		}
		
		if(command.getHeight()<=0){
			errors.rejectValue("height", "error.positive.height", "height must positive!");
		}
	}

}
