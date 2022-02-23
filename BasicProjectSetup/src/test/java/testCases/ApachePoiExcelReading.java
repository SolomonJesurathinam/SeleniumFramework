package testCases;




import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.Test;

public class ApachePoiExcelReading {
	
	@Test
	public void dataRead() throws EncryptedDocumentException, IOException {
	
		List<String> Usernames = new ArrayList<String>();
		List<String> Passwords = new ArrayList<String>();
		
		Workbook workbook = WorkbookFactory.create(new File("testData.xlsx"));
		Sheet sheet = workbook.getSheetAt(0);
		
		//To get individual value
		//System.out.println(sheet.getRow(0).getCell(0).getStringCellValue());
		
		//To get full list
		Iterator<Row> rowIterator = sheet.iterator();
		while(rowIterator.hasNext()) {
			Row rowValue = rowIterator.next();
			Iterator<Cell> cellIterator= rowValue.iterator();
			int i=2;
			while(cellIterator.hasNext()) {
				if(i%2 ==0) {
					Cell cellValue = cellIterator.next();
					Usernames.add(cellValue.getStringCellValue());
				} else {
					Cell cellValue = cellIterator.next();
					Passwords.add(cellValue.getStringCellValue());
				}
				i++;
			}
		}
		Usernames.remove(0);
		Passwords.remove(0);
		System.out.println("Usernames "+Usernames);
		System.out.println("Passwords "+Passwords);
		
		
		//copying the value to a 2d Array
		String[][] data = new String[sheet.getLastRowNum()][sheet.getRow(1).getLastCellNum()];
				
		for(int i=1; i<sheet.getLastRowNum()+1;i++) {
			for(int j=0; j<sheet.getRow(1).getLastCellNum(); j++) {
				data[i-1][j] =  sheet.getRow(i).getCell(j).getStringCellValue();
			}
		}
		
		System.out.println("2d array is "+Arrays.deepToString(data));
	}
}

