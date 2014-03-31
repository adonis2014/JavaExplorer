package name.chenyuelin.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class FileUploadControllerValidator implements Validator{
	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

	@Override
	public void validate(Object target, Errors errors) {
		System.out.println(target);
	}
}
