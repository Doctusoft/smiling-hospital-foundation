package com.doctusoft.smiling.guice;

import com.doctusoft.smiling.security.Restricted;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

public class SmilingModule extends AbstractModule {

	@Override
	protected void configure() {
		CloudEndpointAuthenticationInterceptor cloudEndpointAuthenticationInterceptor = new CloudEndpointAuthenticationInterceptor();
		requestInjection(cloudEndpointAuthenticationInterceptor);
		bindInterceptor(
				Matchers.annotatedWith(Api.class),
				Matchers.annotatedWith(ApiMethod.class),
				cloudEndpointAuthenticationInterceptor);

		bindInterceptor(
				Matchers.annotatedWith(Api.class),
				Matchers.annotatedWith(ApiMethod.class),
				new ExceptionConverterInterceptor());

		AuthorizationInterceptior authorizationInterceptior = new AuthorizationInterceptior();
		requestInjection(authorizationInterceptior);
		bindInterceptor(
				Matchers.any(),
				Matchers.annotatedWith(Restricted.class),
				authorizationInterceptior);
		bindInterceptor(
				Matchers.annotatedWith(Restricted.class),
				Matchers.any(),
				authorizationInterceptior);
	}

}
