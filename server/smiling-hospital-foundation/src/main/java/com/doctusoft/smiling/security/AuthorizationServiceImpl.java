package com.doctusoft.smiling.security;

import com.google.appengine.api.NamespaceManager;
import com.google.inject.Inject;

class AuthorizationServiceImpl implements AuthorizationService {

	private final AuthenticationService authenticationService;

	@Inject
	public AuthorizationServiceImpl(AuthenticationService authenticationService) {
		super();
		this.authenticationService = authenticationService;
	}

	@Override
	public void checkAccess(PermissionLevel permissionLevel) throws AccessDeniedException {
		UserContext userContext = authenticationService.getUserContext();
		if (!userContext.getPermission().hasAccess(permissionLevel)) {
			if (userContext.getEmail() == null) {
				throw new UserNotLoggedInException();
			} else {
				throw new AccessDeniedException();
			}
		} else {
			NamespaceManager.set(null);
		}
	}

}
