package name.chenyuelin.webapp.validator;

import name.chenyuelin.webapp.command.SsRoleCommand;

import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class SsRoleCommandValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ClassUtils.isAssignable(SsRoleCommand.class,clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.requirement.name","名称不能为空");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "enabled", "error.requirement.name","不能为空");
	}

}
