package com.crm.comcast.SDET32.genericUtility;

public interface IPathConstant {

	int IMPLICIT_WAIT_TIME = 20;
	int EXPLICIT_WAIT_TIME = 20;
	int FLUENT_WAIT_TIME = 20;
	int POLLING_WAIT_TIME = 20;
	
	String PROPERTY_FILE_PATH = "./src/test/resources/propertyFile.properties";
	String EXCEL_FILE_PATH = "./src/test/resources/ExcelData1.xlsx";
	String JSON_FILE_PATH = "./src/test/resources/JsonFile.json";
	
	String USERNAME_KEY = "username";
	String PASSWORD_KEY = "password";
	String URL_KEY = "url";
	String BROWSER_KEY = "browser";
	
	String SHEET_NAME1 = "Sheet1";
	String SHEET_NAME2 = "Sheet2";
	
	String CHROME_BROWSER_KEY = "webdriver.chrome.driver";
	String CHROME_BROWSER_VALUE = "./chromedriver.exe";
	
	String DATABASE_URL = "jdbc:mysql://localhost:3306/student";
	String DATABASE_USERNAME = "root";
	String DATABASE_PASSWSORD = "root";

}
