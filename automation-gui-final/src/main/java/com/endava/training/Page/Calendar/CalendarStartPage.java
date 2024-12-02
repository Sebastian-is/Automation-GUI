package com.endava.training.Page.Calendar;

import com.endava.training.actions.WebAction;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CalendarStartPage {
    private final WebDriver driver;
    WebAction actor;


    public CalendarStartPage(WebDriver driver) {
        this.driver = driver;
        actor = new WebAction(driver);
        PageFactory.initElements(this.driver, this);
    }


    @FindBy(xpath = "//*[@id=\"site-nav\"]/li/a[text()=\"Timers\"]")
    private WebElement timersDropdown;

    @FindBy(xpath = "//*[@id=\"site-nav\"]//a[contains(@href, \"/stopwatch/\")]\n")
    private WebElement stopwatch;

    @FindBy(xpath = "//*[@id=\"site-nav\"]//a[contains(@href, \"/countdown/create\")]")
    private WebElement countdownToDate;

    @FindBy(xpath = "//*[@id=\"site-nav\"]/li/a[text()=\"World Clock\"]")
    private WebElement worldClock;

    public void hoverTimers(){
        Actions actions = new Actions(driver);
        actions.moveToElement(timersDropdown).perform();
    }

    public Stopwatch goToStopwatch(){
        actor.click(stopwatch);
        return new Stopwatch(driver);
    }

    public CountdownTodate goToCountdownToDate(){
        actor.click(countdownToDate);
        return new CountdownTodate(driver);
    }

    public WorldClock goToWorldClock(){
        actor.click(worldClock);
        return new WorldClock(driver);
    }
}
