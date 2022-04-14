package com.crm.comcast.SDET32.practise;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.Test;

import com.crm.comcast.SDET32.genericUtility.BaseClass;
import com.google.common.io.Files;


public class ScreenShotTest extends BaseClass {

	@Test
	public void demoTest(Method mtd) throws IOException {
		String time = jUtils.getSystemDateAndTime().replace(" ", "_").replace(":", "_");
		String testCaseName = mtd.getName()+time;
		EventFiringWebDriver eDriver=new EventFiringWebDriver(driver);
		File src = eDriver.getScreenshotAs(OutputType.FILE);
		File dest=new File("./errorShots/"+testCaseName+".png");
		Files.copy(src, dest);
	}
}
