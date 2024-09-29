package com.qa.excel;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {
    private String filePath;
    private List<String> previousData;

    public ExcelUtils(String filePath) {
        this.filePath = filePath;
        this.previousData = readExcelData();
    }

    public List<String> readExcelData() {
        List<String> data = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath); XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
            XSSFSheet sheet = workbook.getSheetAt(0);
            sheet.forEach(row -> row.forEach(cell -> data.add(cell.toString())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public boolean isDataChanged() {
        List<String> currentData = readExcelData();
        return !previousData.equals(currentData);
    }
}



//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.*;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.util.Iterator;
//
//public class ExcelUtils {
//    private String filePath;
//    private XSSFWorkbook workbook;
//    private XSSFSheet sheet;
//
//    public ExcelUtils(String filePath) {
//        this.filePath = filePath;
//    }
//
//    public String readExcelData() throws Exception {
//        FileInputStream fis = new FileInputStream(filePath);
//        workbook = new XSSFWorkbook(fis);
//        sheet = workbook.getSheetAt(0);
//        StringBuilder data = new StringBuilder();
//
//        Iterator<Row> rowIterator = sheet.iterator();
//        while (rowIterator.hasNext()) {
//            Row row = rowIterator.next();
//            Iterator<Cell> cellIterator = row.cellIterator();
//            while (cellIterator.hasNext()) {
//                Cell cell = cellIterator.next();
//                data.append(cell.toString()).append(" ");
//            }
//            data.append("\n");
//        }
//        fis.close();
//        return data.toString();
//    }
//
//    public void writeExcelData(String newData) throws Exception {
//        FileOutputStream fos = new FileOutputStream(filePath);
//        sheet = workbook.getSheetAt(0);
//        Row row = sheet.createRow(sheet.getLastRowNum() + 1);
//        Cell cell = row.createCell(0);
//        cell.setCellValue(newData);
//        workbook.write(fos);
//        fos.close();
//    }
//}





//
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
//public class ExcelUtils {
//
//    /**
//     * Loads a workbook from the specified file path.
//     *
//     * @param filePath the path to the Excel file
//     * @return XSSFWorkbook instance of the loaded workbook
//     * @throws IOException if an I/O error occurs
//     */
//    public static XSSFWorkbook loadWorkbook(String filePath) throws IOException {
//        try (FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\data\\eod_file.xlsx")) {
//            return new XSSFWorkbook(fis); // Load workbook as XSSFWorkbook
//        }
//    }
//
//    /**
//     * Checks if two workbooks are equal by comparing their sheets.
//     *
//     * @param wb1 first workbook to compare
//     * @param wb2 second workbook to compare
//     * @return true if workbooks are equal, false otherwise
//     */
//    public static boolean workbooksAreEqual(XSSFWorkbook wb1, XSSFWorkbook wb2) {
//        if (wb1 == null || wb2 == null) return false;
//
//        if (wb1.getNumberOfSheets() != wb2.getNumberOfSheets()) return false;
//
//        for (int i = 0; i < wb1.getNumberOfSheets(); i++) {
//            XSSFSheet sheet1 = wb1.getSheetAt(i);
//            XSSFSheet sheet2 = wb2.getSheetAt(i);
//
//            if (!sheetsAreEqual(sheet1, sheet2)) return false;
//        }
//
//        return true;
//    }
//
//    /**
//     * Compares two sheets for equality.
//     *
//     * @param sheet1 first sheet to compare
//     * @param sheet2 second sheet to compare
//     * @return true if sheets are equal, false otherwise
//     */
//    private static boolean sheetsAreEqual(XSSFSheet sheet1, XSSFSheet sheet2) {
//        if (sheet1.getPhysicalNumberOfRows() != sheet2.getPhysicalNumberOfRows()) return false;
//
//        for (int i = 0; i < sheet1.getPhysicalNumberOfRows(); i++) {
//            Row row1 = sheet1.getRow(i);
//            Row row2 = sheet2.getRow(i);
//
//            if (!rowsAreEqual(row1, row2)) return false;
//        }
//
//        return true;
//    }
//
//    /**
//     * Compares two rows for equality.
//     *
//     * @param row1 first row to compare
//     * @param row2 second row to compare
//     * @return true if rows are equal, false otherwise
//     */
//    private static boolean rowsAreEqual(Row row1, Row row2) {
//        if (row1.getPhysicalNumberOfCells() != row2.getPhysicalNumberOfCells()) return false;
//
//        for (int j = 0; j < row1.getPhysicalNumberOfCells(); j++) {
//            Cell cell1 = row1.getCell(j);
//            Cell cell2 = row2.getCell(j);
//
//            if (!cellValuesAreEqual(cell1, cell2)) return false;
//        }
//
//        return true;
//    }
//
//    /**
//     * Compares two cell values for equality.
//     *
//     * @param cell1 first cell to compare
//     * @param cell2 second cell to compare
//     * @return true if cell values are equal, false otherwise
//     */
//    private static boolean cellValuesAreEqual(Cell cell1, Cell cell2) {
//        if (cell1 == null && cell2 == null) return true;
//
//        if (cell1 == null || cell2 == null) return false;
//
//        return cell1.toString().equals(cell2.toString());
//    }
//
//    /**
//     * Saves the current workbook to the specified file path.
//     *
//     * @param currentWorkbook the workbook to save
//     * @param filePath       the path where the workbook will be saved
//     * @throws IOException if an I/O error occurs
//     */
//    public static void saveWorkbook(XSSFWorkbook currentWorkbook, String filePath) throws IOException {
//        try (FileOutputStream fos = new FileOutputStream(filePath)) {
//            currentWorkbook.write(fos); // Save workbook as XSSFWorkbook
//            System.out.println("Current workbook saved as previous version.");
//        }
//    }
//}
//
