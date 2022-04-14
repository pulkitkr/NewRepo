package com.crm.comcast.SDET32.pomRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

/**
 * This is the POM Class for Login Page
 * @author Akash
 *
 */
public class LoginPage {

	WebDriver driver;
	//Constructor for LoginPage
	public LoginPage(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	//Find By annotation to identify the webElement
	@FindBy (name = "user_name")
	private WebElement usernameEdit;
	
//	@FindAll ({@FindBy(name="username"),@FindBy (name="user_name")})
//	private WebElement usernameEdit;
	
	@FindBy (name  = "user_password")
	private WebElement passwordEdit;
	
//	@FindAll ({@FindBy(name  = "user_password"), @FindBy(xpath = "//input[@type='user_password']")})
//	private WebElement passwordEdit;
	
	@FindBy (id = "submitButton")
	private WebElement loginButton;
	
	//Getter method to access the private members
	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getUsername() {
		return usernameEdit;
	}

	public WebElement getPassword() {
		return passwordEdit;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}
	
	/**
	 * This method is used to do the Login Action on Vtiger
	 * @param username
	 * @param password
	 */
	//Business Logics
	public void loginAction(String username, String password) {
		usernameEdit.sendKeys(username);
		passwordEdit.sendKeys(password);
		loginButton.click();
	}
	
	

}
