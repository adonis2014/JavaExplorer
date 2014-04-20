package name.chenyuelin.webapp.validator;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;

@Component
@Aspect
@Order(0)
public class FileUploadControllerValidator{
	@Before(value="execution(* name.chenyuelin.webapp.controller.FileUploadController.download1(String,org.springframework.web.context.request.NativeWebRequest)) && args(picName,nativeWebRequest)"
		,argNames="picName,nativeWebRequest")
	public void validateDownload1(JoinPoint joinPoint,String picName, NativeWebRequest nativeWebRequest)throws Exception{
		//throw new RequestArgumentNotValidException();
	}
}
