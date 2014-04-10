package name.chenyuelin.security;

import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;

import org.springframework.security.access.ConfigAttribute;

public class PathElement {
	private int id;
	private PathElement parent;
	private String pathName;
	private EnumMap<MethodType, Collection<ConfigAttribute>> authorities;
	private Map<String, PathElement> children;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPathName() {
		return pathName;
	}

	public void setPathName(String pathName) {
		this.pathName = pathName;
	}

	public EnumMap<MethodType, Collection<ConfigAttribute>> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(EnumMap<MethodType, Collection<ConfigAttribute>> authorities) {
		this.authorities = authorities;
	}

	public Map<String, PathElement> getChildren() {
		return children;
	}

	public void setChildren(Map<String, PathElement> children) {
		this.children = children;
	}

	public PathElement getParent() {
		return parent;
	}

	public void setParent(PathElement parent) {
		this.parent = parent;
	}

}
