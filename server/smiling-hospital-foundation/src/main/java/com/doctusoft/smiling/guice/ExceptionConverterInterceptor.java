package com.doctusoft.smiling.guice;

import lombok.extern.slf4j.Slf4j;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.doctusoft.smiling.security.AccessDeniedException;
import com.doctusoft.smiling.security.UserNotLoggedInException;
import com.google.api.server.spi.ServiceException;
import com.google.api.server.spi.response.BadRequestException;
import com.google.api.server.spi.response.ForbiddenException;
import com.google.api.server.spi.response.InternalServerErrorException;
import com.google.api.server.spi.response.UnauthorizedException;

@Slf4j
public class ExceptionConverterInterceptor implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		try {
			return invocation.proceed();
		} catch (Exception e) {
			throw convertException(e);
		}
	}

	private ServiceException convertException(Exception e) {
		if (e instanceof UserNotLoggedInException) {
			return new UnauthorizedException(e.getMessage());
		}

		if (e instanceof AccessDeniedException) {
			return new ForbiddenException(e.getMessage());
		}

		log.error("Converting error: ", e);
		if (e instanceof ServiceException) {
			return (ServiceException) e;
		}

		if (e instanceof IllegalArgumentException) {
			return new BadRequestException(e.getMessage());
		}

		return new InternalServerErrorException(e.getMessage());
	}

}
