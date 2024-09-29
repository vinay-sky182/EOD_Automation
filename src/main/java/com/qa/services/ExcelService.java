package com.qa.services;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class ExcelService {
    private String currentFilePath;
    private String previousFilePath;
    private Properties config;

    public ExcelService(String currentFilePath, Properties config) {
        this.currentFilePath = currentFilePath;
        this.config = config;
        // Check if previousFilePath exists in the properties
        this.previousFilePath = config.getProperty("excel.previousFilePath");

        if (previousFilePath == null) {
            // If previousFilePath is not defined, initialize it with the current Excel file
            System.out.println("No previous file path found. Assuming this is the first run.");
            this.previousFilePath = "src/main/resources/data/previous_data.xlsx";  // Set default path
            saveCurrentDataAsPrevious();  // Initialize previous Excel file with current data

            // Save the new previousFilePath to the properties file
            config.setProperty("excel.previousFilePath", previousFilePath);
            saveProperties(config);
        }
    }
    // Method to read Excel data from a file and return it as List<String>
    public List<String> readExcelData(String filePath) {
        List<String> data = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                StringBuilder rowData = new StringBuilder();
                for (Cell cell : row) {
                    rowData.append(cell.toString()).append(","); // Add each cell's data to the rowData string
                }
                data.add(rowData.toString()); // Add the row data as a string to the list
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;  // Return empty list in case of error
    }

    // Compare previous and current Excel data
    public boolean isDataChanged() {
        List<String> previousData = readExcelData(previousFilePath);
        List<String> currentData = readExcelData(currentFilePath);

        if (previousData.isEmpty()) return true; // Consider as change if previous data doesn't exist

        // Break the loop as soon as a difference is found
        for (int i = 0; i < Math.min(currentData.size(), previousData.size()); i++) {
            if (!currentData.get(i).equals(previousData.get(i))) {
                return true; // Data has changed, so return true immediately
            }
        }

        // Compare both Excel data lists
        if (previousData.size() != currentData.size()) {
            return true;  // Data changed if sizes differ
        }

//        for (int i = 0; i < previousData.size(); i++) {
//            if (!previousData.get(i).equals(currentData.get(i))) {
//                return true;  // Data changed if any row differs
//            }
//        }

        return false;  // No changes detected
    }

    // Save current Excel data to the previous Excel file for future comparisons
    public void saveCurrentDataAsPrevious() {
        try (FileInputStream fis = new FileInputStream(currentFilePath);
             FileOutputStream fos = new FileOutputStream(previousFilePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            workbook.write(fos);  // Write current Excel data to the previous file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to show differences between previous and current data
    public List<String> getDifferences() {
        List<String> previousData = readExcelData(previousFilePath);
        List<String> currentData = readExcelData(currentFilePath);
        List<String> differences = new ArrayList<>();

        int minSize = Math.min(previousData.size(), currentData.size());

        for (int i = 0; i < minSize; i++) {
            String previousRow = (i < previousData.size()) ? previousData.get(i) : "No Data";
            String currentRow = (i < currentData.size()) ? currentData.get(i) : "No Data";

            if (!previousRow.equals(currentRow)) {
                differences.add("Row " + (i + 1) + " changed:\nPrevious: " + previousRow + "\nCurrent: " + currentRow);
                break;
            }
        }

        return differences;
    }

    // Save properties back to the file
    private void saveProperties(Properties config) {
        try (FileOutputStream output = new FileOutputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\config.properties")) {
            config.store(output, null);  // Save properties to file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}





//public class ExcelService {
//    private String filePath;
//    private XSSFWorkbook workbook;
//    private XSSFSheet sheet;
//
//    public ExcelService(String filePath) {
//        this.filePath = filePath;
//        try {
//            FileInputStream fis = new FileInputStream(filePath);
//            workbook = new XSSFWorkbook(fis);
//            sheet = workbook.getSheetAt(0);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    // Encapsulated method to check if data has changed
//    public boolean isDataChanged(String[] lastKnownData) {
//        Iterator rowIterator = sheet.iterator();
//        int rowIndex = 0;
//        while (rowIterator.hasNext()) {
//            String currentCellData = rowIterator.next().toString();
//            if (!currentCellData.equals(lastKnownData[rowIndex])) {
//                return true;
//            }
//            rowIndex++;
//        }
//        return false;
//    }
//
//    public void saveLastKnownData(String[] data) {
//        try (FileOutputStream fos = new FileOutputStream(filePath)) {
//            for (int i = 0; i < sheet.getLastRowNum(); i++) {
//                sheet.getRow(i).createCell(1).setCellValue(data[i]);
//            }
//            workbook.write(fos);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
