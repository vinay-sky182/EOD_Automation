package com.qa.screenshot;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

public class ScreenshotService {

    private WebDriver driver;

    public ScreenshotService() {
        driver = new ChromeDriver();
    }

    public void takeScreenshot(String excelFilePath, String screenshotSaveLocation) throws IOException {
        driver.get("file:///" + new File(excelFilePath).getAbsolutePath());

        // Use WebDriverWait to wait for the page to load completely
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleContains("Excel")); // Wait until the title contains "Excel"

        TakesScreenshot ts = (TakesScreenshot) driver;
        File screenshotFile = ts.getScreenshotAs(OutputType.FILE);

        try (FileOutputStream fos = new FileOutputStream(screenshotSaveLocation)) {
            fos.write(java.nio.file.Files.readAllBytes(screenshotFile.toPath()));
            System.out.println("Screenshot taken and saved at: " + screenshotSaveLocation);
        }
    }

    public void close() {
        if(driver != null) {
            driver.quit();
        }
    }
}

