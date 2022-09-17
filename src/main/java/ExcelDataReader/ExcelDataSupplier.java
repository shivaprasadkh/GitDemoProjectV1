package ExcelDataReader;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ExcelDataSupplier {
	@DataProvider(name ="loginData")
	public String[][] getData() throws Exception {  //main method first

		File exceFile = new File("./src/test/resources/Test.xlsx");
		// System.out.println(exceFile.exists());

		FileInputStream fis = new FileInputStream(exceFile);

		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("sheet1");

		int noOfrows = sheet.getPhysicalNumberOfRows();
		System.out.println("Number of rows :" + noOfrows);
		int noOfColms = sheet.getRow(0).getLastCellNum();
		System.out.println(sheet.getLastRowNum());
		
		
		String[][] data = new String[noOfrows-1][noOfColms];
		for (int i = 0; i < noOfrows-1; i++) {
			for (int j = 0; j < noOfColms; j++) {
 				DataFormatter df = new DataFormatter();
//				System.out.println(df.formatCellValue(sheet.getRow(i).getCell(j)));
//				//System.out.println(sheet.getRow(i).getCell(j).getStringCellValue());
				
				data[i][j] =  df.formatCellValue(sheet.getRow(i+1).getCell(j));

			}
			//System.out.println();

		}
		workbook.close();
		fis.close();
		
		for (String[] dataArr : data) {
 			System.out.println(Arrays.toString(dataArr));
		}
		return data;
	}

}
