package com.crm.comcast.SDET32.practise;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WriteDataIntoAExcelSheetTest {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		
		//Converting the Physical Representation File into Java Object
		FileInputStream fis=new FileInputStream("./src/test/resources/ExcelData1.xlsx");
		//Converting the Physical Representation of Workbook to Java Representation
		Workbook workbook = WorkbookFactory.create(fis);
		//Getting the control of the cell and creating the cell;
		Cell cell = workbook.getSheet("Sheet2").getRow(1).createCell(4);
		//Passing the value to the particular cell;
		cell.setCellValue("Pass");
		//Converting the Java Representation of the file into Physical RFepresentation;
		FileOutputStream fos=new FileOutputStream("./src/test/resources/ExcelData1.xlsx");
		//Converting the Java Representation of the workbook into Physical Representation of workbook
		workbook.write(fos);
		workbook.close();
	}

}
