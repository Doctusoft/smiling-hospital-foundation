package com.doctusoft.smiling.security;

import com.google.inject.ImplementedBy;

@ImplementedBy(CloudEndpointAuthenticationService.class)
public interface AuthenticationService {

	UserContext getUserContext();

	void reset();
}
