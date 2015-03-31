package com.doctusoft.smiling.user;

import java.io.Serializable;
import java.util.Set;

import lombok.Data;

import com.doctusoft.smiling.InputDataPreconditions;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;

@Data
public class RegistrationData implements Serializable {

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
		email = 						InputDataPreconditions.checkNotEmptyString(email, "email");
		password = 						InputDataPreconditions.checkNotEmptyString(password, "password");
		name = 							InputDataPreconditions.checkNotEmptyString(name, "name");
		placeOfBirth = 					InputDataPreconditions.checkNotEmptyString(placeOfBirth, "placeOfBirth");
		Preconditions.checkNotNull(dateOfBirth, "the date of birth is mandatory");
		residence = 					InputDataPreconditions.checkNotEmptyString(residence, "residence");
		cities = 						InputDataPreconditions.checkNotEmptyCollection(cities, "city");
		phoneNumber = 					InputDataPreconditions.checkNotEmptyString(phoneNumber, "phoneNumber");
		occupation = 					InputDataPreconditions.checkNotEmptyString(occupation, "occupation");
		skills = 						InputDataPreconditions.checkNotEmptyCollection(skills, "skill");
		experienceWithArt = 			InputDataPreconditions.checkNotEmptyString(experienceWithArt, "experienceWithArt");
		experienceWithChildren = 		InputDataPreconditions.checkNotEmptyString(experienceWithChildren, "experienceWithChildren");
		experienceWithSickChildren = 	InputDataPreconditions.checkNotEmptyString(experienceWithSickChildren, "experienceWithSickChildren");
		experienceWithHospitals = 		InputDataPreconditions.checkNotEmptyString(experienceWithHospitals, "experienceWithHospitals");
		reasonsOfApplication = 			InputDataPreconditions.checkNotEmptyCollection(reasonsOfApplication, "reason");
		Preconditions.checkNotNull(visitationFrequency, "the visitationFrequency can't be blank");
		visitationDays = 				InputDataPreconditions.checkNotEmptyString(visitationDays, "visitationDays");
		comment = 						InputDataPreconditions.checkNotEmptyString(comment, "comment");
	}
	
}
