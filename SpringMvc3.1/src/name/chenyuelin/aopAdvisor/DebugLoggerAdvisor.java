/* * @(#)
		$Id: DebugLoggerAdvisor.java, peter.chen Exp $
 *****************************************************************************
 * Copyright (c) 2013 - Owned by OverStock. All rights reserved.
		This software is the confidential and proprietary information of
		OverStock ("Confidential Information"). You shall not disclose such
		Confidential Information and shall use it only in accordance with
		the terms of the license agreement you entered into with OverStock.
 *
 *****************************************************************************
 */
package name.chenyuelin.aopAdvisor;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

/**
 * @ClassName: DebugLoggerAdvisor
 * @Description: TODO(descript the function of this class)
 * @author peter.chen
 * @date 2013-1-15 ÉÏÎç11:31:25
 * 
 */
public class DebugLoggerAdvisor implements MethodBeforeAdvice, AfterReturningAdvice {
    private static final String LOG_NAME = "LOG";

    private static final Log LOG = LogFactory.getLog(DebugLoggerAdvisor.class);
    private final Map<Class<?>, Log> logMap = new HashMap<Class<?>, Log>();

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        if (LOG.isDebugEnabled()) {
            Log log = getLog(target);

            if (log != null && log.isDebugEnabled()) {
                StringBuilder debugMessage = new StringBuilder(50);
                debugMessage.append(method.getName()).append(" start.\nParameters:\n");
                for (Object arg : args) {
                    Class<?> argClass = arg.getClass();
                    debugMessage.append(argClass.getSimpleName()).append(":").append(arg).append("\n");
                }
                if(debugMessage.length()>0){
                    debugMessage.deleteCharAt(debugMessage.length() - 1);
                }
                log.debug(debugMessage);
            }

        }
    }
    
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        if (LOG.isDebugEnabled()) {
            Log log = getLog(target);
            if (log != null && log.isDebugEnabled()) {
                StringBuilder debugMessage = new StringBuilder(50);
                debugMessage.append(method.getName()).append(" end.\nReturns:{");
                debugMessage.append(returnValue.getClass().getSimpleName()).append(":").append(returnValue).append("}");
                log.debug(debugMessage);
            }

        }
    }

    private Log getLog(Object target) throws IllegalArgumentException, SecurityException, IllegalAccessException {
        Class<?> targetClass = target.getClass();
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
