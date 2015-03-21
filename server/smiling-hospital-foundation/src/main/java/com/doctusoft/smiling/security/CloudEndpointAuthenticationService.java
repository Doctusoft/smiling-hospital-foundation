package com.doctusoft.smiling.security;

import com.doctusoft.smiling.user.SmilingUser;
import com.doctusoft.smiling.user.SmilingUserDAO;
import com.google.api.server.spi.auth.common.User;
import com.google.appengine.api.NamespaceManager;
import com.google.inject.Inject;

public class CloudEndpointAuthenticationService implements AuthenticationService {

	private static final ThreadLocal<UserContext> CONTEXT_HOLDER = new ThreadLocal<>();

	private final SmilingUserDAO smilingUserDAO;

	@Inject
	public CloudEndpointAuthenticationService(SmilingUserDAO smilingUserDAO) {
		super();
		this.smilingUserDAO = smilingUserDAO;
	}

	@Override
	public UserContext getUserContext() throws UserNotLoggedInException {
		UserContext userContext = CONTEXT_HOLDER.get();
		if (userContext == null) {
			throw new IllegalStateException("The authentication service is not yet initialized.");
		}
		return userContext;
	}

	public void authenticate(User user, String sessionId) {
		if (CONTEXT_HOLDER.get() == null) {
			// FIXME
			String email = user != null ? user.getEmail() : null;
			if (email != null) {
				NamespaceManager.set(null);
				try {
					SmilingUser smilingUser = smilingUserDAO.get(email);
					if (smilingUser != null) {
						createUserContext(smilingUser);
					} else {
						createVisitorContext();
					}
				} finally {
					NamespaceManager.set("error");
				}
			} else {
				createVisitorContext();
			}
		}
	}

	private void createUserContext(SmilingUser smilingUser) {
		CONTEXT_HOLDER.set(UserContext.builder()
				.email(smilingUser.getEmail())
				.permission(smilingUser.getPermissionLevel())
				.build());
	}

	private void createVisitorContext() {
		CONTEXT_HOLDER.set(UserContext.builder().permission(PermissionLevel.VISITOR).build());
	}

	@Override
	public void reset() {
		CONTEXT_HOLDER.remove();
	}

}
