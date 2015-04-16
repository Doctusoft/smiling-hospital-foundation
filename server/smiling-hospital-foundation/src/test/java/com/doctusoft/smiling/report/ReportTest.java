package com.doctusoft.smiling.report;

import org.junit.Assert;

import org.junit.Test;

public class ReportTest {

	@Test
	public void testGenerateId_validEmail() {
		Assert.assertEquals("tester@test.orgvisitationId", Report.generateId(" tester@test.org ", "visitationId"));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGenerateId_invalidEmail() {
		Report.generateId("invalidemail", " visitationId");
	}

}
