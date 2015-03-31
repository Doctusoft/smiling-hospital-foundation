package com.doctusoft.smiling.report;

import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import com.doctusoft.smiling.Constants;
import com.doctusoft.smiling.InputDataPreconditions;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.doctusoft.smiling.BaseEntity;
import com.doctusoft.smiling.equipment.Equipment;
import com.google.appengine.api.search.checkers.Preconditions;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@NoArgsConstructor
@Data
@Cache
@Entity
@EqualsAndHashCode(callSuper=false, of={"id"})
public class Report extends BaseEntity {

	public static interface IndexedProperties extends BaseEntity.IndexedProperties {
	}

	@Id
	private String id;	
	
	private String email;	
	private String department;
    private int numberOfChildren;
	private int numberOfParents;
	private Set<String> contentOfOccupation;
	private Set<String> customShortage;
	private Set<String> opinionOnThePeers;
	private Set<String> problem;
	private Set<String> solution;
	private Set<String> story;
	private List<Equipment> missingEquipments;
	 

	@Builder
	public Report(String email,
			String visitationId,
			String department,
			int numberOfChildren,
			int numberOfParents,
			Set<String> contentOfOccupation,
			Set<String> customShortage,
			Set<String> opinionOnThePeers,
			Set<String> problem,
			Set<String> solution,
			Set<String> story,
			List<Equipment> missingEquipments) {
		super();
		this.id = generateId(email, visitationId);
		this.email = email;
		this.department = department;
		this.numberOfChildren = numberOfChildren;
		this.numberOfParents = numberOfParents;
		this.contentOfOccupation = contentOfOccupation;
		this.customShortage = customShortage;
		this.opinionOnThePeers = opinionOnThePeers;
		this.problem = problem;
		this.solution = solution;
		this.story = story;
		this.missingEquipments =missingEquipments;
	}
	
	public static String generateId(String email, String visitationId) {
		email = InputDataPreconditions.checkNotEmptyString(email, "e-mail is empty");
		visitationId = InputDataPreconditions.checkNotEmptyString(visitationId, "visitationId is empty");
		Preconditions.checkArgument(Pattern.compile(Constants.RFC_EMAIL).matcher(email).matches(), "email is not valid");
		return email.concat(visitationId);
	}
	

}
