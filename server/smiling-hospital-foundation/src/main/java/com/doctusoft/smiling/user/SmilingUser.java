package com.doctusoft.smiling.user;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.doctusoft.smiling.BaseEntity;
import com.doctusoft.smiling.security.PermissionLevel;
import com.google.common.collect.Sets;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Unindex;

@Unindex
@Cache
@AllArgsConstructor
@Builder
@NoArgsConstructor
@EqualsAndHashCode(of="email", callSuper=false)
@Data
@Entity
public class SmilingUser extends BaseEntity {

	public static interface IndexedProperties extends BaseEntity.IndexedProperties {
		String CITIES = "cities";
		String PERMISSION_LEVEL = "permissionLevel";
	}

	@Id
	private String email;
	private String passwordHash;
	@Index
	private PermissionLevel permissionLevel;

	private String name;
	private String placeOfBirth;
	private Long dateOfBirth;
	private String residence;
	@Index
	private Set<String> cities = Sets.newHashSet();
	private String phoneNumber;
	private String occupation;
	private Set<String> skills = Sets.newHashSet();
	private String experienceWithArt;
	private String experienceWithChildren;
	private String experienceWithSickChildren;
	private String experienceWithHospitals;
	private Set<String> reasonsOfApplication = Sets.newHashSet();
	private VisitationFrequency visitationFrequency;
	private String visitationDays;
	private String comment;
	private String photoUrl;
}
