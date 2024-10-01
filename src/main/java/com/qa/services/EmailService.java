package com.qa.services;

import com.qa.base.BaseTest;
import com.qa.config.Config;
import org.openqa.selenium.WebDriver;
import com.qa.pages.GmailComposePage;
import com.qa.pages.GmailLoginPage;
import org.openqa.selenium.chrome.ChromeDriver;

public class EmailService extends BaseTest {
    private Config configRead;
    private WebDriver driver;  // Encapsulation
    private GmailComposePage composePage;

    public EmailService(WebDriver driver) {
        this.driver = driver;
        configRead = new Config();  // Encapsulation in Config class
        lPage = new GmailLoginPage(driver);
        composePage = new GmailComposePage(driver);
    }

    // Encapsulated method for sending an email
    public void sendEmail(String recipient, String subject, String body, String attachmentPath) throws InterruptedException {
//        driver = new ChromeDriver();
//        driver.get("https://mail.google.com");
        Thread.sleep(5000);


        // Fetch Gmail credentials from properties file
        String gmailUsername = configRead.getProperty("gmail.username");
        String gmailPassword = configRead.getProperty("gmail.password");
        // Perform login and compose email using encapsulated methods
        lPage.login(gmailUsername, gmailPassword);  // Encapsulation in GmailLoginPage
        Thread.sleep(2000);
        composePage.sendEmail(recipient, subject, body, attachmentPath);  // Encapsulation in GmailComposePage
    }

    public void sendDefaultEmail() {
        // Get default email properties from the config file
        String emailRecipient = configRead.getProperty("email.recipient");
        String emailSubject = configRead.getProperty("email.subject");
        String emailBody = configRead.getProperty("email.body");
        String attachment = configRead.getProperty("screenshot.filename");

        composePage.sendEmail(emailRecipient, emailSubject, emailBody, attachment);  // Encapsulation in GmailComposePage
    }
}

