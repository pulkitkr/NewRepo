package com.crm.comcast.SDET32.practise;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
/**
 * This class is used to get the data from the Excel sheet
 * @author 	Pulkit
 *
 */
public class SimpleScriptToGetDataFromExcelSheet {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
	
	//converting physical representation of the file into the java representation
	FileInputStream fis =new FileInputStream("./src/test/resources/ExcelData1.xlsx");
	//converting the physical representation workbook into  java representation
	Workbook workbook = WorkbookFactory.create(fis);
	//Getting the control of the sheet
	Sheet sheet = workbook.getSheet("Sheet1");
	//Getting the control of the Row
	Row row = sheet.getRow(2);
	//Getting the control of the cell
	Cell cell = row.getCell(0);
	//Converting the cell type of object into String Value
	String data = cell.getStringCellValue();
	//Printing the value
	System.out.println(data);
	//closing the workbook
	workbook.close();

	}

}