/**
 * 
 */
package name.chenyuelin.schedule;

import java.util.TimerTask;
import java.util.concurrent.ConcurrentMap;

import javax.annotation.Resource;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

/**
 * @author P1
 * @version 1.0 Feb 21, 2013
 */
@Component
public class CacheCleanSchedule extends TimerTask {
	private static final int cacheSize = 10000;

	private CacheManager cacheManager;

	@Resource(name = "cacheManager")
	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	@Override
	public void run() {
		for (String cacheName : cacheManager.getCacheNames()) {
			Cache cache = cacheManager.getCache(cacheName);
			ConcurrentMap<?, ?> nativeCache = (ConcurrentMap<?, ?>) cache.getNativeCache();
			if (nativeCache.size() > cacheSize) {
				cache.clear();
			}
		}

	}

}
