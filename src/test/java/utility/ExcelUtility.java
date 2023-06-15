package utility;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

public class ExcelUtility {
    /**
     * Read test steps from an Excel file.
     *
     * @param filePath    the path to the Excel file
     * @param sheetNames  the names of the sheets to read from
     * @return a list of test steps
     */
    public static List<String> readTestSteps(String filePath, String... sheetNames) {
        List<String> steps = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(filePath)) {
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            for (String sheetName : sheetNames) {
                XSSFSheet sheet = workbook.getSheet(sheetName);

                if (sheet == null) {
                    System.out.println("Sheet '" + sheetName + "' not found. Skipping...");
                    continue;
                }

                for (Row row : sheet) {
                    for (Cell cell : row) {
                        String cellValue = cell.getStringCellValue();
                        steps.add(cellValue);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return steps;
    }
}
