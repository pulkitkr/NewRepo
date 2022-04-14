package com.crm.comcat.SDET32.Vtiger.TestNg.Assigned.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
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
 *  This class is used to create opportunity and click on cancel button to display popup
 * @author hp
 *
 */
public class CreateOpportunitybyClickOnClear extends BaseClass{

	/**
	 * This is a testscript to create opportunity using organization and click on clear then verify the opportunity name
	 * @throws InterruptedException
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	@Test(groups = "regressionTesting")
	public void CreateOpportunityUsingOrganisationAndClickOnClear() throws InterruptedException, EncryptedDocumentException, IOException {
		

		// generating randomnumbers
		int value = jUtils.getRandomValue();
		
		// Generating System Data time
		String calDate = jUtils.getSystemDateInYYY_MM_DDFormat();

		// getting data from ExcelSheet and Initializing the organisation name
		String organizationName = eUtils.getStringDataFromExcelSheet(IPathConstant.SHEET_NAME2, 3, 2) + value;
		String firstName = eUtils.getStringDataFromExcelSheet(IPathConstant.SHEET_NAME2, 3, 4) + value;
		String lastName = eUtils.getStringDataFromExcelSheet(IPathConstant.SHEET_NAME2, 3, 5) + value;
		String opportunityName = eUtils.getStringDataFromExcelSheet(IPathConstant.SHEET_NAME2, 3, 3) + value;

		//verify the login page
		String logintTitle=driver.getTitle();
		Assert.assertTrue(logintTitle.contains("vtiger"));
		System.out.println("Pass: Login page display successfully");
		
		//object creation  of organization and opportunities module
		LoginPage login = new LoginPage(driver);
		HomePage home=new HomePage(driver);
		OrganizationsPage orgPage = new OrganizationsPage(driver);
		OrganizationInformationPage orgInfo = new OrganizationInformationPage(driver);
		CreateNewOrganizationPage createNewOrg = new CreateNewOrganizationPage(driver);
		OpportunitiesPage oppPage = new OpportunitiesPage(driver);
		CreateNewOpportunityPage createOpp = new CreateNewOpportunityPage(driver);
		OpportunityInformationPage oppInfo = new OpportunityInformationPage(driver);
		
		
		//verify homepage
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
		
		//Enter the opportunity name
		createOpp.sendValuesToOpportunityName(opportunityName);
		
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

		//click on cancel button
		createOpp.clickOncancelButton();
		
		// verify the organization name in field
		String actualOrgName = oppInfo.getOrgText();
		Assert.assertTrue(!actualOrgName.contains(organizationName));
		System.out.println("Pass: The organization name is not matching ");
		
		oppPage.clickOnOpportunitiesPlusImage();
		// verify whether create new opportunity page is displayed or not
		String opportunityHeader1 = oppInfo.getCreateOppText();
		Assert.assertTrue(opportunityHeader1.contains("Creating New Opportunity"));
		System.out.println("Pass: Create new  Opportunity displayed");

		//Enter the opportunity name
		createOpp.sendValuesToOpportunityName(opportunityName);
		
		createOpp.clickOnThePlusButton();
		String parenWindowOpportunity1 = driver.getTitle();
		wUtils.switchToWindow(driver, parenWindowOpportunity);
		// verify whether organization window is displayed or not
		String moduleName1 = oppInfo.getOrgWinText();
		Assert.assertTrue(moduleName1.contains(moduleName1));
		System.out.println("Pass: Organization window displayed successfully");
		
		createOpp.handleOppChildBrowser(organizationName);
		wUtils.switchToWindow(driver, parenWindowOpportunity);
		String moduleVerfyName1 = oppInfo.getOrgTextVerify();
		Assert.assertTrue(moduleVerfyName1.contains(organizationName));
		System.out.println("Pass: Organization displayed successfully");

		// Passing the Date
		createOpp.passValueToTheClosingDate(calDate);
		//verify the close date
		String datematch = driver.findElement(By.name("closingdate")).getText();
		Assert.assertTrue(calDate.contains(datematch));
		System.out.println("Pass: Close date is displayed successfully");
		
		//select the group radio button
		createOpp.clickOnTheGroupRadioButton();
		
		//Select the particular element from dropdown
		createOpp.handleAssignToDropdown("Team Selling");
		
		//Select the particular element from dropdown of SalesStage
		createOpp.handleSalesStageDropdown("Closed Lost");
		
		//click on save button
		createOpp.clickOnSaveButton();

		// verify the organization name in field
		String actualOrgName1 = oppInfo.getOrgText();
		Assert.assertTrue(actualOrgName1.contains(organizationName));
		System.out.println("Pass: The organization name is matching");
		
		
		// verify whether opportunity is created or not
		String actualOpportunityName = oppInfo.getOppText();
		Assert.assertTrue(actualOpportunityName.contains(opportunityName));
		System.out.println("Pass: The opportunity " + opportunityName + " has created successfully");
		
	}
}