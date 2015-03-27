package com.doctusoft.smiling.user;

import java.io.Serializable;
import java.util.Set;

import lombok.Data;

import com.doctusoft.smiling.BaseInputData;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;

@Data
public class RegistrationData extends BaseInputData implements Serializable {

	private String email;
	private String password;

	private String name;
	private String placeOfBirth;
	private Long dateOfBirth;
	private String residence;
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

	public void validateAndNormalize() {
		email = checkString(email, "email");
		password = checkString(password, "password");
		name = checkString(name, "name");
		placeOfBirth = checkString(placeOfBirth, "placeOfBirth");
		Preconditions.checkArgument(dateOfBirth != null, "the date of birth is mandatory");
		residence = checkString(residence, "residence");
		cities = checkCollection(cities, "city");
		phoneNumber = checkString(phoneNumber, "phoneNumber");
		occupation = checkString(occupation, "occupation");
		skills = checkCollection(skills, "skill");
		experienceWithArt = checkString(experienceWithArt, "experienceWithArt");
		experienceWithChildren = checkString(experienceWithChildren, "experienceWithChildren");
		experienceWithSickChildren = checkString(experienceWithSickChildren, "experienceWithSickChildren");
		experienceWithHospitals = checkString(experienceWithHospitals, "experienceWithHospitals");
		reasonsOfApplication = checkCollection(reasonsOfApplication, "reason");
		Preconditions.checkArgument(visitationFrequency != null, "the visitationFrequency can't be blank");
		visitationDays = checkString(visitationDays, "visitationDays");
		comment = checkString(comment, "comment");
	}
	
}
