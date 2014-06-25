package name.chenyuelin.person.springws;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.ws.soap.security.wss4j.Wss4jSecurityInterceptor;

public class BeanPostProcessorImp implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if(bean.getClass().isAssignableFrom(Wss4jSecurityInterceptor.class)){
			System.out.println(beanName);
		}
		return bean;
	}

}
