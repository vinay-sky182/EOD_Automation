package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GmailLoginPage {
    private WebDriver driver;

    @FindBy(id = "identifierId")
    private WebElement emailField;

    @FindBy(xpath = "//span[contains(text(),'Next')]")
    private WebElement nextButton;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//span[contains(text(),'Next')]")
    private WebElement passwordNextButton;

    public GmailLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void login(String email, String password) {
        emailField.sendKeys(email);
        nextButton.click();
        // Add some wait to ensure the password field appears
        try {
            Thread.sleep(2000);  // Consider replacing with WebDriverWait for a more robust solution
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        passwordField.sendKeys(password);
        nextButton.click();
    }
}


