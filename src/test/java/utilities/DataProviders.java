package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException
	{
		String path = ".\\testData\\LoginData.xlsx";
		ExcelUtility excelUtil = new ExcelUtility(path);
		
		int totalRows = excelUtil.getRowCount("Sheet1");	
		int totalCols = excelUtil.getCellCount("Sheet1", 1);
				
		String [][] loginData = new String[totalRows][totalCols];
		
		for(int i = 1; i <= totalRows; i++)  
			for(int j = 0; j < totalCols; j++)  
				loginData[i-1][j]= excelUtil.getCellData("Sheet1",i, j);  

		return loginData;
	}
}