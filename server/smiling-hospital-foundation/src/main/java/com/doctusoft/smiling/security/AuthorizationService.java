package com.doctusoft.smiling.security;

import com.google.inject.ImplementedBy;

@ImplementedBy(AuthorizationServiceImpl.class)
public interface AuthorizationService {

	void checkAccess(PermissionLevel permissionLevel) throws AccessDeniedException;

}
