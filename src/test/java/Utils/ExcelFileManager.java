package Utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

/**
 * ExcelFileManager — reads test data from .xlsx files.
 *
 * Usage:
 *   String value = ExcelFileManager.getCellData("TestData/Data.xlsx", "Sheet1", 0, 1);
 */
public class ExcelFileManager {

    public static String getCellData(String filePath, String sheetName, int rowNum, int colNum) {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            Row   row   = sheet.getRow(rowNum);
            Cell  cell  = row.getCell(colNum);

            DataFormatter formatter = new DataFormatter();
            return formatter.formatCellValue(cell);

        } catch (Exception e) {
            throw new RuntimeException("Failed to read cell [" + rowNum + "," + colNum + "] " +
                    "from sheet '" + sheetName + "' in " + filePath, e);
        }
    }

    public static int getRowCount(String filePath, String sheetName) {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            return workbook.getSheet(sheetName).getLastRowNum() + 1;
        } catch (Exception e) {
            throw new RuntimeException("Failed to count rows in " + filePath, e);
        }
    }
}
