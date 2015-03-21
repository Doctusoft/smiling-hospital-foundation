package com.doctusoft.smiling.security;

import java.util.Set;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class UserContext {

	String email;
	Set<String> cities;
	PermissionLevel permission;
}
