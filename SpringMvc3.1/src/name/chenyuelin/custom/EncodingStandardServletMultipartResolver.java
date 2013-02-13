/**
 * 
 */
package name.chenyuelin.custom;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

/**
 * @author U1
 * @version 1.0 2013-2-8
 */
public class EncodingStandardServletMultipartResolver extends StandardServletMultipartResolver {
	private String encoding;
	
	public MultipartHttpServletRequest resolveMultipart(HttpServletRequest request) throws MultipartException {
		return new EncodingStandardMultipartHttpServletRequest(encoding,request);
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
}
