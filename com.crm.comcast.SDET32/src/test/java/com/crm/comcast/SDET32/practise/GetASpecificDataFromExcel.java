package com.crm.comcast.SDET32.practise;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class GetASpecificDataFromExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		
		FileInputStream fis=new FileInputStream("./src/test/resources/ExcelData1.xlsx");
		Workbook workbook = WorkbookFactory.create(fis);
		
		String expectedData="admin";
		Sheet sheet = workbook.getSheet("Sheet1");
		int rowCount = sheet.getLastRowNum();
		for(int i=0;i<=rowCount;i++) {
			Row row = sheet.getRow(i);
			String actualData=row.getCell(2).getStringCellValue();
			if(actualData.equals(expectedData)) {
				String data = row.getCell(3).getStringCellValue();
				System.out.println("Value is present = "+data);
				
			}
		}
	}
}
