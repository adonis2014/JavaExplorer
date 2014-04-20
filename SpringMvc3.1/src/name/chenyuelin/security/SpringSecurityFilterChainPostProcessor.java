package name.chenyuelin.security;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.config.ConstructorArgumentValues.ValueHolder;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.ManagedList;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

public class SpringSecurityFilterChainPostProcessor implements BeanFactoryPostProcessor {
	private static final Log LOG = LogFactory.getLog(SpringSecurityFilterChainPostProcessor.class);

	private String securityFilterChainName;
	private Collection<Class<?>> excludeFilterClasses;

	private boolean rejectPublicInvocations;
	private boolean usernameBasedPrimaryKey;
	
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		removeExcludeFilters(beanFactory);
		setFilterInvocationSecurityMetadataSource(beanFactory);
		setFilterSecurityInterceptorProperties(beanFactory);
		setJdbcUserDetailsManagerProperties(beanFactory);
	}

	private void removeExcludeFilters(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		boolean logIsTrace = LOG.isTraceEnabled();
		boolean logIsDebug = LOG.isDebugEnabled();
		BeanDefinition httpSecurityFilterChain = beanFactory.getBeanDefinition(securityFilterChainName);
		ConstructorArgumentValues constructorArgumentValues = httpSecurityFilterChain.getConstructorArgumentValues();
		// FilterChainProxy filterChainProxy=beanFactory.getBean("org.springframework.security.filterChainProxy", FilterChainProxy.class);
		ValueHolder valueHolder = constructorArgumentValues.getIndexedArgumentValues().get(1);
		ManagedList<?> managedList = (ManagedList<?>) valueHolder.getValue();
		for (int i = 0; i < managedList.size(); i += 1) {
			Object beanElement = managedList.get(i);
			if (beanElement instanceof RootBeanDefinition) {
				RootBeanDefinition rootBeanDefinition = (RootBeanDefinition) managedList.get(i);
				Class<?> beanClass = rootBeanDefinition.getBeanClass();
				if (excludeFilterClasses.contains(beanClass)) {
					managedList.remove(i);
					i -= 1;
				}
			} else if (beanElement instanceof RuntimeBeanReference) {
				if (logIsTrace) {
					LOG.trace("HttpSecurityFilterChain the RuntimeBeanReference is " + beanElement);
				}
			}
		}
		// log out the current filters
		if (logIsDebug) {
			StringBuilder debugMessage = new StringBuilder(30);
			debugMessage.append("Current available filters size is ").append(managedList.size()).append(". ");
			debugMessage.append("They are:\n");
			for (Object filter : managedList) {
				debugMessage.append(filter).append("\n");
			}
			debugMessage.deleteCharAt(debugMessage.length() - 1);
			LOG.debug(debugMessage);
		}
	}

	private void setFilterInvocationSecurityMetadataSource(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		boolean logIsTrace = LOG.isTraceEnabled();
		String[] filterInvocationSecurityMetadataSources = beanFactory.getBeanNamesForType(FilterInvocationSecurityMetadataSource.class);
		BeanDefinition metadataSources = null;
		String beanReference = null;
		for (String metadataSourcesName : filterInvocationSecurityMetadataSources) {
			if (metadataSourcesName.startsWith("org.springframework.security")) {
				continue;
			} else {
				beanReference = metadataSourcesName;
				metadataSources = beanFactory.getBeanDefinition(metadataSourcesName);
				break;
			}
		}
		
		if (beanReference != null) {
			String[] filterSecurityInterceptors = beanFactory.getBeanNamesForType(FilterSecurityInterceptor.class);
			if (filterSecurityInterceptors.length > 0) {
				BeanDefinition interceptor = beanFactory.getBeanDefinition(filterSecurityInterceptors[0]);
				MutablePropertyValues mutablePropertyValues = interceptor.getPropertyValues();
				mutablePropertyValues.add("securityMetadataSource", new RuntimeBeanReference(beanReference));
			}
		}

		if (logIsTrace) {
			if (beanReference == null) {
				LOG.trace("Not customer FilterInvocationSecurityMetadataSource had set.");
			} else {
				LOG.trace("Setting " + metadataSources.getBeanClassName() + " complete!");
			}

		}
	}

	private void setFilterSecurityInterceptorProperties(ConfigurableListableBeanFactory beanFactory){
		String[] filterSecurityInterceptors = beanFactory.getBeanNamesForType(FilterSecurityInterceptor.class);
		for(String filterSecurityInterceptor:filterSecurityInterceptors){
			BeanDefinition interceptor = beanFactory.getBeanDefinition(filterSecurityInterceptor);
			MutablePropertyValues mutablePropertyValues = interceptor.getPropertyValues();
			mutablePropertyValues.add("rejectPublicInvocations", rejectPublicInvocations);
		}
	}
	
	private void setJdbcUserDetailsManagerProperties(ConfigurableListableBeanFactory beanFactory){
		String[] jdbcUserDetailsManagers = beanFactory.getBeanNamesForType(JdbcUserDetailsManager.class);
		for(String jdbcUserDetailsManager:jdbcUserDetailsManagers){
			BeanDefinition interceptor = beanFactory.getBeanDefinition(jdbcUserDetailsManager);
			MutablePropertyValues mutablePropertyValues = interceptor.getPropertyValues();
			mutablePropertyValues.add("usernameBasedPrimaryKey", usernameBasedPrimaryKey);
		}
	}
	
	public void setExcludeFilterClasses(Collection<Class<?>> excludeFilterClasses) {
		this.excludeFilterClasses = excludeFilterClasses;
	}

	public void setSecurityFilterChainName(String securityFilterChainName) {
		this.securityFilterChainName = securityFilterChainName;
	}

	public void setRejectPublicInvocations(boolean rejectPublicInvocations) {
		this.rejectPublicInvocations = rejectPublicInvocations;
	}

	public void setUsernameBasedPrimaryKey(boolean usernameBasedPrimaryKey) {
		this.usernameBasedPrimaryKey = usernameBasedPrimaryKey;
	}
}
