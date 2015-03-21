package com.doctusoft.smiling;

import java.util.concurrent.ThreadLocalRandom;

import com.google.common.base.Strings;

public class IdGenerator {
	
	public static String createId() {
		String time = Strings.padStart(Long.toHexString(System.currentTimeMillis()), 11, '0');
		String random = Strings.padStart(Long.toHexString(ThreadLocalRandom.current().nextLong()), 16, '0');
		return time + random;
	}
	
}
