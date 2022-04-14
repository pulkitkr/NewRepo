package com.crm.comcast.SDET32.pomRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewOrganizationPage {

	WebDriver driver;
	public CreateNewOrganizationPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="accountname")
	private WebElement OrganizationNameTextField;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveButton;
	
	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getOrganizationNameTextField() {
		return OrganizationNameTextField;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}
	
	public void passValueToOrganizationTextField(String value) {
		OrganizationNameTextField.sendKeys(value);
	}
	
	public void clickOnSaveButton() {
		saveButton.click();
	}
}
