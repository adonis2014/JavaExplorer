package name.chenyuelin.security;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.core.userdetails.User;

public class SecurityAdvisor implements MethodInterceptor{

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object[] arguments=invocation.getArguments();
		String userName=(String)arguments[0];
		User user=(User)invocation.proceed();
		name.chenyuelin.security.User newUser=new name.chenyuelin.security.User(userName,user.getPassword(),user.getAuthorities());
		newUser.setId(Integer.parseInt(user.getUsername()));
		return newUser;
	}
}
