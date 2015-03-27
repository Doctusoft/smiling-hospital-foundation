package com.doctusoft.smiling.report;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.doctusoft.smiling.BaseInputData;
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
public class ApiReport extends BaseInputData implements Serializable {
	
	private String email;
	private String visitationId;
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
	
	
	public void validateAndNormalize() {
		// TODO Auto-generated method stub
		Preconditions.checkArgument(numberOfChildren != null, "the number of children is mandatory");
		Preconditions.checkArgument(numberOfParents != null, "the number of parents is mandatory");
		contentOfOcupation = checkCollection(contentOfOcupation, "contentOfOcupation");
		opinionOnThePeers = checkCollection(opinionOnThePeers, "opinionOnThePeers");
		problem = checkCollection(problem, "problem");
	}
	
}
