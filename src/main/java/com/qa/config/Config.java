package com.qa.config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private Properties prop;

    public Config() {
        loadProperties();
    }

    // Load properties from the config file
    private void loadProperties() {
        prop = new Properties();
        try (FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\config.properties")){
            prop.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Encapsulated access to config properties
    public String getProperty(String key) {
        return prop.getProperty(key);
    }

    // Get the properties object
    public Properties getProperties() {
        return prop;
    }

    // Save updated properties to the config file
    public void saveProperties() {
        try (FileOutputStream output = new FileOutputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\config.properties")) {
            prop.store(output, "New Added");  // Store the updated properties back to the file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to set a property value and save it to the file
    public void setProperty(String key, String value) {
        prop.setProperty(key, value);
        saveProperties();  // Save the updated properties back to the file
    }
}









//
//import java.io.IOException;
//import java.io.FileInputStream;
//import java.util.Properties;
//
//public class Config {
//    private static Properties properties = new Properties();
//
//    public Config() {
//        loadProperties();
//    }
//
//    private void loadProperties() {
//        try (FileInputStream input = new FileInputStream("src/main/resources/config.properties")) {
//            properties.load(input);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public static String getExcelFilePath() {
//        return properties.getProperty("excel.file.path");
//    }
//
//    public static String getEmailRecipient() {
//        return properties.getProperty("email.recipient");
//    }
//
//    public static String getEmailSubject() {
//        return properties.getProperty("email.subject");
//    }
//
//    public static String getEmailBody() {
//        return properties.getProperty("email.body");
//    }
//
//    public static String getScreenshotSaveLocation() {
//        return properties.getProperty("screenshot.save.location");
//    }
//
//    public static String getSmtpHost() {
//        return properties.getProperty("smtp.host");
//    }
//
//    public static String getSmtpUser() {
//        return properties.getProperty("smtp.user");
//    }
//
//    public static String getSmtpPassword() {
//        return properties.getProperty("smtp.password");
//    }
//}
