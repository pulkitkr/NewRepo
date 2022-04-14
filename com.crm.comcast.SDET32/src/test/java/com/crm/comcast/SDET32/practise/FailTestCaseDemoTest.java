package com.crm.comcast.SDET32.practise;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FailTestCaseDemoTest {
	@Test
	public void failTestCaseDemoTest() {
		System.out.println("====================step1======================");
		Assert.fail();
		System.out.println("====================step2=======================");
	}

}
