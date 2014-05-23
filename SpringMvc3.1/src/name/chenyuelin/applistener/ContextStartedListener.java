package name.chenyuelin.applistener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;

public class ContextStartedListener implements ApplicationListener<ContextStartedEvent> {
	private static final Log LOG = LogFactory.getLog(ContextStartedListener.class);
	
	@Override
	public void onApplicationEvent(ContextStartedEvent event) {
		ApplicationContext ac = event.getApplicationContext();
		if (LOG.isDebugEnabled()) {
			LOG.debug("ContextStartedListener. Parent is " + ac.getParent());
		}
	}

}
