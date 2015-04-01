package com.doctusoft.smiling;

import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;

public class InputDataPreconditionsTest {

	@Test
	public void testSuccessCheckNotEmptyCollection() {
		Set<String> result = InputDataPreconditions.checkNotEmptyCollection(Lists.newArrayList( "volczi"," volczi"," alm a"," ","" ), "test1");
		Assert.assertEquals(Lists.newArrayList( "alm a","volczi"), Lists.newArrayList(result));
	}

	@Test(expected=IllegalArgumentException.class)
	public void testFailedCheckNotEmptyCollection() {
			InputDataPreconditions.checkNotEmptyCollection(Lists.newArrayList(""), "test2");
	}
	
	@Test
	public void testSuccesscheckNotEmptyString() {
		Assert.assertEquals("volczi", InputDataPreconditions.checkNotEmptyString("volczi", "test3"));
		Assert.assertEquals("volczi", InputDataPreconditions.checkNotEmptyString("  volczi  ", "test3"));
		Assert.assertEquals("vol czi", InputDataPreconditions.checkNotEmptyString(" vol czi ", "test3"));
	}

	@Test(expected=IllegalArgumentException.class)
	public void testFailedcheckNotEmptyString() {
			InputDataPreconditions.checkNotEmptyString("", "test4");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFailedcheckNotEmptyString2() {
			InputDataPreconditions.checkNotEmptyString("  ", "test5");
	}
	
	
}
