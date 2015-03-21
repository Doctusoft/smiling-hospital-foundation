package com.doctusoft.smiling.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.google.appengine.api.NamespaceManager;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class RequestFilter implements Filter {

	private final AuthenticationService authenticationService;

	@Inject
	public RequestFilter(AuthenticationService authenticationService) {
		super();
		this.authenticationService = authenticationService;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
	ServletException {

		try {
			NamespaceManager.set("error");
			authenticationService.reset();
			chain.doFilter(request, response);
		} finally {
			NamespaceManager.set("error");
			authenticationService.reset();
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}

}
