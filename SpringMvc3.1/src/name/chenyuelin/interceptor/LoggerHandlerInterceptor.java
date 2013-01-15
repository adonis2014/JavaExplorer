/**
 * 
 */
package name.chenyuelin.interceptor;

import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author P1
 * @version 1.0 Jan 13, 2013
 */
public class LoggerHandlerInterceptor implements HandlerInterceptor {
    private static final Log LOG = LogFactory.getLog(LoggerHandlerInterceptor.class);
    private static final String LOG_NAME="LOG";
    
    private final Map<Class<?>, Log> logMap=new Hashtable<Class<?>, Log>();
    
    @Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	    if(LOG.isDebugEnabled()){
	        HandlerMethod handlerMethod=(HandlerMethod)handler;
	        Class<?> original=handlerMethod.getBeanType();
	        Log log=logMap.get(original);
	        if(log==null){
	            try {
	                log=(Log)original.getField(LOG_NAME).get(null);
	                logMap.put(original, log);
	            } catch (NoSuchFieldException e) {
	                LOG.warn("", e);
	                return true;
	            }
	        }
	        if(log.isDebugEnabled()){
	            Method method=handlerMethod.getMethod();
	            MethodParameter[] methodParameters=handlerMethod.getMethodParameters();
	            StringBuilder debugLogInfo=new StringBuilder(50);
	            debugLogInfo.append(method.getName()).append(" start.\t");
	            debugLogInfo.append("parameters:\n");
	            for(MethodParameter methodParameter:methodParameters){
	                System.out.println(methodParameter);
	            }
	            
	            log.debug(debugLogInfo);
	        }
	        
	    }
		return true;
	}

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet
     * .http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
     * java.lang.Object, org.springframework.web.servlet.ModelAndView)
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
        if (LOG.isDebugEnabled()) {

        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax
     * .servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
     * java.lang.Object, java.lang.Exception)
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        if (LOG.isDebugEnabled()) {

        }

    }

}
