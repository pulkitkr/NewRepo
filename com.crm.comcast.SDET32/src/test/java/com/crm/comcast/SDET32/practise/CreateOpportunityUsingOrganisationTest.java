package com.crm.comcast.SDET32.practise;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class CreateOpportunityUsingOrganisationTest {

	public static void main(String[] args) throws IOException {
		WebDriver driver=null;
		Random random = new Random();
		int value=random.nextInt(1000);
		
		FileInputStream fisForExcel=new FileInputStream("./src/test/resources/ExcelData1.xlsx");
		Workbook workbook = WorkbookFactory.create(fisForExcel);
		String organisationName = workbook.getSheet("Sheet2").getRow(3).getCell(2).getStringCellValue()+value;
		String firstName = workbook.getSheet("Sheet2").getRow(3).getCell(4).getStringCellValue()+value;
		String lastName = workbook.getSheet("Sheet2").getRow(3).getCell(5).getStringCellValue()+value;
		String opportunityName = workbook.getSheet("Sheet2").getRow(3).getCell(3).getStringCellValue()+value;
		
		FileInputStream fisProperty=new FileInputStream("./src/test/resources/propertyFile.properties");
		Properties p=new Properties();
		p.load(fisProperty);
		
		String BROWSER = p.getProperty("browser");
		String URL = p.getProperty("url");
		String USERNAME = p.getProperty("username");
		String PASSWORD = p.getProperty("password");
		
		if(BROWSER.equals("chrome")) {
			driver=new ChromeDriver();
		}
			else System.out.println("Browser doesn't exist");
			
			driver.manage().window().maximize();
			
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			
			driver.get(URL);
			
			driver.findElement(By.name("user_name")).sendKeys(USERNAME);
			driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
			driver.findElement(By.id("submitButton")).click();
			
			driver.findElement(By.linkText("Organizations")).click();
			driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
			
			driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(organisationName);
			driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
			
			String actualResult=organisationName;
			String expectedResult=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
			
			if(expectedResult.contains(actualResult)) {
				System.out.println("PASS: The Organisation is created successfully");
			}
			else System.out.println("FAIL: The Organisation is not created successfully");
			
			driver.findElement(By.linkText("Contacts")).click();
			driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
			
			
			Select s=new Select(driver.findElement(By.name("salutationtype")));
			s.selectByVisibleText("Mr.");
			
			driver.findElement(By.name("firstname")).sendKeys(firstName);
			driver.findElement(By.name("lastname")).sendKeys(lastName);
			
			driver.findElement(By.xpath("//td[text()='Organization Name 			']/parent::tr/descendant::img[@src='themes/softed/images/select.gif']")).click();
//			
			String parentWindow = driver.getWindowHandle();
			Set<String> childWindows = driver.getWindowHandles();
			for(String wb:childWindows) {
				driver.switchTo().window(wb);
				if(!parentWindow.equals(wb)) {
					driver.findElement(By.id("search_txt")).sendKeys(organisationName+Keys.ENTER);
//					driver.findElement(By.id("search_txt")).sendKeys(organisationName);
//					driver.findElement(By.name("search")).click();
					driver.findElement(By.linkText(""+organisationName+"")).click();
					break;
				}
			}
				driver.switchTo().window(parentWindow);
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
				
				String actualContactName=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				
				if(actualContactName.contains(lastName)) {
					System.out.println("Pass: The contact has created");
				}
				else System.out.println("Fail: The contact has not been created");
				
				
				driver.findElement(By.linkText("Opportunities")).click();
				driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
				driver.findElement(By.name("potentialname")).sendKeys(opportunityName);
				
				Select contactOpportunity=new Select(driver.findElement(By.id("related_to_type")));
				contactOpportunity.selectByVisibleText("Contacts");
	}

}
