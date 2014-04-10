package name.chenyuelin.security;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;

public class ConfigAttributeFactory {
	private static final ConfigAttributeFactory DEFAULT_CONFIGATTRIBUTE_FACTORY = new ConfigAttributeFactory();

	private final Map<String, ConfigAttribute> configAttributeStorage = new HashMap<String, ConfigAttribute>();

	public ConfigAttribute getConfigAttribute(String key) {
		ConfigAttribute configAttribute = configAttributeStorage.get(key);
		if (configAttribute == null) {
			configAttribute = new SecurityConfig(key);
		}
		return configAttribute;
	}

	public Collection<ConfigAttribute> getConfigAttributes(String... keys) {
		Collection<ConfigAttribute> configAttributes = new HashSet<ConfigAttribute>();
		for (String key : keys) {
			configAttributes.add(getConfigAttribute(key));
		}
		return configAttributes;
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return configAttributeStorage.values();
	}

	public static ConfigAttribute getDefaultConfigAttribute(String key) {
		return DEFAULT_CONFIGATTRIBUTE_FACTORY.getConfigAttribute(key);
	}

	public static Collection<ConfigAttribute> getDefaultConfigAttributes(String... keys) {
		return DEFAULT_CONFIGATTRIBUTE_FACTORY.getConfigAttributes(keys);
	}

	public static Collection<ConfigAttribute> getDefaultAllConfigAttributes() {
		return DEFAULT_CONFIGATTRIBUTE_FACTORY.getAllConfigAttributes();
	}
}
