package com.qa.services;

import com.qa.base.BaseTest;
import com.qa.config.Config;
import org.openqa.selenium.WebDriver;
import com.qa.pages.GmailComposePage;
import com.qa.pages.GmailLoginPage;

public class EmailService extends BaseTest {
    private Config configRead;
    private WebDriver driver;  // Encapsulation
    private GmailLoginPage loginPage;
    private GmailComposePage composePage;

    public EmailService(WebDriver driver) {
        this.driver = driver;
        configRead = new Config();  // Encapsulation in Config class
    }

    // Encapsulated method for sending an email
    public void sendEmail(String recipient, String subject, String body, String attachmentPath) throws InterruptedException {
        driver.get("https://login.microsoftonline.com/common/oauth2/authorize?client_id=00000002-0000-0ff1-ce00-000000000000&redirect_uri=https%3a%2f%2foutlook.office365.com%2fowa%2f&resource=00000002-0000-0ff1-ce00-000000000000&response_mode=form_post&response_type=code+id_token&scope=openid&msafed=1&msaredir=1&client-request-id=c3a46e11-42fb-84a1-7587-b9e2595e774c&protectedtoken=true&claims=%7b%22id_token%22%3a%7b%22xms_cc%22%3a%7b%22values%22%3a%5b%22CP1%22%5d%7d%7d%7d&nonce=638632649537222926.3ada144e-ff8b-469c-bd8b-38afb1be3560&state=DYtBboMwAASh_UtvNMbGDj5ElUjrojaQYDCk3GwwUmNQEEGg5nV5Wn2Y3dnDuo7jPFueLC6w4WwJCgmCJKAYbSGEFJJXJFvpB4H2ui5UXkBo46nWGgplp3ylESbAtd-Hu7mucvN2m-Wsd_7LpNvfSTdzcd3JmIMmTsjhjy7tmd8UpNNhoEM99Je6SGCa44uCYFEVG9Wejmr4WrTtek-jrGRTJnikWS9q1NfaiHvBuJF2Z2d-5ABPlUnWVGDxc0-TkhlQDKOUfi9EER2lMWtVskgI9iny-cjf-YcA7XceszRFZa0qfMpNz095-A8&sso_reload=true");
        Thread.sleep(5000);
        loginPage = new GmailLoginPage(driver);

        // Fetch Gmail credentials from properties file
        String gmailUsername = configRead.getProperty("gmail.username");
        String gmailPassword = configRead.getProperty("gmail.password");
        // Perform login and compose email using encapsulated methods
        loginPage.login(gmailUsername, gmailPassword);  // Encapsulation in GmailLoginPage
        Thread.sleep(2000);
        composePage = new GmailComposePage(driver);
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

