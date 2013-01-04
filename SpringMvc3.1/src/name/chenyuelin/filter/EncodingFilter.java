package name.chenyuelin.filter;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.xml.XmlAwareFormHttpMessageConverter;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class EncodingFilter implements Filter {
	private static final FormHttpMessageConverter FORM_CONVERTER = new XmlAwareFormHttpMessageConverter();
	
	private String encoding;
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		if(request instanceof HttpServletRequest){
			HttpServletRequest req=(HttpServletRequest)request;
			String method=req.getMethod();
			if(method.equals("POST")){
				if("XMLHttpRequest".equals(req.getHeader("x-requested-with"))){
					request.setCharacterEncoding("utf-8");
				}else{
					request.setCharacterEncoding(encoding);
				}
			}else if(method.equals("PUT")){
				request.setCharacterEncoding("utf-8");
				if(request.getParameter("doConverter")!=null){
					HttpInputMessage inputMessage = new InputStreamBodyServletServerHttpRequest(req);
					MultiValueMap<String, String> formParameters = FORM_CONVERTER.read(null, inputMessage);
					HttpServletRequest wrapper = new HttpPutFormContentRequestWrapper(req, formParameters);
					request=wrapper;
				}
			}
		}
		filterChain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		encoding = arg0.getInitParameter("encoding");
	}
	
	private static final class InputStreamBodyServletServerHttpRequest extends ServletServerHttpRequest{
		private final HttpServletRequest servletRequest;
		
		public InputStreamBodyServletServerHttpRequest(HttpServletRequest servletRequest){
			super(servletRequest);
			this.servletRequest=servletRequest;
		}
		
		public InputStream getBody() throws IOException {
			return servletRequest.getInputStream();
		}
	}
	
	private static class HttpPutFormContentRequestWrapper extends HttpServletRequestWrapper {
		
		private MultiValueMap<String, String> formParameters;

		public HttpPutFormContentRequestWrapper(HttpServletRequest request, MultiValueMap<String, String> parameters) {
			super(request);
			this.formParameters = (parameters != null) ? parameters : new LinkedMultiValueMap<String, String>();
		}

		@Override
		public String getParameter(String name) {
			String queryStringValue = super.getParameter(name);
			String formValue = this.formParameters.getFirst(name);
			return (queryStringValue != null) ?  queryStringValue : formValue;
		}

		@Override
		public Map<String, String[]> getParameterMap() {
			Map<String, String[]> result = new LinkedHashMap<String, String[]>();
			Enumeration<String> names = this.getParameterNames();
			while (names.hasMoreElements()) {
				String name = names.nextElement();
				result.put(name, this.getParameterValues(name));
			}
			return result;
		}

		@SuppressWarnings("unchecked")
		@Override
		public Enumeration<String> getParameterNames() {
			Set<String> names = new LinkedHashSet<String>();
			names.addAll(Collections.list(super.getParameterNames()));
			names.addAll(this.formParameters.keySet());
			return Collections.enumeration(names);
		}

		@Override
		public String[] getParameterValues(String name) {
			String[] queryStringValues = super.getParameterValues(name);
			List<String> formValues = this.formParameters.get(name);
			if (formValues == null) {
				return queryStringValues;
			}
			else if (queryStringValues == null) {
				return formValues.toArray(new String[formValues.size()]);
			}
			else {
				List<String> result = new ArrayList<String>();
				result.addAll(Arrays.asList(queryStringValues));
				result.addAll(formValues);
				return result.toArray(new String[result.size()]);
			}
		}
	}
}
