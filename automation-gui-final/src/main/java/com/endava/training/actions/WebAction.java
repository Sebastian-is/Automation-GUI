package com.endava.training.actions;

import com.endava.training.utils.ScreenshotUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class WebAction {
    WebDriver driver;

    public WebAction(WebDriver driver){
        this.driver=driver;
    }

    public String getText(WebElement element) {
        return getText(element, false);  // Llama el método por defecto sin modificar el texto.
    }

    public String getNumericText(WebElement element) {
        return getText(element, true);   // Llama el método para obtener solo los números.
    }

    private String getText(WebElement element, boolean numericOnly) {
        String text = "";
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.of(10, ChronoUnit.SECONDS));
            WebElement clickableElement = wait.until(ExpectedConditions.visibilityOf(element));
            if (clickableElement.isDisplayed()) {
                text = clickableElement.getText();
                if (numericOnly) {
                    text = text.replaceAll("[^0-9]", ""); // Filtra solo los números.
                }
            }
        } catch (NoSuchElementException e) {
            takeScreenShot("error_getting_text_from_" + element);
            throw e;
        }
        return text;
    }
    public void click(WebElement element){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.of(10, ChronoUnit.SECONDS));
            WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(element));
            if (clickableElement.isDisplayed()) {
                clickableElement.click();
            }
        }catch (NoSuchElementException e){
            takeScreenShot("error_clicking_"+element);
            throw e;
        }
    }

    public void sendKeys(String input, WebElement element){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.of(10, ChronoUnit.SECONDS));
            WebElement visibleElement = wait.until(ExpectedConditions.visibilityOf(element));
            if (visibleElement.isEnabled()) {
                visibleElement.clear();
                visibleElement.sendKeys(input);
            }
        }catch (NoSuchElementException e){
            takeScreenShot("error_typing_on_"+element);
            throw e;
        }
    }

    public void takeScreenShot(String message){
        ScreenshotUtils.takeScreenshot(driver, message + System.currentTimeMillis());
    }

    public boolean isVisible(WebElement element) {
        return element.isDisplayed();
    }

}
