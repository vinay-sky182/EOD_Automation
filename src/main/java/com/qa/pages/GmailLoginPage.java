package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GmailLoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "identifierId")
    private WebElement emailField;

    @FindBy(xpath = "//span[contains(text(),'Next')]")
    private WebElement nextButton;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//span[contains(text(),'Next')]")
    private WebElement passwordNextButton;

    // Adding an element from the inbox page to wait for after login
    @FindBy(xpath = "//div[contains(text(),'Compose')]")
    private WebElement inboxComposeButton;

    public GmailLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Wait for elements to load
    }

    public GmailComposePage login(String email, String password) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(emailField));  // Wait for email field
        emailField.sendKeys(email);
        nextButton.click();

        // Wait for the password field to appear after clicking "Next"
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.sendKeys(password);
        passwordNextButton.click(); // Click the "Next" button after entering the password

        Thread.sleep(4000);

        wait.until(ExpectedConditions.visibilityOf(inboxComposeButton));
        return new GmailComposePage(driver);
    }

    public void goTo() {
        driver.get("https://mail.google.com");
    }
}
