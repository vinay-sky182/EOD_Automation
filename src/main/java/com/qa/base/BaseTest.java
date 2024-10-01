package com.qa.base;

import com.qa.config.Config;
import com.qa.pages.GmailLoginPage;
import com.qa.services.ExcelService;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
    protected WebDriver driver; // Encapsulation
    protected GmailLoginPage lPage; // Encapsulation;
    protected ExcelService excelService; // Encapsulation;
    private Config config = new Config();

    @BeforeClass
    public GmailLoginPage launchApplication() throws IOException
    {
        Properties properties = config.getProperties();
        String currentFilePath = config.getProperty("excel.currentFilePath");
        excelService = new ExcelService(currentFilePath, properties);

        // Check if Excel content has changed
        if (excelService.isDataChanged()) {
            driver = initializeDriver(); // Only initialize if content has changed
            lPage = new GmailLoginPage(driver);
            lPage.goTo();
            return lPage;
        } else {
            System.out.println("No changes detected in Excel file. Skipping browser initialization.");
            return null; // Return null if no changes were detected
        }
    }

    public WebDriver initializeDriver() throws IOException, FileNotFoundException {
        Properties prop = new Properties(); // creating object of 'Properties' class

        try (FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\config.properties")){
            prop.load(fis);
        }
        // creating the 'FileInputStream' object and passing the path of '.properties' extension file
        // System.getProperty("user.dir") :- It will give the project path from the local system, dynamically "C:\Users\vigupta\eclipseProjects\Ecommerce Demo\SeleniumFrameworkDesign"

         // calling the load() function of 'Properties' class which takes the 'FileInputStream' object

        String browserName = prop.getProperty("browser");

        if(browserName.equalsIgnoreCase("chrome"))
        {
            driver = new ChromeDriver();
        }
        else if(browserName.equalsIgnoreCase("firefox"))
        {
            driver = new FirefoxDriver();
        }
        else
        {
            driver = new EdgeDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }

    @AfterClass
    public void tearDown()
    {
        if (driver != null) {
            driver.close();
        }
    }



//    // Set up WebDriver for any test class that extends TestBase
//    @BeforeClass(alwaysRun=true)
//    public void setup() {
//        driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        driver.manage().window().maximize();
//    }
//
//    @AfterClass
//    public void tearDown() {
//        driver.quit();
//    }
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


