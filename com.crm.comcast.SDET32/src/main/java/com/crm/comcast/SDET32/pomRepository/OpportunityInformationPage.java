package com.crm.comcast.SDET32.pomRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OpportunityInformationPage {
	
	WebDriver driver;
	public OpportunityInformationPage(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className = "dvHeaderText")
	private WebElement actualOpportunityName;
	
	@FindBy(className="lvtHeaderText")
	private WebElement actualCreateOpportunityName;
	
	@FindBy(xpath="//a[@title='Organizations']")
	private WebElement actualOrgNameMatch;
	
	@FindBy(xpath="//td[@class='moduleName']")
	private WebElement actualOrgWinText;
	
	@FindBy(xpath = "//input[@type='text' and@ name='related_to_display'] ")
	private WebElement verifyOrgNameText;
	
	public WebElement getActualOrgNameMatch() {
		return actualOrgNameMatch;
	}

	public WebElement getActualOrgWinText() {
		return actualOrgWinText;
	}

	public WebElement getActualCreateOpportunityName() {
		return actualCreateOpportunityName;
	}

	public WebElement getVerifyOrgNameText() {
		return verifyOrgNameText;
	}

	public WebElement getActualOrgWinMatch() {
		return actualOrgNameMatch;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getActualOpportunityName() {
		return actualOpportunityName;
	}
	
	public String getOppText() {
		return actualOpportunityName.getText();
	}
	
	public String getCreateOppText() {
		return actualCreateOpportunityName.getText();
	}
	
	public String getOrgText() {
		return actualOrgNameMatch.getText();
	}
	
	public String getOrgWinText() {
		return actualOrgWinText.getText();
	}
	
	public String getOrgTextVerify() {
		return verifyOrgNameText.getAttribute("value");
	}
}
