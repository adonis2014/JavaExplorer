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
		/*objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
		objectMapper.getSerializationConfig().setDateFormat(myDateFormat);
		final DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		objectMapper.setDateFormat(myDateFormat); // 1.8 and above
		objectMapper.getSerializationConfig().setDateFormat(myDateFormat); // for earlier versions (deprecated for 1.8+)
*/		return objectMapper;
	}
}
