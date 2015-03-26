package com.doctusoft.smiling.report;

import java.util.List;

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
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Unindex;

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
    private String numberOfChildren;
	private String numberOfParents;
	private String contentOfOcupation;
	private String customShortage;
	private String opinionOnThePeers;
	private String problem;
	private String solution;
	private String story;
	private List<Equipment> missingEquipments;
	 

	@Builder
	public Report(String department,
			String numberOfChildren,
			String numberOfParents,
			String contentOfOcupation,
			String customShortage,
			String opinionOnThePeers,
			String problem,
			String solution,
			String story,
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
