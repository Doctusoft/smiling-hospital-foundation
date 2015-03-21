package com.doctusoft.smiling.guice;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.doctusoft.smiling.security.CloudEndpointAuthenticationService;
import com.google.api.server.spi.auth.common.User;
import com.google.api.server.spi.config.Named;
import com.google.inject.Inject;

@Slf4j
public class CloudEndpointAuthenticationInterceptor implements MethodInterceptor {

	@Inject
	private CloudEndpointAuthenticationService authenticationService;

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		if(log.isDebugEnabled()) {
			log.debug(Arrays.toString(invocation.getArguments()));
		}
		User user = getUser(invocation.getArguments());
		String sessionId = getSessionId(invocation.getMethod(), invocation.getArguments());
		log.info((user != null ? user.getEmail() : "null") + " " + sessionId);

		authenticationService.authenticate(user, sessionId);

		return invocation.proceed();
	}

	private String getSessionId(Method method, Object[] arguments) {
		for (int i = 0; i < arguments.length; ++i) {
			Annotation[] annotations = method.getParameterAnnotations()[i];
			for (Annotation annotation : annotations) {
				if (annotation.annotationType() == Named.class) {
					Named named = (Named) annotation;
					if ("sessionId".equalsIgnoreCase(named.value())) {
						if (arguments[i] == null || arguments[i] instanceof String) {
							return (String) arguments[i];
						} else {
							throw new IllegalArgumentException("The session id must be a string!"
									+ method.getDeclaringClass() + "." + method.getName());
						}
					}
				}
			}
		}
		throw new IllegalArgumentException("The session id must be on: "
				+ method.getDeclaringClass() + "." + method.getName());
	}

	private User getUser(Object[] arguments) {
		for (Object object : arguments) {
			if (object instanceof User) {
				return (User) object;
			}
		}
		return null;
	}

}
