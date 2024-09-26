package com.qa.config;

import java.io.IOException;
import java.io.FileInputStream;
import java.util.Properties;

public class Config {
    private static Properties properties = new Properties();

    public Config() {
        loadProperties();
    }

    private void loadProperties() {
        try (FileInputStream input = new FileInputStream("src/main/resources/config.properties")) {
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getExcelFilePath() {
        return properties.getProperty("excel.file.path");
    }

    public static String getEmailRecipient() {
        return properties.getProperty("email.recipient");
    }

    public static String getEmailSubject() {
        return properties.getProperty("email.subject");
    }

    public static String getEmailBody() {
        return properties.getProperty("email.body");
    }

    public static String getScreenshotSaveLocation() {
        return properties.getProperty("screenshot.save.location");
    }

    public static String getSmtpHost() {
        return properties.getProperty("smtp.host");
    }

    public static String getSmtpUser() {
        return properties.getProperty("smtp.user");
    }

    public static String getSmtpPassword() {
        return properties.getProperty("smtp.password");
    }
}
