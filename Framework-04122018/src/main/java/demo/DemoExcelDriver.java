package demo;

import commonLibs.utils.ExcelDriver;

public class DemoExcelDriver {

	public static void main(String[] args) {
		try {
			ExcelDriver excelDriver = new ExcelDriver();
			String sheetName = "TestData";
			String filename = System.getProperty("user.dir") + "/outputFile/testData.xlsx";
			excelDriver.cerateExcelWorkbook(filename);
			excelDriver.openExcelWorkbook(filename);
			excelDriver.createSheet(sheetName);

			excelDriver.setCellData(sheetName, 1, 1, "Sucheta");
			excelDriver.setCellData(sheetName, 1, 2, "Rishaan");
			excelDriver.setCellData(sheetName, 1, 3, "Munish");
			excelDriver.setCellData(sheetName, 1, 4, "Parul");

			excelDriver.save();

			excelDriver.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
