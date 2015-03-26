package com.doctusoft.smiling.report;

import java.io.Serializable;
import java.util.List;

import com.doctusoft.smiling.equipment.Equipment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ApiReport implements Serializable {

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
}
