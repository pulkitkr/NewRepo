package com.crm.comcast.SDET32.genericUtility;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.crm.comcast.SDET32.pomRepository.HomePage;
import com.crm.comcast.SDET32.pomRepository.LoginPage;
/**
 * This is the base class for all the @test 
 * @author hp
 *
 */
public class BaseClass {
	public WebDriver driver;
	public static WebDriver sDriver;
	
	// creating objects of all libraries
	public JavaUtility jUtils = new JavaUtility();
	public FileUtility fUtils = new FileUtility();
	public ExcelUtility eUtils = new ExcelUtility();
	public WebDriverUtility wUtils = new WebDriverUtility();

	/**
	 * This is used to connnect to database
	 */
	@BeforeSuite(groups = {"smokeTesting","regressionTesting"})
	public void bsConfig() {
		System.out.println("============Database Connection is Established================");
	}

	/**
	 * This is used to launch the browser
	 * @throws IOException
	 */
//	@Parameters("browser")
	@BeforeClass(groups = {"smokeTesting","regressionTesting"})
	public void bcConfig(/*String BROWSER*/) throws IOException {

		// getting data from property file
//		String BROWSER = fUtils.getDataFromPropertyFile(IPathConstant.BROWSER_KEY);
		String BROWSER = System.getProperty("browser");
		String URL = fUtils.getDataFromPropertyFile(IPathConstant.URL_KEY);

		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else
			System.out.println("Browser doesn't exist");
		
		sDriver=driver;
		
		// Maxmize the window
		driver.manage().window().maximize();

		// implicit wait
		wUtils.waitForElement(driver);

		// navigate to vtiger
		driver.get(URL);
		System.out.println("===================Browser has been Launched==================");
	}

	/**
	 * This is used for lofin and navigating to webapplication
	 * @throws IOException
	 */
	@BeforeMethod(groups = {"smokeTesting","regressionTesting"})
	public void bmConfig() throws IOException {
		String USERNAME = fUtils.getDataFromPropertyFile(IPathConstant.USERNAME_KEY);
		String PASSWORD = fUtils.getDataFromPropertyFile(IPathConstant.PASSWORD_KEY);

		LoginPage login = new LoginPage(driver);
		// logging into the application
		login.loginAction(USERNAME, PASSWORD);
		System.out.println("============Successfully logged in to the Application============");
	}

	/**
	 * This is used to logout from application
	 */
	@AfterMethod(groups = {"smokeTesting","regressionTesting"})
	public void amConfig() {
		HomePage home=new HomePage(driver);
		home.logoutAction();
		System.out.println("============Successfully logged out from the Application=========");
	}

	/**
	 * This is used to cloase the browser
	 */
	@AfterClass(groups = {"smokeTesting","regressionTesting"})
	public void acConfig() {
		driver.close();
		System.out.println("===================Browser has been closed==================");
	}

	/**
	 * This is used to close database connection
	 */
	@AfterSuite(groups = {"smokeTesting","regressionTesting"})
	public void asConfig() {
		System.out.println("============Database Connection is Established================");
	}
}
