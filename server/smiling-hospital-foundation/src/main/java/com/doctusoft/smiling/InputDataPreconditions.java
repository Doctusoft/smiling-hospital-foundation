package com.doctusoft.smiling;

import java.util.Set;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Sets;

public class InputDataPreconditions  {
	
	public static Set<String> checkNotEmptyCollection(Iterable<String> Iterable, String parameterName) {
		Set<String> normalizedSet = Sets.newHashSet();
		for (String string : Iterable) {
			String normalizedString = Strings.nullToEmpty(string).trim();
			if (!Strings.isNullOrEmpty(normalizedString)) {
				normalizedSet.add(normalizedString);
			}
		}

		Preconditions.checkArgument(!normalizedSet.isEmpty(), "you have to give at least one " + parameterName);
		return normalizedSet;
	}
	
	public static String checkNotEmptyString(String toCheck, String parameterNameme) {
		toCheck = Strings.nullToEmpty(toCheck).trim();
		Preconditions.checkArgument(!Strings.isNullOrEmpty(toCheck), "the parameter " + parameterNameme + " can't be blank");
		return toCheck;
	}

}
