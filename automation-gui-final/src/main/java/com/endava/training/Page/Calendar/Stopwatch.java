package com.endava.training.Page.Calendar;

import com.endava.training.actions.WebAction;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Stopwatch {

    private final WebDriver driver;
    WebAction actor;

    public Stopwatch(WebDriver driver) {
        this.driver = driver;
        actor = new WebAction(driver);
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(css = "div.duration-time-display.stopwatch__duration > span.duration-time-display__unit.duration-time-display__unit--seconds")
    private WebElement timerSeconds;

    @FindBy(css = ".stopwatch__action--start")
    private WebElement startButton;

    @FindBy(css = ".headline-banner__title")
    private WebElement stopwatchTitle;

    @FindBy(css = "button.stopwatch__action--split")
    private WebElement splitButton;

    @FindBy(css = ".form-submit")
    private WebElement exportButton;

    @FindBy(xpath = "//button[text()=' Download as Text File']")
    private WebElement downloadButton;


    public String getSeconds(){
        String seconds = actor.getText(timerSeconds);
        return seconds.replace(".", "");
    }

    public void clickStartButton(){
        actor.click(startButton);
    }

    public String getTitle(){
        return actor.getText(stopwatchTitle);
    }

    public void stopTimer(String duration) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        long durationInMillis = Long.parseLong(duration) * 1000;

        wait.until(driver -> {
            String currentSeconds = getSeconds();
            long currentTimeInMillis = parseTimeToMillis(currentSeconds);
            return currentTimeInMillis >= durationInMillis;
        });

        WebElement stopBtn = wait.until(ExpectedConditions.visibilityOf(startButton));
        wait.until(ExpectedConditions.elementToBeClickable(stopBtn));

        actor.click(startButton);
    }

    private long parseTimeToMillis(String time) {
        return Long.parseLong(time) * 1000;
    }

    public void splitSecond() {
        String initialTime = getSeconds();

        for (int i = 0; i < 10; i++) {

            while (getSeconds().equals(initialTime)) {

            }

            actor.click(splitButton);

            initialTime = getSeconds();
        }
    }

    public void clickExport(){
        actor.click(exportButton);
    }

    

}
