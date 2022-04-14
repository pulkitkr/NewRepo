package com.crm.comcast.SDET32.practise;

import static org.testng.Assert.fail;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.crm.comcast.SDET32.genericUtility.BaseClass;

public class DemoRetryTest extends BaseClass {
	@Test(retryAnalyzer = com.crm.comcast.SDET32.genericUtility.RetryAnalyserImplementationClass.class)
	public void demoTest() {
		System.out.println("=========step1===========");
		Assert.fail();
		System.out.println("=========step2===========");
	}

}
