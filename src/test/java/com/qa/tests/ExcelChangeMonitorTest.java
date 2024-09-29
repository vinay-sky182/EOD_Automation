package com.qa.tests;

import com.qa.screenshot.ScreenshotService;
import org.testng.annotations.Test;

import com.qa.base.BaseTest;
import com.qa.config.Config;
import com.qa.services.EmailService;
import com.qa.services.ExcelService;

import java.util.List;
import java.util.Properties;


public class ExcelChangeMonitorTest extends BaseTest {
    private Config config = new Config();

    @Test
    public void checkForExcelChange() {

        // Fetch properties from the config file
        Properties properties = config.getProperties();
        String currentFilePath = config.getProperty("excel.currentFilePath");

        // Check for changes in the Excel file
        ExcelService excelService = new ExcelService(currentFilePath, properties);
        // Check if the Excel data has changed
        if (excelService.isDataChanged()) {
            // Capture a screenshot if changes are detected
            String screenshotFileName = properties.getProperty("screenshot.filename");
            ScreenshotService screenshotUtil = new ScreenshotService();
            screenshotUtil.captureScreenshot(driver, screenshotFileName);

            // Get and log differences
            List<String> differences = excelService.getDifferences();
            System.out.println("Changes detected:");
            differences.forEach(System.out::println);  // Print each difference

            // Save the current state as the previous state for future comparisons
            excelService.saveCurrentDataAsPrevious();

            // Use EmailService to send an email notification
            EmailService emailService = new EmailService(driver);
            emailService.sendDefaultEmail();
        } else {
            System.out.println("No changes detected.");
        }
    }
}


//public class ExcelChangeMonitorTest {
//
//    @Test
//    public void testMonitorChanges() {
//        ScreenshotService screenshotService = new ScreenshotService(); // Create instance of ScreenshotService
//        ExcelChangeMonitor monitor = new ExcelChangeMonitor(screenshotService); // Pass it to the monitor
//        monitor.monitorChanges(); // Call the method to monitor changes
//    }
//}

