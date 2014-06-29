package name.chenyuelin.person.springws;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

public class AfterReturningAdviceImp implements AfterReturningAdvice {

	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		System.out.println(123);
	}

}
