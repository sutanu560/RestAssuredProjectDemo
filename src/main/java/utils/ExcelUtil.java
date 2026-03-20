package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtil {
    
    public static void generateSampleExcel(String filePath) {
        try {
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Users");
            
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("name");
            headerRow.createCell(1).setCellValue("job");
            
            Row row1 = sheet.createRow(1);
            row1.createCell(0).setCellValue("api_user_1");
            row1.createCell(1).setCellValue("qa_engineer");
            
            Row row2 = sheet.createRow(2);
            row2.createCell(0).setCellValue("api_user_2");
            row2.createCell(1).setCellValue("sdet");

            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }
            workbook.close();
            LoggerUtil.info("Generated sample Excel file successfully at: " + filePath);
        } catch (Exception e) {
            LoggerUtil.error("Failed to generate sample Excel", e);
        }
    }

    public static Object[][] getTestData(String filePath, String sheetName) {
        if (!new File(filePath).exists()) {
            generateSampleExcel(filePath);
        }
        Object[][] data = null;
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            
            Sheet sheet = workbook.getSheet(sheetName);
            int rowCount = sheet.getLastRowNum();
            int colCount = sheet.getRow(0).getLastCellNum();
            
            data = new Object[rowCount][colCount];
            
            for (int i = 0; i < rowCount; i++) {
                Row row = sheet.getRow(i + 1);
                for (int j = 0; j < colCount; j++) {
                    Cell cell = row.getCell(j);
                    if (cell != null) {
                        data[i][j] = cell.toString();
                    } else {
                        data[i][j] = "";
                    }
                }
            }
        } catch (IOException e) {
            LoggerUtil.error("Error reading Excel data from: " + filePath, e);
        }
        return data;
    }
}
