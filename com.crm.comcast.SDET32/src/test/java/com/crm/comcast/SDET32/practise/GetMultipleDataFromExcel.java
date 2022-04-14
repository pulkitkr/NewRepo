package com.crm.comcast.SDET32.practise;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class GetMultipleDataFromExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream("./src/test/resources/ExcelData1.xlsx");
		Workbook workbook = WorkbookFactory.create(fis);
		
//		String expectedData="admin";
		Sheet sheet = workbook.getSheet("Sheet1");
		int rowCount = sheet.getPhysicalNumberOfRows();
		int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
		System.out.println(rowCount);
		System.out.println(colCount);
		Object[][]data=new Object[rowCount][colCount];
		for(int i=0;i<rowCount;i++)
		{
			for(int j=0;j<colCount;j++)
			{
				data[i][j]=sheet.getRow(i).getCell(j).toString();
			}
		}
		for(Object[] a: data) {
			for(Object b: a) {
				System.out.print(b+"\t");
			}
			System.out.println();
		}
	}

}
