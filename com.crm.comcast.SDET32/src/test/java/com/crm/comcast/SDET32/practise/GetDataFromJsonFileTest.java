package com.crm.comcast.SDET32.practise;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/**
 * This class is used to fetch data from Json File
 * @author Pulkit
 *
 */
public class GetDataFromJsonFileTest {

	public static void main(String[] args) throws IOException, ParseException {
		//Create an Object of JSON Parser
		JSONParser parser=new JSONParser();
		//Convert Physical file to Java Object using FileReader
		FileReader file=new FileReader("./src/test/resources/JsonFile.json");
		//Convert json file to java file
		Object obj = parser.parse(file);
		//Downcast the object type of reference to JsonObject type to access the get method
		//By the rule of java we cannot access sub class members from super class reference 
		JSONObject jObj=(JSONObject)obj;
		////Printing the values into the console using the json file keys using get method
		System.out.println(jObj.get("browser"));
		System.out.println(jObj.get("url"));
		System.out.println(jObj.get("username"));
		System.out.println(jObj.get("password"));
	}

}
