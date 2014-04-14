package name.chenyuelin.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

public class AjaxLogoutSuccessHandler implements LogoutSuccessHandler {
	private static final Log LOG=LogFactory.getLog(AjaxLogoutSuccessHandler.class);
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		if(LOG.isDebugEnabled()){
			if(authentication==null){
				LOG.debug("There is no user log in!");
			}else{
				LOG.debug(authentication.getName()+" is logout!");
			}
		}
		PrintWriter write=response.getWriter();
		write.write("Logout complete!");
		write.close();
	}

}
