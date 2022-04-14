package com.crm.comcast.SDET32.BasicTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
/**
 * This is used to create customer in vtiger
 * @author hp
 *
 */
public class CreateContact {

	public static void main(String[] args) throws IOException {
WebDriver driver=null;
		
		FileInputStream fis=new FileInputStream("./propertyFile.properties");
		Properties p=new Properties();
		p.load(fis);
		
		String BROWSER = p.getProperty("browser");
		String URL = p.getProperty("url");
		String USERNAME = p.getProperty("username");
		String PASSWORD = p.getProperty("password");
		
		if(BROWSER.equals("chrome")){
			driver=new ChromeDriver();
		}
		else if(BROWSER.equals("firefox")){
			driver=new FirefoxDriver();
		}
		else System.out.println("Browser doesn't exist");
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		driver.get(URL);
		
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		driver.findElement(By.name("lastname")).sendKeys("Kumar");
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		String actualResult="Kumar";
		String expectedResult=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		
		if(expectedResult.contains(actualResult)) {
			System.out.println("Pass: The organisation created");
		}
		else { System.out.println("Fail: The organisation is not created successfully");}
		
		Actions a=new Actions(driver);
		a.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		driver.findElement(By.linkText("Sign Out")).click();
	}

}
