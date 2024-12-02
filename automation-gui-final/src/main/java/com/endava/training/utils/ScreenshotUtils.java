package com.endava.training.utils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ScreenshotUtils {

    public static void takeScreenshot(WebDriver driver, String fileName) {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Path destinationPath = Paths.get("./screenshots", fileName + ".png");
        try {
            Files.createDirectories(destinationPath.getParent());
            Files.copy(scrFile.toPath(), destinationPath);
        } catch (IOException e) {
            System.err.println("Error while saving screenshot: " + e.getMessage());
        }
    }
}
