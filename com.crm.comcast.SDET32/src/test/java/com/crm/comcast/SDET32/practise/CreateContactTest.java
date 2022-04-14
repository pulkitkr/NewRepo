package com.crm.comcast.SDET32.practise;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateContactTest {

		public static void main(String[] args) throws IOException {
			
		
			//Generate Random Contact
			Random random=new Random();
			int value=random.nextInt(1000);
			
			//Read Test Data from Excel File
			FileInputStream fisForExcel=new FileInputStream("./src/test/resources/ExcelData1.xlsx");
			Workbook workbook = WorkbookFactory.create(fisForExcel);
			String LastName = workbook.getSheet("Sheet3").getRow(1).getCell(2).getStringCellValue()+value;
			
				WebDriver driver=null;
					//Read common data from Propeerties file
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
					driver.findElement(By.name("lastname")).sendKeys(LastName);
					driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
					
					String actualResult=LastName;
					String expectedResult=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
					
					if(expectedResult.contains(actualResult)) {
						Cell cell = workbook.getSheet("Sheet3").getRow(1).createCell(4);
						cell.setCellValue("Pass");
						FileOutputStream fos=new FileOutputStream("./src/test/resources/ExcelData1.xlsx");
						workbook.write(fos);
						workbook.close();
						System.out.println("Pass: The organisation created");
					}
					else { 
						Cell cell = workbook.getSheet("Sheet3").getRow(1).createCell(4);
						cell.setCellValue("Fail");
						FileOutputStream fos=new FileOutputStream("./src/test/resources/ExcelData1.xlsx");
						workbook.write(fos);
						workbook.close();
						System.out.println("Fail: The organisation is not created successfully");
						}
					
					Actions a=new Actions(driver);
					a.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
					driver.findElement(By.linkText("Sign Out")).click();
				}

			}

