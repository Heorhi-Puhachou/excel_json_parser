package by.excel.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import by.convert.AcadLacinkaConverter;
import by.convert.AcadTaraskConverter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertExcel2Json {

    public static void main(String[] args) {
        // Step 1: Read Excel File into Java List Objects
        List<Record> customers = readExcelFile("base.xlsx");

        // Step 2: Write Java List Objects to JSON File
        writeObjects2JsonFile(customers, "glossary.json");

        System.out.println("Done");
    }

    /**
     * Read Excel File into Java List Objects
     *
     * @param filePath
     * @return
     */
    private static List<Record> readExcelFile(String filePath) {
        try {
            FileInputStream excelFile = new FileInputStream(new File(filePath));
            Workbook workbook = new XSSFWorkbook(excelFile);

            Sheet sheet = workbook.getSheet("Спіс тэрмінаў");
            Iterator<Row> rows = sheet.iterator();

            List<Record> lstCustomers = new ArrayList<Record>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }


                Iterator<Cell> cellsInRow = currentRow.iterator();

                Record record = null;
                String originalValue = "";
                String acadValue = "";
                String acadWrong = "";
                String acadComment = "";


                int cellIndex = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    if (cellIndex == 0) { // Original value
                        originalValue = currentCell.getStringCellValue();
                    } else if (cellIndex == 1) { // acad value
                        acadValue = currentCell.getStringCellValue();
                    } else if (cellIndex == 2) { // Wrong example
                        acadWrong = currentCell.getStringCellValue();
                    } else if (cellIndex == 3) { // Comment
                        acadComment = currentCell.getStringCellValue();
                    }


                    cellIndex++;
                }

                if (originalValue.isEmpty()) {
                    break;
                }
                record = new Record(rowNumber, originalValue,
                        AcadTaraskConverter.convert(acadValue), AcadTaraskConverter.convert(acadWrong), AcadTaraskConverter.convert(acadComment),
                        acadValue, acadWrong, acadComment,
                        AcadLacinkaConverter.convert(acadValue), AcadLacinkaConverter.convert(acadWrong), AcadLacinkaConverter.convert(acadComment)

                );

                lstCustomers.add(record);
                rowNumber++;
            }

            // Close WorkBook
            workbook.close();

            return lstCustomers;
        } catch (IOException e) {
            throw new RuntimeException("FAIL! -> message = " + e.getMessage());
        }
    }

    /**
     * Convert Java Objects to JSON File
     *
     * @param records
     * @param pathFile
     */
    private static void writeObjects2JsonFile(List<Record> records, String pathFile) {
        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        File file = new File(pathFile);
        try {
            // Serialize Java object info JSON file.
            mapper.writeValue(file, records);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}