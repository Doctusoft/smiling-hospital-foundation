package com.doctusoft.smiling.report;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.doctusoft.smiling.equipment.Equipment;
import com.google.appengine.repackaged.com.google.common.base.Strings;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ApiReport implements Serializable {

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
	
	
	//TODO: copy-paste alert!
	private Set<String> checkCollection(Collection<String> collection, String name) {
		Set<String> normalizedSet = Sets.newHashSet();
		for (String string : collection) {
			String normalizedString = Strings.nullToEmpty(string).trim();
			if (!Strings.isNullOrEmpty(normalizedString)) {
				normalizedSet.add(normalizedString);
			}
		}

		Preconditions.checkArgument(!normalizedSet.isEmpty(), "you need to gave at least one " + name);
		return normalizedSet;
	}
	//TODO: copy-paste alert!
	private String checkString(String toCheck, String name) {
		toCheck = Strings.nullToEmpty(toCheck).trim();
		Preconditions.checkArgument(!Strings.isNullOrEmpty(toCheck), "the " + name + " can't be blank");
		return toCheck;
	}
}
