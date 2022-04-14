package com.crm.comcast.SDET32.practise;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SpiceJet {
	
	@Test
	public void demoFlightTest() throws InterruptedException {
		
		String fromDest = "Bengaluru";
		String toDest = "Goa";
		
		LocalDateTime date = LocalDateTime.now().plusDays(5);
//		String month= date.getMonth().toString().charAt(0)+date.getMonth().toString().substring(1).toLowerCase();
		String month = "May";
		String year = "2022";
//		int year = date.getYear();
		int day = date.getDayOfMonth();
		String monthYear = month+" "+year;
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://www.spicejet.com/");
		
		driver.findElement(By.xpath("//input[@dir='auto']")).sendKeys(fromDest);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[text()='To']/following-sibling::div/input")).sendKeys(toDest);
		
//		Thread.sleep(5000);
//		driver.findElement(By.xpath("//div[text()='Departure Date']")).click();
		for(;;) {
			try {
		Thread.sleep(2000);
				driver.findElement(By.xpath("//div[text()='May ' and '2022']/ancestor::div[3]/child::div[1]/child::div[3]/child::div[3]/child::div[2]/div[1]/child::div[text()='10']")).click();
//				driver.findElement(By.xpath("//div[text()='"+month +"' and '"+year+"']/ancestor::div[3]/child::div[1]/child::div[3]/child::div[3]/child::div[2]/div[1]/child::div[text()='" + day + "']")).click();
				break;
			}
			catch (Exception e) {
				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[name()='svg']//*[local-name()='g' and @fill-rule='evenodd']//*[local-name()='circle' and @cy='24.5']")).click();
			}
		}
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[text()='Search Flight']")).click();
	}

}
