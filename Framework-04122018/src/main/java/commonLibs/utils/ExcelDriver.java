package commonLibs.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDriver {

	private InputStream fileReader;

	// To write into a file
	private OutputStream fileWriter;

	// Creating a workbook
	private Workbook excelWorkbook;

	// Default name for a file
	private String sExcelFileName;

	// sFileName -- Complete path including filename
	public void cerateExcelWorkbook(String sFilename) throws Exception {
		// Removing whitespaces from the end
		sFilename = sFilename.trim();

		// Putting empty check
		if (sFilename.isEmpty()) {
			throw new Exception("Invalid filename ...");
		}

		// Create instance of file class

		File file = new File(sFilename);

		if (file.exists()) {
			throw new Exception("File already exists");
		}

		if (sFilename.endsWith("xlsx")) {
			excelWorkbook = new XSSFWorkbook();
		} else if (sFilename.endsWith("xls")) {
			excelWorkbook = new HSSFWorkbook();
		} else {
			throw new Exception("Invalid file extension");
		}

		// Creating a output stream with mentioned filename to be written in workbook

		fileWriter = new FileOutputStream(sFilename);

		// Writing to a workbook
		excelWorkbook.write(fileWriter);

		// Closing output stream
		fileWriter.close();

		// Closing created file
		excelWorkbook.close();
	}

	public void openExcelWorkbook(String sFileName) throws Exception {

		sFileName = sFileName.trim();

		if (sFileName.isEmpty()) {
			throw new Exception("File name not specified");
		}

		if (!(new File(sFileName)).exists()) {
			throw new Exception("File doesnot exist");
		}

		// Reading data from an excel sheet
		fileReader = new FileInputStream(sFileName);

		// Assigning sheet name to global variable
		sExcelFileName = sFileName;

		// Representing or converting raw data read from an excel into excel workbook
		// format

		excelWorkbook = WorkbookFactory.create(fileReader);

	}

	public void save() throws Exception {

		fileWriter = new FileOutputStream(sExcelFileName);
		excelWorkbook.write(fileWriter);
		fileWriter.close();
	}

	public void saveAs(String sfileNewName) throws Exception {

		if (sfileNewName.isEmpty()) {
			throw new Exception("File name doesnot exist");
		}

		if ((new File(sfileNewName)).exists()) {
			throw new Exception("File already exist");
		}

		fileWriter = new FileOutputStream(sfileNewName);
		excelWorkbook.write(fileWriter);
		fileWriter.close();
	}

	public void close() throws Exception {
		excelWorkbook.close();
		fileReader.close();
	}

	public void createSheet(String sSheetName) throws Exception {

		sSheetName = sSheetName.trim();

		if (sSheetName.isEmpty()) {
			throw new Exception("Sheet name not specified");
		}

		Sheet oSheet;

		oSheet = excelWorkbook.getSheet(sSheetName);

		if (oSheet != null) {
			throw new Exception("Sheet already exist");
		}

		excelWorkbook.createSheet(sSheetName);
	}

	public int getRowCountOfSheet(String sSheetName) throws Exception {

		sSheetName = sSheetName.trim();

		if (sSheetName.isEmpty()) {
			throw new Exception("Sheet name not specified");
		}

		Sheet oSheet;

		oSheet = excelWorkbook.getSheet(sSheetName);

		if (oSheet == null) {

			throw new Exception("Sheet does not exist");
		}

		return oSheet.getLastRowNum();
	}

	public int getCellCount(String sSheetName, int iRow) throws Exception {

		sSheetName = sSheetName.trim();

		if (sSheetName.isEmpty()) {
			throw new Exception("Sheet name not specified");
		}

		Sheet oSheet;

		oSheet = excelWorkbook.getSheet(sSheetName);

		if (oSheet == null) {
			throw new Exception("Sheet does not exist");
		}

		if (iRow < 1) {
			throw new Exception("Row index starts from 1");
		}

		Row oRow;

		oRow = oSheet.getRow(iRow - 1);

		if (oRow == null) {
			return 0;
		} else {
			return oRow.getLastCellNum();
		}
	}

	public String getCellData(String sSheetName, int iRow, int iCell) {
		try {
			sSheetName = sSheetName.trim();
			if (sSheetName.isEmpty()) {
				throw new Exception("Sheet name not specified");
			}

			Sheet oSheet;

			oSheet = excelWorkbook.getSheet(sSheetName);

			if (oSheet == null) {
				throw new Exception("Sheet does not exist");
			}

			if (iRow < 1 || iCell < 1) {
				throw new Exception("Row and cell index starts from 1");
			}

			Row oRow;

			oRow = oSheet.getRow(iRow - 1);

			if (oRow == null) {
				return "";
			}

			Cell oCell;

			oCell = oRow.getCell(iCell - 1);

			if (oCell == null) {
				return "";
			} else {

				if (oCell.getCellTypeEnum() == CellType.NUMERIC) {
					return String.valueOf(oCell.getNumericCellValue());
				} else {
					return oCell.getStringCellValue();
				}
			}

		} catch (Exception e) {

			return "";
		}
	}

	public void setCellData(String sSheetName, int iRow, int iCell, String sValue) {

		try {
			sSheetName = sSheetName.trim();

			if (sSheetName.isEmpty()) {

				throw new Exception("Sheet name not specified");
			}

			Sheet oSheet;

			oSheet = excelWorkbook.getSheet(sSheetName);

			if (oSheet == null) {

				throw new Exception("Sheet does not exist");
			}

			if (iRow < 1 || iCell < 1) {
				throw new Exception("Row and cell index starts from1");
			}

			Row oRow;

			oRow = oSheet.getRow(iRow - 1);

			if (oRow == null) {

				oSheet.createRow(iRow - 1);
				oRow = oSheet.getRow(iRow - 1);
			}

			Cell oCell;

			oCell = oRow.getCell(iCell - 1);

			if (oCell == null) {
				oRow.createCell(iCell - 1);
				oCell = oRow.getCell(iCell - 1);
			}

			oCell.setCellValue(sValue);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
