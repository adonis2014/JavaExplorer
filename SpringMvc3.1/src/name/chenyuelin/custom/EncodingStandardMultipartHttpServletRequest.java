/**
 * 
 */
package name.chenyuelin.custom;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import name.chenyuelin.constants.BaseConstants;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

/**
 * @author U1
 * @version 1.0 2013-2-8
 */
public class EncodingStandardMultipartHttpServletRequest extends StandardMultipartHttpServletRequest {
	//="GB18030"
	private final String encoding;
	
	public EncodingStandardMultipartHttpServletRequest(String encoding,HttpServletRequest request) throws MultipartException {
		super(request);
		this.encoding=encoding;
	}
	
	public String getParameter(String s){
		String parameter=super.getParameter(s);
		
		if(StringUtils.hasLength(parameter)){
			try {
				parameter = new String(parameter.getBytes(BaseConstants.STRING_ENCODING_ISO88591),encoding);
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
		}
		return parameter;
	};

    public String[] getParameterValues(String s){
    	String[] parameters=super.getParameterValues(s);
    	
    	if(parameters.length>0){
    		String[] encodingParameters=new String[parameters.length];
    		try {
				for(int i=0;i<parameters.length;i+=1){
					encodingParameters[i]=new String(parameters[i].getBytes("ISO-8859-1"),encoding);
				}
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
    		parameters=encodingParameters;
    	}
    	return parameters;
    }

}
