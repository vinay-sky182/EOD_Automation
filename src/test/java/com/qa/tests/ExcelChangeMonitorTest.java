package com.qa.tests;


import com.qa.monitor.ExcelChangeMonitor;
import com.qa.screenshot.ScreenshotService;
import org.testng.annotations.Test;

public class ExcelChangeMonitorTest {

    @Test
    public void testMonitorChanges() {
        ScreenshotService screenshotService = new ScreenshotService(); // Create instance of ScreenshotService
        ExcelChangeMonitor monitor = new ExcelChangeMonitor(screenshotService); // Pass it to the monitor
        monitor.monitorChanges(); // Call the method to monitor changes
    }
}

