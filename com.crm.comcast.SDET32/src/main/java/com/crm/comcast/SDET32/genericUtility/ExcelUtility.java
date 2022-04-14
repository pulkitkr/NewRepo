package com.crm.comcast.SDET32.genericUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * This class is used to fetch and write data from or to excel sheet
 * @author hp
 *
 */
public class ExcelUtility {
	/**
	 * This method fetches String data from Excel sheet
	 * @param sheetName
	 * @param rowNo
	 * @param cellNo
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public String getStringDataFromExcelSheet(String sheetName,int rowNo,int cellNo) throws EncryptedDocumentException, IOException {
		FileInputStream fisForExcel=new FileInputStream(IPathConstant.EXCEL_FILE_PATH);
		Workbook workbook = WorkbookFactory.create(fisForExcel);
		return workbook.getSheet(sheetName).getRow(rowNo).getCell(cellNo).getStringCellValue();	
	}
	public double getNumberDataFromExcelSheet(String sheetName,int rowNo,int cellNo) throws EncryptedDocumentException, IOException {
		FileInputStream fisForExcel=new FileInputStream(IPathConstant.EXCEL_FILE_PATH);
		Workbook workbook = WorkbookFactory.create(fisForExcel);
		return workbook.getSheet(sheetName).getRow(rowNo).getCell(cellNo).getNumericCellValue();	
	}
	/**
	 * This method fetches boolean data from Excel sheet
	 * @param sheetName
	 * @param rowNo
	 * @param cellNo
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public boolean getBooleanDataFromExcelSheet(String sheetName,int rowNo,int cellNo) throws EncryptedDocumentException, IOException {
		FileInputStream fisForExcel=new FileInputStream(IPathConstant.EXCEL_FILE_PATH);
		Workbook workbook = WorkbookFactory.create(fisForExcel);
		return workbook.getSheet(sheetName).getRow(rowNo).getCell(cellNo).getBooleanCellValue();	
	}
	/**
	 * This method is used to write data into an Excel Sheet
	 * @param sheetName
	 * @param rowNo
	 * @param cellNo
	 * @param value
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public void writeValueToExcelSheet(String sheetName,int rowNo,int cellNo,String value) throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream(IPathConstant.EXCEL_FILE_PATH);
		Workbook workbook = WorkbookFactory.create(fis);
		workbook.getSheet(sheetName).getRow(rowNo).createCell(cellNo).setCellValue(value);	
		FileOutputStream fos=new FileOutputStream(IPathConstant.EXCEL_FILE_PATH);
		workbook.write(fos);
		System.out.println("Data is updated successfully");
		workbook.close();
	}

}
