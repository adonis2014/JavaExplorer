/**
 * 
 */
package name.chenyuelin.webapp.interceptor;

import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
	        Log log=getLog(original);
	        if(log!=null&&log.isDebugEnabled()){
	            log.debug(handlerMethod.getMethod().getName()+" start.");
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
        if(LOG.isDebugEnabled()){
            HandlerMethod handlerMethod=(HandlerMethod)handler;
            Class<?> original=handlerMethod.getBeanType();
            Log log=getLog(original);
            if(log!=null&&log.isDebugEnabled()){
                StringBuilder debugMessage=new StringBuilder();
                debugMessage.append(handlerMethod.getMethod().getName()).append(" end.\n");
                debugMessage.append("Returns:").append(modelAndView.getModelMap());
                log.debug(debugMessage);
            }
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
            LOG.debug("afterCompletion");
        }
    }
    
    private Log getLog(Class<?> targetClass) throws IllegalArgumentException, SecurityException, IllegalAccessException {
        Log log = logMap.get(targetClass);
        if (log == null) {
            try {
                log = (Log) targetClass.getField(LOG_NAME).get(null);
                logMap.put(targetClass, log);
            } catch (NoSuchFieldException e) {
                LOG.warn(e);
                return null;
            } catch (IllegalArgumentException e) {
                throw e;
            } catch (SecurityException e) {
                throw e;
            } catch (IllegalAccessException e) {
                throw e;
            }
        }
        return log;
    }

}
