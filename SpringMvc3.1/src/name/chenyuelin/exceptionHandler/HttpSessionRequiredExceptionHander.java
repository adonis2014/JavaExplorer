/**
 * 
 */
package name.chenyuelin.exceptionHandler;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author P1
 * @version 1.0 Jan 12, 2013
 */
@Controller
public class HttpSessionRequiredExceptionHander {
	@ExceptionHandler(value={HttpSessionRequiredException.class,SQLException.class})
	public String HttpSessionRequiredExceptionProcess(HttpSessionRequiredException exception,HttpServletRequest request){
		return "sessionRequired";
	}
}
