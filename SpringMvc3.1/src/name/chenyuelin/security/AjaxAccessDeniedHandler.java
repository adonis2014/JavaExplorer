package name.chenyuelin.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class AjaxAccessDeniedHandler implements AccessDeniedHandler {
	private static final Log LOG = LogFactory.getLog(AjaxAccessDeniedHandler.class);

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
		if (LOG.isDebugEnabled()) {
			LOG.debug("[" + request.getServletPath() + "] " + accessDeniedException.getMessage());
		}
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		PrintWriter write = response.getWriter();
		write.write(accessDeniedException.getMessage());
		write.close();
	}
}
