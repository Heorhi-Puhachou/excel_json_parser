package by.excel.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import by.convert.AcadLacinkaConverter;
import by.convert.AcadTaraskConverter;
import by.excel.parser.glossary.Record;
import by.excel.parser.links.Link;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertExcel2Json {

    public static void main(String[] args) {
        List<Record> customers = readExcelFile(
                "Гласарый і стайлгайд перакладу Ubuntu на беларускую мову.xlsx",
                "Спіс тэрмінаў");
        writeObjects2JsonFile(customers, "glossary.json");


        HashMap<String, ArrayList<Link>> linksInfo = readLinksInfo(
                "Гласарый і стайлгайд перакладу Ubuntu на беларускую мову.xlsx",
                "Карысныя спасылкі");
        writeLinks2JsonFile(linksInfo, "links.json");

        System.out.println("Канвертацыя скончана.");
    }

    /**
     * Read Excel File into Java List Objects
     *
     * @param filePath
     * @return
     */
    private static List<Record> readExcelFile(String filePath, String sheetName) {
        try {
            Workbook workbook = getWorkbook(filePath);
            Iterator<Row> rows = getSheetIterator(workbook, sheetName);

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
     * @param links
     * @param pathFile
     */
    private static void writeLinks2JsonFile(HashMap<String, ArrayList<Link>> links, String pathFile) {
        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        File file = new File(pathFile);
        try {
            // Serialize Java object info JSON file.
            mapper.writeValue(file, links);
        } catch (IOException e) {
            e.printStackTrace();
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

    private static Workbook getWorkbook(String filePath) throws IOException {
        FileInputStream excelFile = new FileInputStream(new File(filePath));
        return new XSSFWorkbook(excelFile);
    }

    private static Iterator<Row> getSheetIterator(Workbook workbook, String sheetName) {
        Sheet sheet = workbook.getSheet(sheetName);
        return sheet.iterator();
    }


    /**
     * Read Excel File into Java List Objects
     *
     * @param filePath
     * @return
     */
    private static HashMap<String, ArrayList<Link>> readLinksInfo(String filePath, String sheetName) {
        try {
            Workbook workbook = getWorkbook(filePath);
            Iterator<Row> rows = getSheetIterator(workbook, sheetName);

            HashMap<String, ArrayList<Link>> categorizedLinks = new HashMap<>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }


                Iterator<Cell> cellsInRow = currentRow.iterator();

                String category = "";
                String url = "";
                String description = "";


                int cellIndex = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    if (cellIndex == 0) { // Original value
                        category = currentCell.getStringCellValue();
                    } else if (cellIndex == 1) { // acad value
                        url = currentCell.getStringCellValue();
                    } else if (cellIndex == 2) { // Wrong example
                        description = currentCell.getStringCellValue();
                    }

                    cellIndex++;
                }

                if (url.isEmpty()) {
                    break;
                }

                Link link = new Link(url, description);
                if (category.isEmpty()) {
                    category = "Агульнае";
                }
                if (categorizedLinks.get(category) == null) {
                    categorizedLinks.put(category, new ArrayList<>());
                }
                ArrayList linksForCategory = categorizedLinks.get(category);
                linksForCategory.add(link);


                rowNumber++;
            }

            workbook.close();
            return categorizedLinks;
        } catch (IOException e) {
            throw new RuntimeException("FAIL! -> message = " + e.getMessage());
        }
    }
}