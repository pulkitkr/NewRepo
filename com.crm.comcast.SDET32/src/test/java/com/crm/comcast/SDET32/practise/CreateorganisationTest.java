package com.crm.comcast.SDET32.practise;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
/**
 * 
 * @author Pulkit
 *
 */
public class CreateorganisationTest {
	/**
	 * 
	 * @param args
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		//Generic Random
		int random=(int) (Math.random()*1000);
		//Converting the physical representation
		FileInputStream fisForExcel=new FileInputStream("./src/test/resources/ExcelData1.xlsx");
		Workbook workbook = WorkbookFactory.create(fisForExcel);
		String organisationName = workbook.getSheet("Sheet2").getRow(1).getCell(2).getStringCellValue()+random;
		
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
		
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(organisationName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		String actualResult=organisationName;
		String expectedResult=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		
		if(expectedResult.contains(actualResult)) {
			FileInputStream fisupdate=new FileInputStream("./src/test/resources/ExcelData1.xlsx");
			Workbook workbook1 = WorkbookFactory.create(fisupdate);
			Cell cell = workbook.getSheet("Sheet2").getRow(1).createCell(4);
			cell.setCellValue("Pass");
//			Cell cell1 = workbook.getSheet("Sheet2").getRow(1).createCell(2);
//			cell1.setCellValue(organisationName);
			FileOutputStream fos=new FileOutputStream("./src/test/resources/ExcelData1.xlsx");
			workbook.write(fos);
			workbook.close();
		}
		else { 
			FileInputStream fisupdate=new FileInputStream("./src/test/resources/ExcelData1.xlsx");
			Workbook workbook1 = WorkbookFactory.create(fisupdate);
			Cell cell = workbook.getSheet("Sheet2").getRow(1).createCell(4);
			cell.setCellValue("Fail");
			FileOutputStream fos=new FileOutputStream("./src/test/resources/ExcelData1.xlsx");
			workbook.write(fos);
			workbook.close();
			}
		
		Actions a=new Actions(driver);
		a.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		driver.findElement(By.linkText("Sign Out")).click();
	}

}