package com.crm.comcast.SDET32.practise;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.comcast.SDET32.genericUtility.BaseClass;

@Listeners(com.crm.comcast.SDET32.genericUtility.ListenerImplementationClass.class)
public class ListnerImplementpractice extends BaseClass {
	
	@Test
	public void demo1() {
		System.out.println("demo1");
		Assert.fail();
	}
	@Test
	public void demo2() {
		System.out.println("demo2");
		System.out.println("demo2------2");
	}

}
