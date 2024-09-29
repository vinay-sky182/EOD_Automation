package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GmailComposePage {
    private WebDriver driver;

    @FindBy(xpath = "//div[contains(text(),'Compose')]")
    private WebElement composeButton;

    @FindBy(name = "to")
    private WebElement recipientField;

    @FindBy(name = "subjectbox")
    private WebElement subjectField;

    @FindBy(xpath = "//div[@role='textbox']")
    private WebElement bodyField;

    @FindBy(xpath = "//input[@type='file']")
    private WebElement attachFileInput; // Input for file attachment

    @FindBy(xpath = "//div[contains(text(),'Send')]")
    private WebElement sendButton;

    public GmailComposePage(WebDriver driver) {
        this.driver = driver; // Use the shared driver
        PageFactory.initElements(driver, this);
    }

    public void sendEmail(String to, String subject, String body, String attachmentPath) {
        composeButton.click();
        recipientField.sendKeys(to);
        subjectField.sendKeys(subject);
        bodyField.sendKeys(body);

        // Attach a file if the path is not empty
        if (attachmentPath != null && !attachmentPath.isEmpty()) {
            attachFile(attachmentPath);
        }

        sendButton.click();
    }

    private void attachFile(String attachmentPath) {
        // Send the file path to the file input element
        attachFileInput.sendKeys(attachmentPath);
    }
}


