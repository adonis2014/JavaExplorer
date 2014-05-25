package name.chenyuelin.common;

import java.util.Map;

/**
 * @author U1
 * @version 1.0 2014-5-25
 */
public final class BaseUtil {
	
	public static Object getMapData(Map<?, ?> map, Object... keys) {
		Object currentData = map;
		for (Object key : keys) {
			currentData = ((Map<?, ?>) currentData).get(key);
		}
		return currentData;
	}
}
