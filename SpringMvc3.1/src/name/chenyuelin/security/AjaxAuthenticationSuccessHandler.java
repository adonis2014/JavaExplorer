package name.chenyuelin.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	private static final Log LOG = LogFactory.getLog(AjaxAuthenticationSuccessHandler.class);

	private ObjectMapper objectMapper;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		User user = (User) authentication.getPrincipal();
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("authorities", authentication.getAuthorities());
		
		if(user instanceof name.chenyuelin.security.User){
			body.put("id", ((name.chenyuelin.security.User)user).getId());
		}
		
		objectMapper.writeValue(response.getWriter(), body);

		if (LOG.isDebugEnabled()) {
			LOG.debug("User " + user.getUsername() + " lonin is complete. Authorities are " + authentication.getAuthorities());
		}
	}

	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

}
