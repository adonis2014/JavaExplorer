package name.chenyuelin.constants;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

public final class BaseConstants {
	private BaseConstants() {
	}

	public static final ObjectMapper DEFAULT_OBJECT_MAPPER = createDefaultObjectMapper();

	private static final ObjectMapper createDefaultObjectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
		// objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		System.out.println(objectMapper);
		return objectMapper;
	}
}
