package name.chenyuelin.controller;

import name.chenyuelin.security.TreeDataFilterInvocationSecurityMetadataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("aaa")
public class TTTTT {
	private TreeDataFilterInvocationSecurityMetadataSource[] treeDataFilterInvocationSecurityMetadataSource;
	private FilterChainProxy[] filterChainProxys;
	
	private AuthenticationManager[] authenticationManager;
	
	private AccessDecisionManager accessDecisionManager;
//	@Resource(name="filterChainProxy")
//	@Autowired
	@Autowired
	public void setAuthenticationManager(AuthenticationManager[] authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	@Autowired
//	@Resource(name="org.springframework.security.access.vote.AffirmativeBased")
	public void setAccessDecisionManager(AccessDecisionManager accessDecisionManager) {
		this.accessDecisionManager = accessDecisionManager;
	}
	@Autowired
	public void setFilterChainProxys(FilterChainProxy[] filterChainProxys) {
		this.filterChainProxys = filterChainProxys;
	}
	public TreeDataFilterInvocationSecurityMetadataSource[] getTreeDataFilterInvocationSecurityMetadataSource() {
		return treeDataFilterInvocationSecurityMetadataSource;
	}
	@Autowired
	public void setTreeDataFilterInvocationSecurityMetadataSource(TreeDataFilterInvocationSecurityMetadataSource[] treeDataFilterInvocationSecurityMetadataSource) {
		this.treeDataFilterInvocationSecurityMetadataSource = treeDataFilterInvocationSecurityMetadataSource;
	}
	
}
