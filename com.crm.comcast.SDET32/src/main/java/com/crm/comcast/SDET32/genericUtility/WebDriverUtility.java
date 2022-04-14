package com.crm.comcast.SDET32.genericUtility;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;
/**
 * This class contains all the web driver specific resuable method
 * @author hp
 *
 */
public class WebDriverUtility {
	/**
	 * This method is used to wait for the element to load on the webpage
	 * @param driver
	 */
	public void waitForElement(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(IPathConstant.IMPLICIT_WAIT_TIME, TimeUnit.SECONDS);
	}
	/**
	 * This method is used to wait for the element to be clickable
	 * @param driver
	 * @param element
	 */
	public void waitForElementToBeClickable(WebDriver driver,WebElement element) {
		WebDriverWait wait=new WebDriverWait(driver, IPathConstant.EXPLICIT_WAIT_TIME);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	/**
	 * This method will wait for the element to be clickable for specific polling period
	 * @param driver
	 * @param element
	 * @param pollingPeriod
	 * @throws InterruptedException
	 */
	public void waitForTheElementToBeClickableForSpecificTime(WebDriver driver, WebElement element, int pollingPeriod) throws InterruptedException {
		FluentWait wait = new FluentWait(driver);
		wait.pollingEvery(pollingPeriod, TimeUnit.SECONDS);
		wait.wait(IPathConstant.FLUENT_WAIT_TIME);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	/**
	 * This method will wait for the element to be clickable
	 * @param element
	 * @throws InterruptedException
	 */
	public void waitForElement(WebElement element) throws InterruptedException {
		int count=0;
		while(count<20) {
			try {
				element.click();
				break;
			} catch (Exception e) {
				Thread.sleep(10000);
			}
		}
	}
	/**
	 * This method will switch between windows
	 * @param driver
	 * @param parentWindowTitle
	 */
	public void switchToWindow(WebDriver driver,String parentWindowTitle) {
			Set<String> set = driver.getWindowHandles();
			Iterator<String> it = set.iterator();
			while(it.hasNext()) {
				String winID = it.next();
				driver.switchTo().window(winID);
				String currentWindowTitle = driver.getTitle();
				if(!currentWindowTitle.contains(parentWindowTitle)) {
					break;
				}
			}
	}
	/**
	 * This method will accept alert popup
	 * @param driver
	 */
	public void acceptAlertPopup(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	/**
	 * This method will dismiss alert popup
	 * @param driver
	 */
	public void dismissAlertPopup(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	/**
	 * This method will gettext from alert popup
	 * @param driver
	 */
	public void textAlertPopup(WebDriver driver) {
		String popupMsg = driver.switchTo().alert().getText();
		if(popupMsg.contains("Opportunity Name cannot be empty")) {
			System.out.println("Pass: All field are mandatory");
		}
	}
	/**
	 * This method handle Dropdown using visible Text
	 * @param element
	 * @param visibleText
	 */
	public void handleDropDown(WebElement element, String visibleText) {
		Select select = new Select(element);
		select.selectByVisibleText(visibleText);
	}
	/**
	 * This method handle Dropdown using index number
	 * @param element
	 * @param visibleText
	 */
	public void handleDropDown(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
	}
	/**
	 * This method handle Dropdown using value
	 * @param element
	 * @param visibleText
	 */
	public void handleDropDown(String value,WebElement element) {
		Select select = new Select(element);
		select.selectByValue(value);
	}
	/**
	 * This method will do move hover action
	 * @param driver
	 * @param element
	 */
	public void moveToElement(WebDriver driver, WebElement element) {
		Actions action=new Actions(driver);
		action.moveToElement(element).perform();
	}
	/**
	 * This method will perform right click action
	 * @param driver
	 */
	public void rightClickAction(WebDriver driver) {
		Actions action=new Actions(driver);
		action.contextClick().perform();
	}
	/**
	 * This method will perform right click action on a particular element
	 * @param driver
	 */
	public void rightClickAction(WebDriver driver,WebElement element) {
		Actions action=new Actions(driver);
		action.contextClick(element).perform();
	}
	/**
	 * This method will handle asyn script
	 * @param driver
	 * @param javaScriptCode
	 */
	public void handleAsyncScript(WebDriver driver, String javaScriptCode) {
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeAsyncScript(javaScriptCode, null);
	}
	/**
	 * This method will take a screen shot of a particular webpage
	 * @param driver
	 * @param FileName
	 * @throws IOException
	 */
	public void takeScreenShot(WebDriver driver,String FileName) throws IOException {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest=new File("./errorShots/"+FileName+".png");
		Files.copy(src, dest);
	}
	/**
	 * This method willswitchto a frame using index number
	 * @param driver
	 * @param index
	 */
	public void switchToFrame(WebDriver driver,int index) {
		driver.switchTo().frame(index);
	}
	/**
	 * This method willswitchto a frame using value
	 * @param driver
	 * @param index
	 */
	public void switchToFrame(WebDriver driver,String value) {
		driver.switchTo().frame(value);
	}
	/**
	 * This method willswitchto a frame using a particular WebElement
	 * @param driver
	 * @param index
	 */
	public void switchToFrame(WebDriver driver,WebElement element) {
		driver.switchTo().frame(element);
	}
}
