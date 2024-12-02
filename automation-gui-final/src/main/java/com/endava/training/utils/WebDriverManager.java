package com.endava.training.utils;

import com.endava.training.utils.ConfigManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverManager {
    private WebDriver driver;

    public WebDriver getDriver() {
            String browser = ConfigManager.getProperty("browser").toLowerCase();
            Dimension screen_dimension = new Dimension(Integer.parseInt(ConfigManager.getProperty("width")),Integer.parseInt(ConfigManager.getProperty("height")));
            switch (browser) {
                case "firefox":
                    System.setProperty("webdriver.gecko.driver", ConfigManager.getProperty("geckoDriverPath"));
                    driver = new FirefoxDriver();
                    driver.manage().window().setSize(screen_dimension);
                    break;
                case "chrome":
                    System.setProperty("webdriver.chrome.driver", ConfigManager.getProperty("chromeDriverPath"));
                    driver = new ChromeDriver();
                    driver.manage().window().setSize(screen_dimension);
                    break;
                default:
                    System.out.println("Option "+browser+" doesn't exists, please select a valid browser");
            }

        return driver;
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}