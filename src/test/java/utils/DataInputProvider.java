package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import wrapper.GenericWrappers;

public class DataInputProvider {
	
	
	
	public static String[][] getSheet(String dataSheetName) throws IOException{
		System.out.println("---------data------");
		String [][] data = null;
		
		System.out.println("Path is :"+GenericWrappers.getAbsolutePath()+"data/"+dataSheetName+".xlsx");
		
		FileInputStream fis = new FileInputStream(new File(GenericWrappers.getAbsolutePath()+"/data/"+dataSheetName+".xlsx"));
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		//get the no of rows
		int rowCount = sheet.getLastRowNum();
		
		// get the number of columns
		
		int columnCout= sheet.getRow(0).getLastCellNum();
		
		//set the 2D arry size
		data = new String[rowCount][columnCout];
		
		
		// loop through the rows and add the values in 2D array
		
		for(int i = 1;i<=rowCount;i++) {
			try {
				
				XSSFRow row = sheet.getRow(i);
				
				for(int j = 0; j<columnCout;j++) {
					try {
						
						String cellVal="";
						try {
							
							cellVal = row.getCell(j).getStringCellValue();
							System.out.println("cell val = "+cellVal);
						}
						catch (NullPointerException e) {
							e.printStackTrace();
						}
						data[i-1][j] = cellVal;
					}
					catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
				
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		
		fis.close();
		workbook.close();
		
		return data;
	}
	
	

}
