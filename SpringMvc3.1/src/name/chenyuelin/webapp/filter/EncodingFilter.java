package name.chenyuelin.webapp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class EncodingFilter implements Filter {
	
	private String encoding;
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		if(request instanceof HttpServletRequest){
			HttpServletRequest req=(HttpServletRequest)request;
			if("XMLHttpRequest".equals(req.getHeader("x-requested-with"))){
                request.setCharacterEncoding("UTF-8");
            }else{
                request.setCharacterEncoding(encoding);
            }
		}
		filterChain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		encoding = arg0.getInitParameter("encoding");
	}
	
}
