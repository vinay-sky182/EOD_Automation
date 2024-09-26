package com.qa.monitor;


import com.example.email.EmailService;

import com.qa.config.Config;
import com.qa.excel.ExcelUtils;
import com.qa.screenshot.ScreenshotService;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelChangeMonitor extends AbstractChangeMonitor {

    public ExcelChangeMonitor(ScreenshotService screenshotService) {
        super(screenshotService); // Call to parent constructor
    }

    @Override
    public void monitorChanges() { // Implementation of the abstract method
        try {
            Config config = new Config();
            String excelFilePath = config.getExcelFilePath();

            // Load previous version of the Excel file if it exists
            Workbook previousWorkbook = ExcelUtils.loadWorkbook("previous_version.xlsx");
            Workbook currentWorkbook = ExcelUtils.loadWorkbook(excelFilePath);

            // Check for changes
            boolean changesDetected = !ExcelUtils.workbooksAreEqual(previousWorkbook, currentWorkbook);

            if (changesDetected) {
                // Take a screenshot of the current Excel content
                screenshotService.takeScreenshot(excelFilePath, config.getScreenshotSaveLocation());

                // Send email with the screenshot attached
                EmailService emailService = new EmailService();
                emailService.sendEmailWithAttachment(config.getScreenshotSaveLocation());
            } else {
                System.out.println("No changes detected.");
            }

            // Save the current workbook as the previous version for future comparisons
            ExcelUtils.saveWorkbook(currentWorkbook, "previous_version.xlsx");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            screenshotService.close();
        }
    }
}

