package com.doctusoft.smiling.report;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.doctusoft.smiling.InputDataPreconditions;
import com.doctusoft.smiling.equipment.Equipment;
import com.google.common.base.Preconditions;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ApiReport implements Serializable {
	
	private String visitationId;
	private String department;
    private Integer numberOfChildren;
	private Integer numberOfParents;
	private Set<String> contentOfOccupation;
	private Set<String> customShortage;
	private Set<String> opinionOnThePeers;
	private Set<String> problem;
	private Set<String> solution;
	private Set<String> story;
	private List<Equipment> missingEquipments;
	
	
	public void validateAndNormalize() {
		Preconditions.checkNotNull(numberOfChildren, "the number of children is mandatory");
		contentOfOccupation = 	InputDataPreconditions.checkNotEmptyCollection(contentOfOccupation, "contentOfOccupation");
		opinionOnThePeers = 	InputDataPreconditions.checkNotEmptyCollection(opinionOnThePeers, "opinionOnThePeers");
		problem = 				InputDataPreconditions.checkNotEmptyCollection(problem, "problem");
	}
	
}
