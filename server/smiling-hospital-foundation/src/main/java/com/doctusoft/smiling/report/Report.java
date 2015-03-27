package com.doctusoft.smiling.report;

import java.util.List;
import java.util.Set;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.doctusoft.smiling.BaseEntity;
import com.doctusoft.smiling.IdGenerator;
import com.doctusoft.smiling.equipment.Equipment;
import com.google.appengine.repackaged.com.google.common.base.MoreObjects;
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
	
	private String department;
    private Integer numberOfChildren;
	private Integer numberOfParents;
	private Set<String> contentOfOcupation;
	private Set<String> customShortage;
	private Set<String> opinionOnThePeers;
	private Set<String> problem;
	private Set<String> solution;
	private Set<String> story;
	private List<Equipment> missingEquipments;
	 

	@Builder
	public Report(String department,
			Integer numberOfChildren,
			Integer numberOfParents,
			Set<String> contentOfOcupation,
			Set<String> customShortage,
			Set<String> opinionOnThePeers,
			Set<String> problem,
			Set<String> solution,
			Set<String> story,
			List<Equipment> missingEquipments) {
		super();
		this.id = MoreObjects.firstNonNull(id, IdGenerator.createId());
		this.department = department;
		this.numberOfChildren = numberOfChildren;
		this.numberOfParents = numberOfParents;
		this.contentOfOcupation = contentOfOcupation;
		this.customShortage = customShortage;
		this.opinionOnThePeers = opinionOnThePeers;
		this.problem = problem;
		this.solution = solution;
		this.story = story;
	}


}
