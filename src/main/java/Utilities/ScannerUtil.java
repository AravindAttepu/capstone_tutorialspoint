package Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ScannerUtil {

    /**
     * Reads an Excel file and returns a Map where the first column is the key
     * and the second column is the value.
     *
     * @param filePath Path of the Excel file
     * @return Map<String, String>
     * @throws IOException if file is not found or cannot be read
     */
    public static Map<String, String> readExcelToMap(String filePath) throws IOException {
        Map<String, String> data = new HashMap<>();
        DataFormatter formatter = new DataFormatter();

        try (InputStream is = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(is)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                Cell keyCell = row.getCell(0);
                Cell valueCell = row.getCell(1);

                if (keyCell != null && valueCell != null) {
                    String key = formatter.formatCellValue(keyCell).trim();
                    String value = formatter.formatCellValue(valueCell).trim();
                    if (!key.isEmpty()) {
                        data.put(key, value);
                    }
                }
            }
        }
        return data;
    }

   
}

