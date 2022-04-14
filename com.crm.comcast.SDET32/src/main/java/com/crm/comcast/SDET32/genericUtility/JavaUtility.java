package com.crm.comcast.SDET32.genericUtility;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.Random;
/**
 * This method is used for as a generic 
 * @author Pulkit
 *
 */
public class JavaUtility {
	/**
	 * This method is used to get random values
	 * @return
	 */
	public int getRandomValue() {
		Random random=new Random();
		 return random.nextInt(10000);
	}
	/**
	 * This method is used to get date and time
	 * @return
	 */
	public String getSystemDateAndTime() {
		Date date = new Date();
		return date.toString();
	}
	/**
	 * This method is used to get the date in YYYY-MM-DD format
	 * @return
	 */
	public String getSystemDateInYYY_MM_DDFormat() {
		Date date = new Date();
		String YYYY=date.toString().split(" ")[5];
		int month = date.getMonth()+1;
		int day = date.getDate();
		return YYYY+"-"+month+"-"+day;
	}
	/**
	 * This method is usd to do keyboard simulation to press the Enter button
	 * @throws AWTException
	 */
	public void pressEnterButton() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

}
