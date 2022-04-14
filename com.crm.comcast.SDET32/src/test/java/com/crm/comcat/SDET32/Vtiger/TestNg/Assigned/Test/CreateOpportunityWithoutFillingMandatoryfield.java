package com.crm.comcat.SDET32.Vtiger.TestNg.Assigned.Test;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.comcast.SDET32.genericUtility.BaseClass;
import com.crm.comcast.SDET32.genericUtility.ExcelUtility;
import com.crm.comcast.SDET32.genericUtility.FileUtility;
import com.crm.comcast.SDET32.genericUtility.IPathConstant;
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
//@Listeners(com.crm.comcast.SDET32.genericUtility.ListenerImplementationClass.class)
public class CreateOpportunityWithoutFillingMandatoryfield extends BaseClass{

	/**
	 * This is a testscript to create opportunity using organization without filling mandatory field and verify the opportunity 
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@Test(groups = "regressionTesting")
	public void CreateOpportunityUsingOrganisationWithoutFillingMandatoryfield() throws EncryptedDocumentException, IOException, InterruptedException {

		
		// Generating System Data time
		String calDate = jUtils.getSystemDateInYYY_MM_DDFormat();
		
		//generating randomnumbers
		int value = jUtils.getRandomValue();
				
		//getting data from ExcelSheet and Initializing the organisation name
		String organizationName = eUtils.getStringDataFromExcelSheet(IPathConstant.SHEET_NAME2, 3, 2)+value;
		String firstName = eUtils.getStringDataFromExcelSheet(IPathConstant.SHEET_NAME2, 3, 4)+value;
		String lastName = eUtils.getStringDataFromExcelSheet(IPathConstant.SHEET_NAME2, 3, 5)+value;
		String opportunityName = eUtils.getStringDataFromExcelSheet(IPathConstant.SHEET_NAME2, 3, 3)+value;
		
		//verify loginpage
		String logintTitle=driver.getTitle();
		Assert.assertTrue(logintTitle.contains("vtiger"));
		System.out.println("Pass: Login page display successfully");
		
		//object creation of organization and opportunity module
		LoginPage login = new LoginPage(driver);
		HomePage home=new HomePage(driver);
		OrganizationsPage orgPage = new OrganizationsPage(driver);
		OrganizationInformationPage orgInfo = new OrganizationInformationPage(driver);
		CreateNewOrganizationPage createNewOrg = new CreateNewOrganizationPage(driver);
		OpportunitiesPage oppPage = new OpportunitiesPage(driver);
		CreateNewOpportunityPage createOpp = new CreateNewOpportunityPage(driver);
		OpportunityInformationPage oppInfo = new OpportunityInformationPage(driver);
		
		//verify home page
		String homeTitle = driver.getTitle();
		Assert.assertTrue(homeTitle.contains("Home"));
		System.out.println("Pass: Home page displayed");
		
		//click on organization module
		home.clickOnOrganizationLink();
		
		
		// Clicking on Organization Plus Image
		orgPage.clickOnOrganizationPlusImage();		
		
		// Creating new Organization
		createNewOrg.passValueToOrganizationTextField(organizationName);
		createNewOrg.clickOnSaveButton();
		
		//organisation should be displayed in campagin page
		eUtils.writeValueToExcelSheet(IPathConstant.SHEET_NAME2,3,11,organizationName);
		
		
		// Verification
		String actualTitle = orgInfo.verifyOrganizationNameText();
		Assert.assertTrue(actualTitle.contains(organizationName));
		System.out.println("Pass: Organization has been created");
				
		// Creating new Opportunity
		home.clickOnOpportunitiesLink();
		// verify whether opportunity list page is displaying or not
		String opportunityTitle = driver.getTitle();
		Assert.assertTrue(opportunityTitle.contains("Opportunities "));
		System.out.println("Pass: Opportunity list page displayed");

		oppPage.clickOnOpportunitiesPlusImage();
		// verify whether create new opportunity page is displayed or not
		String opportunityHeader = oppInfo.getCreateOppText();
		Assert.assertTrue(opportunityHeader.contains("Creating New Opportunity"));
		System.out.println("Pass: Create new  Opportunity displayed");


		createOpp.clickOnThePlusButton();
		String parenWindowOpportunity = driver.getTitle();
		wUtils.switchToWindow(driver, parenWindowOpportunity);
		// verify whether organization window is displayed or not
		String moduleName = oppInfo.getOrgWinText();
		Assert.assertTrue(moduleName.contains("Organizations"));
		System.out.println("Pass: Organization window displayed successfully");
		
		createOpp.handleOppChildBrowser(organizationName);
		wUtils.switchToWindow(driver, parenWindowOpportunity);
		String moduleVerfyName = oppInfo.getOrgTextVerify();
		Assert.assertTrue(moduleVerfyName.contains(organizationName));
		System.out.println("Pass: Organization displayed successfully");
		
		// Passing the Date
		createOpp.passValueToTheClosingDate(calDate);
		//verify the close date
		String dateText = driver.findElement(By.name("closingdate")).getText();
		Assert.assertTrue(calDate.contains(dateText));
		System.out.println("Pass: Close date is displayed successfully");
		
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
		Assert.assertTrue(actualOrgName.contains(organizationName));
		System.out.println("Pass: The organization name is matching");
		
		// verify whether opportunity is created or not
		String actualOpportunityName=oppInfo.getOppText();
		Assert.assertTrue(actualOpportunityName.contains(opportunityName));
		System.out.println("Pass: The opportunity " + opportunityName + " has created successfully");
	}
}

