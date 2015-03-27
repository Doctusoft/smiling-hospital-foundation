package com.doctusoft.smiling;

import java.util.Collection;
import java.util.Set;

import com.google.appengine.repackaged.com.google.common.base.Strings;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;

public class BaseInputData {
	
	public Set<String> checkCollection(Collection<String> collection, String name) {
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
	
	public String checkString(String toCheck, String name) {
		toCheck = Strings.nullToEmpty(toCheck).trim();
		Preconditions.checkArgument(!Strings.isNullOrEmpty(toCheck), "the " + name + " can't be blank");
		return toCheck;
	}

}
