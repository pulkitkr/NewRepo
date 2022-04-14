package com.crm.comcast.SDET32.genericUtility;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/**
 * This class contains methods  to fetch the data from property and Json file
 * @author Pulkit
 *
 */
public class FileUtility {
	/**
	 * This method will fetch the data from the property file
	 * @param key
	 * @return
	 * @throws IOException
	 */
		public String getDataFromPropertyFile(String key) throws IOException {
		FileInputStream fisProperty=new FileInputStream(IPathConstant.PROPERTY_FILE_PATH);
		Properties p=new Properties();
		p.load(fisProperty);
		return p.getProperty(key);
		}
		/**
		 * This method will fetch the data from the Json file
		 * @param filePath
		 * @param key
		 * @return
		 * @throws IOException
		 * @throws ParseException
		 */
		public String getDataFromJsonFile(String filePath, String key) throws IOException, ParseException {
			//Create an Object of JSON Parser
			JSONParser parser=new JSONParser();
			//Convert Physical file to Java Object using FileReader
			FileReader file=new FileReader(filePath);
			//Convert json file to java file
			Object obj = parser.parse(file);
			//Downcast the object type of reference to JsonObject type to access the get method
			//By the rule of java we cannot access sub class members from super class reference 
			JSONObject jObj=(JSONObject)obj;
			////Printing the values into the console using the json file keys using get method
			return jObj.get(key).toString();
		}
}
