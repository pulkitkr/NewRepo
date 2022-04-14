package com.crm.comcast.SDET32.practise;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
/**
 * This class is used to get the data from first and second column
 * @author Pulkit
 *
 */
public class SimpleScriptToGetTheDataFromTwoColumns {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		// TODO Auto-generated method stub
		//converting physical representation of the file into the java representation
		FileInputStream fis =new FileInputStream("./src/test/resources/ExcelData1.xlsx");
		//converting the physical representation workbook into  java representation
		Workbook workbook = WorkbookFactory.create(fis);
		//Getting the control of the sheet
		Sheet sheet = workbook.getSheet("Sheet1");
		int rowCount = sheet.getLastRowNum();
		for(int i=0;i<=rowCount;i++) {
			//Getting the control of the row
			Row row = sheet.getRow(i);
			//getting the first control of Column and Converting the cell type of object into String Value
		String firstColData = row.getCell(0).getStringCellValue();
			//Getting the control of second column and Converting the cell type of object into String Value

			String secondColData = row.getCell(1).getStringCellValue();
			//Printing the first column data with second column data
			System.out.println(firstColData+"\t"+secondColData);

			
		}

	}

}