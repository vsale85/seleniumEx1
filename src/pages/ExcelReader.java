package pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	File fajl;
	FileInputStream fis;
	XSSFWorkbook wb;
	XSSFSheet sheet;
	XSSFRow row;
	XSSFCell cell;

	public ExcelReader(String filePath) throws IOException {
		this.fajl = new File(filePath);
		this.fis = new FileInputStream(fajl);
		this.wb = new XSSFWorkbook(fis);

	}

	public String getCellData(String sheetName, int rowNum, int columnNum) {
		sheet = wb.getSheet(sheetName);
		row = sheet.getRow(rowNum - 1);
		cell = row.getCell(columnNum - 1);
		DataFormatter formatter = new DataFormatter();
		String stringCell = formatter.formatCellValue(cell);
		return stringCell;
	}

	public void closeFis() throws IOException {
		fis.close();
	}
}
