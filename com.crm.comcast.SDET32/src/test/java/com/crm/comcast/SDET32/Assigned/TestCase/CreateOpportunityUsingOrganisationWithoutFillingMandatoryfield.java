package com.crm.comcast.SDET32.Assigned.TestCase;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.crm.comcast.SDET32.genericUtility.ExcelUtility;
import com.crm.comcast.SDET32.genericUtility.FileUtility;
import com.crm.comcast.SDET32.genericUtility.JavaUtility;
import com.crm.comcast.SDET32.genericUtility.WebDriverUtility;
import com.crm.comcast.SDET32.pomRepository.CreateNewOpportunityPage;
import com.crm.comcast.SDET32.pomRepository.CreateNewOrganizationPage;
import com.crm.comcast.SDET32.pomRepository.HomePage;
import com.crm.comcast.SDET32.pomRepository.LoginPage;
import com.crm.comcast.SDET32.pomRepository.OpportunitiesPage;
import com.crm.comcast.SDET32.pomRepository.OpportunityInformationPage;
import com.crm.comcast.SDET32.pomRepository.OrganizationInformationPage;
import com.crm.comcast.SDET32.pomRepository.OrganizationsPage;

/**
 * This class is used to create opportunity without filling all mandatory field
 * @author Pulkit
 *
 */
public class CreateOpportunityUsingOrganisationWithoutFillingMandatoryfield {

