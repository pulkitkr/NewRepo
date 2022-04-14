package com.crm.comcast.SDET32.practise;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.internal.WebElementToJsonConverter;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Irctc {
//	static {
//		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
//	}
		public static void main(String[] args) throws InterruptedException  {
		WebDriver driver= new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://www.irctc.co.in/");
		driver.manage().window().maximize();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//button[@type='submit'])[1]")).click();
		driver.findElement(By.linkText("LOGIN")).click();
		driver.findElement(By.xpath("(//input[@type='text'])[6]")).sendKeys("Pulkitkr");
		driver.findElement(By.xpath("(//input[@type='password'])")).sendKeys("Pin685");
		Thread.sleep(10000);
		driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//input[@role='searchbox'])[1]")).sendKeys("clg");
		Thread.sleep(3000);
		List<WebElement> from= driver.findElements(By.xpath("(//li[@role='option'])[1]/span"));
		from.get(0).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//input[@role='searchbox'])[2]")).sendKeys("bgp");
		Thread.sleep(2000);
		List<WebElement> to= driver.findElements(By.xpath("(//li[@role='option'])[1]/span"));
		to.get(0).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//input[@type='text'])[3]")).click();
		driver.findElement(By.xpath("//span[@class='ui-datepicker-next-icon pi pi-chevron-right ng-tns-c59-10']")).click();
		
		driver.findElement(By.xpath("(//a[@class='ui-state-default ng-tns-c59-10 ng-star-inserted'])[1]")).click();
		driver.findElement(By.xpath("//span[text()='All Classes']")).click();
		Thread.sleep(2000);
		List<WebElement> allclasses = driver.findElements(By.xpath("//li[@class='ui-dropdown-item ui-corner-all']"));
		int count = allclasses.size();
		allclasses.get(count-2).click(); 
		
//		driver.findElement(By.xpath("//span[text()='GENERAL']")).click();
//		Thread.sleep(2000);
//		List<WebElement> general = driver.findElements(By.xpath("//li[@class='ui-dropdown-item ui-corner-all']"));
//		int count1 = general.size();
//		System.out.println(count1);
//		allclasses.get(count).click(); 
		
		driver.findElement(By.xpath("//button[text()='Search']")).click();
		
		List<WebElement> name = driver.findElements(By.xpath("//strong[contains(text(),'EXP')]"));
		List<WebElement> time = driver.findElements(By.xpath("//span[@class='time']/strong"));
		int c=name.size();
		int t=time.size();
		
		//System.out.println(time);
//		for(int i=0;i<c;i++) 
//		{Thread.sleep(1000);
//			String txt=name.get(i).getText();
//			String txt1=time.get(i).getText();
//            System.out.println(txt +" "+txt1);
//            System.out.println("<======================================>");
//		}
		driver.findElement(By.xpath("//div[text()=' Refresh ']")).click();
		driver.findElement(By.xpath("(//strong[contains(text(),'AVAILABLE')])[1]")).click();
		driver.findElement(By.xpath("(//button[@type='button'])[5]")).click();
//		driver.close();
	}
}
		
		