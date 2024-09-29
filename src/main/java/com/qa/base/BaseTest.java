package com.qa.base;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver; // Encapsulation

    // Set up WebDriver for any test class that extends TestBase
    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}


//public class BaseTest {
//    protected WebDriver driver;
//    ConfigReader config;
//
//    @BeforeClass
//    public void setUp() {
//        config = new ConfigReader();
//        System.setProperty("webdriver.chrome.driver", config.getProperty("webdriver.chrome.driver"));
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//    }
//
//    @AfterClass
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }
//}


