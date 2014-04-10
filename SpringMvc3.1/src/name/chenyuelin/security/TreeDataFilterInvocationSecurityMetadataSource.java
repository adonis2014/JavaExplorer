package name.chenyuelin.security;

import java.util.Collection;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import name.chenyuelin.constants.BaseConstants;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

public class TreeDataFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource, InitializingBean {
	private static final Log LOG = LogFactory.getLog(TreeDataFilterInvocationSecurityMetadataSource.class);

	//@formatter:off
	private static final String QUERY_AUTHORITY = 
		"SELECT r.id,r.parent_id,r.pathname,sa.method,a.authority FROM ss_resource r "+
		"INNER JOIN ss_resource_authority sa ON r.id=sa.resource_id AND r.enabled=true "+
		"INNER JOIN ss_authority a ON sa.authority_id=a.id AND sa.enabled=true "+
		"ORDER BY r.id;";
	//@formatter:on

	private final Map<String, PathElement> authoritiesTree = new HashMap<String, PathElement>();

	private String rolePrefix;
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		if (LOG.isTraceEnabled()) {
			LOG.trace("Get attributes from " + object);
		}
		Collection<ConfigAttribute> configAttributes = null;
		if (object instanceof FilterInvocation) {
			FilterInvocation filterInvocation = (FilterInvocation) object;
			String[] paths = filterInvocation.getRequestUrl().split(BaseConstants.STRING_URI_SEPARATOR);
			MethodType method = MethodType.valueOf(filterInvocation.getHttpRequest().getMethod());

			PathElement currentPathElement = null;
			for (int i = 1; i < paths.length; i += 1) {
				String path = paths[i];
				if (currentPathElement == null) {
					currentPathElement = authoritiesTree.get(path);
					if (currentPathElement == null) {
						throw new IllegalArgumentException("URL path can not found in DB!");
					}
				} else {
					Map<String, PathElement> leaf = currentPathElement.getChildren();
					PathElement pathElement = leaf.get(path);
					if (pathElement == null) {
						break;
					} else {
						currentPathElement = pathElement;
					}
				}
			}

			configAttributes = getAttributes(currentPathElement, method);
		}
		return configAttributes;
	}

	private Collection<ConfigAttribute> getAttributes(PathElement pathElement, MethodType methodType) {
		if (pathElement == null) {
			return null;
		}
		Collection<ConfigAttribute> configAttributes = pathElement.getAuthorities().get(methodType);
		if (configAttributes == null && methodType != MethodType.ALL) {
			configAttributes = pathElement.getAuthorities().get(MethodType.ALL);
		}
		if (configAttributes == null) {
			configAttributes = getAttributes(pathElement.getParent(), methodType);
		}
		return configAttributes;
	}

	private void fillAuthoritiesTree(SqlRowSet sqlRowSet) {
		Map<Integer, PathElement> pathElementMap = new HashMap<Integer, PathElement>();
		PathElement pathElement = null;
		int id = 0;
		while (sqlRowSet.next()) {
			if (id != sqlRowSet.getInt(1)) {
				id = sqlRowSet.getInt(1);
				int parentId = sqlRowSet.getInt(2);
				String pathname = sqlRowSet.getString("pathname");

				pathElement = new PathElement();
				pathElement.setId(id);
				pathElement.setPathName(pathname);
				pathElement.setChildren(new HashMap<String, PathElement>());
				pathElement.setAuthorities(new EnumMap<MethodType, Collection<ConfigAttribute>>(MethodType.class));

				pathElementMap.put(id, pathElement);

				// true is root path.
				if (id == parentId) {
					authoritiesTree.put(pathname, pathElement);
				} else {
					PathElement parent = pathElementMap.get(parentId);
					if (parent != null) {
						parent.getChildren().put(pathname, pathElement);
						pathElement.setParent(parent);
					}
				}
			}
			EnumMap<MethodType, Collection<ConfigAttribute>> authorities = pathElement.getAuthorities();
			MethodType methodType = MethodType.valueOf(sqlRowSet.getString("method"));
			Collection<ConfigAttribute> configAttributes = authorities.get(methodType);
			if (configAttributes == null) {
				configAttributes = new HashSet<ConfigAttribute>();
				authorities.put(methodType, configAttributes);
			}
			configAttributes.add(ConfigAttributeFactory.getDefaultConfigAttribute(rolePrefix + sqlRowSet.getString("authority")));
		}
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return ConfigAttributeFactory.getDefaultAllConfigAttributes();
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}

	public void cleanAuthorities() {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Cleaning authorities...");
		}
		authoritiesTree.clear();
	}

	public void loadAuthorities() {
		boolean logIsDebug = LOG.isDebugEnabled();
		if (logIsDebug) {
			LOG.debug("Loading authorities...");
		}
		SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(QUERY_AUTHORITY);
		fillAuthoritiesTree(sqlRowSet);
		if (logIsDebug) {
			LOG.debug("Load authorities is complete.");
		}
	}

	public void setRolePrefix(String rolePrefix) {
		this.rolePrefix = rolePrefix;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if(LOG.isTraceEnabled()){
			LOG.trace("afterPropertiesSet");
		}
	}

}