	public static void main(String[] args) throws EncryptedDocumentException, IOException, InterruptedException {
		WebDriver driver=null;
		
		//creating objects of all libraries
		JavaUtility jUtils = new JavaUtility();
		FileUtility fUtils=new FileUtility();
		ExcelUtility eUtils=new ExcelUtility();
		WebDriverUtility wUtils = new WebDriverUtility();
		
		// Generating System Data time
		String calDate = jUtils.getSystemDateInYYY_MM_DDFormat();
		
		//generating randomnumbers
		int value = jUtils.getRandomValue();
				
		//getting data from ExcelSheet and Initializing the organisation name
		String organizationName = eUtils.getStringDataFromExcelSheet("Sheet2", 3, 2)+value;
		String firstName = eUtils.getStringDataFromExcelSheet("Sheet2", 3, 4)+value;
		String lastName = eUtils.getStringDataFromExcelSheet("Sheet2", 3, 5)+value;
		String opportunityName = eUtils.getStringDataFromExcelSheet("Sheet2", 3, 3)+value;
		
		//getting data from property file
		String BROWSER = fUtils.getDataFromPropertyFile("browser");
		String URL = fUtils.getDataFromPropertyFile("url");
		String USERNAME = fUtils.getDataFromPropertyFile("username");
		String PASSWORD = fUtils.getDataFromPropertyFile("password");
		
		
		if(BROWSER.equals("chrome")) {
			driver=new ChromeDriver();
		}
		else if(BROWSER.equals("firefox")) {
			driver=new FirefoxDriver();
		}
		else System.out.println("Browser doesn't exist");
		
		//Maxmize the window	
		driver.manage().window().maximize();
		
		//implicit wait
		wUtils.waitForElement(driver);
			
		//navigate to vtiger
		driver.get(URL);
		String logintTitle=driver.getTitle();
		if(logintTitle.contains("vtiger")) {
			System.out.println("Pass: Login page display successfully");
		}
		else {
			System.out.println("Fail: Login page not displayed");
		}
		
	
		LoginPage login = new LoginPage(driver);
		HomePage home=new HomePage(driver);
		OrganizationsPage orgPage = new OrganizationsPage(driver);
		OrganizationInformationPage orgInfo = new OrganizationInformationPage(driver);
		CreateNewOrganizationPage createNewOrg = new CreateNewOrganizationPage(driver);
		OpportunitiesPage oppPage = new OpportunitiesPage(driver);
		CreateNewOpportunityPage createOpp = new CreateNewOpportunityPage(driver);
		OpportunityInformationPage oppInfo = new OpportunityInformationPage(driver);
		
		//logging into the application
		login.loginAction(USERNAME, PASSWORD);
		
		String homeTitle = driver.getTitle();
		if(homeTitle.contains("Home")) {
			System.out.println("Pass: Home page displayed");
		}
		else {
			System.out.println("Fail: Home page not displayed");
		}
		
		
		//click on organization module
		home.clickOnOrganizationLink();
		
		
		// Clicking on Organization Plus Image
		orgPage.clickOnOrganizationPlusImage();		
		
		// Creating new Organization
		createNewOrg.passValueToOrganizationTextField(organizationName);
		createNewOrg.clickOnSaveButton();
		
		//organisation should be displayed in campagin page
		eUtils.writeValueToExcelSheet("Sheet2",3,11,organizationName);
		
		
		// Verification
		String actualTitle = orgInfo.verifyOrganizationNameText();

		if (actualTitle.contains(organizationName)) {
			System.out.println("Pass: Organization has been created");
		} else
			System.out.println("Fail: Organization has not been created");
				
		
		// Creating new Opportunity
		home.clickOnOpportunitiesLink();
		// verify whether opportunity list page is displaying or not
		String opportunityTitle = driver.getTitle();
		if (opportunityTitle.contains("Opportunities ")) {
			System.out.println("Pass: Opportunity list page displayed");
		} else {
			System.out.println("Fail: Opportunity list page not displayed");
		}

		oppPage.clickOnOpportunitiesPlusImage();
		// verify whether create new opportunity page is displayed or not
		String opportunityHeader = oppInfo.getCreateOppText();
		if (opportunityHeader.contains("Creating New Opportunity")) {
			System.out.println("Pass: Create new  Opportunity displayed");
		} else {
			System.out.println("Fail: Create new Opportunity not displayed");
		}


		createOpp.clickOnThePlusButton();
		String parenWindowOpportunity = driver.getTitle();
		wUtils.switchToWindow(driver, parenWindowOpportunity);
		// verify whether organization window is displayed or not
		String moduleName = oppInfo.getOrgWinText();
		if (moduleName.contains("Organizations")) {
			System.out.println("Pass: Organization window displayed successfully");

		} else {
			System.out.println("Fail: Organization window not displayed successfully");

		}
		createOpp.handleOppChildBrowser(organizationName);
		wUtils.switchToWindow(driver, parenWindowOpportunity);
		String moduleVerfyName = oppInfo.getOrgTextVerify();
		if (moduleVerfyName.contains(organizationName)) {
			System.out.println("Pass: Organization displayed successfully");

		} else {
			System.out.println("Fail: Organization not displayed successfully");

		}
		
		
		
		// Passing the Date
		createOpp.passValueToTheClosingDate(calDate);
		//verify the close date
		if (calDate.contains(driver.findElement(By.name("closingdate")).getText())) {
			System.out.println("Pass: Close date is displayed successfully");
		} else {
			System.out.println("Fail: Close date is not displayed successfully");
		}
		
		//select the group radio button
		createOpp.clickOnTheGroupRadioButton();
		
		//Select the particular element from dropdown
		createOpp.handleAssignToDropdown("Team Selling");
		
		//Select the particular element from dropdown of SalesStage
		createOpp.handleSalesStageDropdown("Closed Lost");
		

		//click on save button
		createOpp.clickOnSaveButton();


		Thread.sleep(2000);
		//popup will display
		wUtils.textAlertPopup(driver);
		
		//accept popup
		wUtils.acceptAlertPopup(driver);
		
		//Enter the opportunity name
		createOpp.sendValuesToOpportunityName(opportunityName);

		
		//click on save button
		createOpp.clickOnSaveButton();
		
		// verify the organization name in field
		String actualOrgName = oppInfo.getOrgText();
		if (actualOrgName.equals(organizationName)) {
			System.out.println("Pass: The organization name is matching");
		} else {
			System.out.println("Fail: Organization name is not matching");
		}
		
		// verify whether opportunity is created or not
		String actualOpportunityName=oppInfo.getOppText();
		if (actualOpportunityName.contains(opportunityName)) {
			System.out.println("Pass: The opportunity " + opportunityName + " has created successfully");
		} else
			System.out.println("Fail: The opportunity has not been created");
		
		//logout
		home.logoutAction();
	}

}

