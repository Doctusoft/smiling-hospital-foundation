package com.doctusoft.smiling.equipment;

import org.junit.Assert;
import org.junit.Test;

public final class TestEquipment {
	
	Equipment equipment = new Equipment("test_name");

	
	@Test
	public void testTest(){
		Assert.assertEquals("test_name", equipment.getName());
	}
}
