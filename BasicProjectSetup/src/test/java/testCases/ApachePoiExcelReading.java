package testCases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.testng.annotations.Test;

public class ApachePoiExcelReading {
	
	@Test
	public void dataRead() throws IOException {
		
		FileInputStream file = new FileInputStream("‪C:\\Users\\Solo\\Desktop\\testData.xlsx");
		
		Workbook workbook = new HSSFWorkbook(file);
	}

}
