package com.doctusoft.smiling.guice;

import java.lang.reflect.Method;

import lombok.extern.slf4j.Slf4j;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.doctusoft.smiling.security.AuthorizationService;
import com.doctusoft.smiling.security.Restricted;
import com.google.inject.Inject;

@Slf4j
public class AuthorizationInterceptior implements MethodInterceptor {

	@Inject
	private AuthorizationService authorizationService;

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Method method = invocation.getMethod();

		log.debug("Intercepting: " + method.getName());

		Restricted methodRestricted = method.getAnnotation(Restricted.class);
		if(methodRestricted != null) {
			authorizationService.checkAccess(methodRestricted.value());
		} else {
			Class<?> declaringClass = method.getDeclaringClass();
			Restricted classRestricted = declaringClass.getAnnotation(Restricted.class);
			if(classRestricted != null) {
				authorizationService.checkAccess(classRestricted.value());
			} else {
				throw new IllegalArgumentException("The method or the class must contain a Restricted annotations: " + declaringClass + "." + method.getName());
			}
		}

		return invocation.proceed();
	}

}
