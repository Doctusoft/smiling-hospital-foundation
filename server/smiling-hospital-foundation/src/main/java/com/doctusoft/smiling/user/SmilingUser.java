package com.doctusoft.smiling.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.doctusoft.smiling.BaseEntity;
import com.doctusoft.smiling.security.PermissionLevel;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Cache
@AllArgsConstructor
@Builder
@NoArgsConstructor
@EqualsAndHashCode(of="email", callSuper=false)
@Data
@Entity
public class SmilingUser extends BaseEntity {

	@Id
	private String email;
	private String name;
	private PermissionLevel permissionLevel;
}
