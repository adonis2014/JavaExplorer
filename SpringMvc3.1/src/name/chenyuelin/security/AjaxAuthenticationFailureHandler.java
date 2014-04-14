package name.chenyuelin.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class AjaxAuthenticationFailureHandler implements AuthenticationFailureHandler {
	private static final Log LOG=LogFactory.getLog(AjaxAuthenticationFailureHandler.class);
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		if (LOG.isDebugEnabled()) {
			LOG.debug("[" + request.getServletPath() + "] " + exception.getMessage());
		}
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		PrintWriter write=response.getWriter();
		write.write(exception.getMessage());
		write.close();
	}
}
