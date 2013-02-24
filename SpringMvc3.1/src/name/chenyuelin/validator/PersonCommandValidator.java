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
		if(command.getId()<1){
			errors.rejectValue("id", "aa.bb", "id is required!");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "aa.bb","name is required!");
		
		if(command.getSalary()<0){
			errors.rejectValue("salary", "aa.bb", "salary must positive!");
		}
		
		if(command.getHeight()<=0){
			errors.rejectValue("height", "aa.bb", "height must positive!");
		}
	}

}