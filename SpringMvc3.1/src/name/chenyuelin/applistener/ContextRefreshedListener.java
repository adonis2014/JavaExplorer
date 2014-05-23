/**
 * 
 */
package name.chenyuelin.applistener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @author U1
 * @version 1.0 2014-5-23
 */
public class ContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent> {
	private static final Log LOG = LogFactory.getLog(ContextRefreshedListener.class);
	private static final String SPRING_WS_APP_NAME = "spring-ws";
	private static final String SPRING_MVC_APP_NAME = "SpringMVC";

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		boolean isDebugLevel = LOG.isDebugEnabled();
		ApplicationContext ac = event.getApplicationContext();
		String appId = ac.getId();
		if (appId.endsWith(ac.getApplicationName())) {
			System.out.println("root:"+appId);
		} else if (appId.endsWith(SPRING_WS_APP_NAME)) {
			System.out.println("ws:"+appId);
		} else if (appId.endsWith(SPRING_MVC_APP_NAME)) {
			System.out.println("mvc:"+appId);
		}
		if(isDebugLevel){
			LOG.debug("AppName:"+ac.getApplicationName()+"\nId:"+appId+"\nName:"+ac.getDisplayName());
		}
	}

}
