package com.doctusoft.smiling.security;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class UserContext {

	String email;
	PermissionLevel permission;
}
