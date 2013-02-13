package name.chenyuelin.validator;

import java.util.Collection;

import javax.annotation.Resource;

import name.chenyuelin.command.PersonCommand;
import name.chenyuelin.command.PersonCommandListWrap;

import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PersonCommandListWrapValidator implements Validator {
	private Validator personCommandValidator;

	@Resource(name = "personCommandValidator")
	public void setPersonCommandValidator(Validator personCommandValidator) {
		this.personCommandValidator = personCommandValidator;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return ClassUtils.isAssignable(PersonCommandListWrap.class, clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		PersonCommandListWrap commandWrap = (PersonCommandListWrap) target;

		Collection<PersonCommand> personCommandcollection = commandWrap.getPersonCommands();

		if (!CollectionUtils.isEmpty(personCommandcollection)) {
			StringBuilder nestedPath = new StringBuilder();
			int i = 0;
			for (PersonCommand command : personCommandcollection) {
				nestedPath.append("personCommands[").append(i).append("]");
				errors.pushNestedPath(nestedPath.toString());
				ValidationUtils.invokeValidator(personCommandValidator, command, errors);
				errors.popNestedPath();
				nestedPath.setLength(0);
				i+=1;
			}
		}
	}

}
