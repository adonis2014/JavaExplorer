package name.chenyuelin.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	private ObjectMapper objectMapper;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		User user=(User)authentication.getPrincipal();
		Map<String, Object> body=new HashMap<String, Object>();
		body.put("id", user.getUsername());
		body.put("authorities", authentication.getAuthorities());
		objectMapper.writeValue(response.getWriter(), body);

	}

	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

}
