/**
 * 
 */
package name.chenyuelin.schedule;

import java.util.concurrent.ConcurrentMap;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author P1
 * @version 1.0 Feb 21, 2013
 */
@Component
public class CacheCleanSchedule{
	public static final Log LOG=LogFactory.getLog(CacheCleanSchedule.class);
	
	private static final int cacheSize = 10000;

	private CacheManager cacheManager;

	@Resource(name = "cacheManager")
	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	@Scheduled(fixedRate=1000*60*24,initialDelay=1000*60*24)
	public void cleanCache() {
		for (String cacheName : cacheManager.getCacheNames()) {
			Cache cache = cacheManager.getCache(cacheName);
			ConcurrentMap<?, ?> nativeCache = (ConcurrentMap<?, ?>) cache.getNativeCache();
			if (nativeCache.size() > cacheSize) {
				cache.clear();
			}
		}
	}

	@Scheduled(fixedDelay=Long.MAX_VALUE)
	public void initContainer(){
		LOG.info("容器正常启动");
	}
	
	@PostConstruct
	public void init(){
		LOG.info("对象正常启动");
	}
	
	@PreDestroy
	public void destroy(){
		LOG.info("对象正常注销");
	}
}
