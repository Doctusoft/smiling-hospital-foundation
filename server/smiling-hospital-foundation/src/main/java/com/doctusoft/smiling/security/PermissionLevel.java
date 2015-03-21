package com.doctusoft.smiling.security;

public enum PermissionLevel {
	// the order is important
	VISITOR, REGISTRATED, IN_TRIAL, VOLUNTEER, EXPERIENCED_VOLUNTEER, COORDINATOR, NATIONAL_COORDINATOR;

	public boolean hasAccess(PermissionLevel permissionLevel) {
		return ordinal() >= permissionLevel.ordinal();
	}
}
