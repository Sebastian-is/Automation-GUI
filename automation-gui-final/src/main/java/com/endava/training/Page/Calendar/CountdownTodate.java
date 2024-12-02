package com.endava.training.Page.Calendar;

import com.endava.training.actions.WebAction;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.List;
import java.util.Random;

public class CountdownTodate {
    private final WebDriver driver;
    WebAction actor;


    public CountdownTodate(WebDriver driver) {
        this.driver = driver;
        actor = new WebAction(driver);
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(css = ".two.columns.mob-6")
    private List<WebElement> timerDesigns;

    @FindBy(css = ".ten")
    private WebElement countdownTitle;

    @FindBy(css = "select#font")
    private WebElement fontStyleDropwdown;

    @FindBy(css = "[vale=sanserif]")
    private WebElement sanserifDropdownOption;

    @FindBy(css = ".btn-datepicker")
    private WebElement calendarButton;

    @FindBy(css = ".picked-month")
    private WebElement calendarMonth;

    @FindBy(xpath = "//table//a[contains(text(), 'feb')]")
    private WebElement februaryMonth;

    @FindBy(xpath = "//table//a[contains(text(), '13')]")
    private WebElement day13;

    @FindBy(xpath = "//table//a[contains(text(), '2025')]")
    private WebElement year2025;

    @FindBy(xpath = "//*[@placeholder='hh']")
    private WebElement hourField;

    @FindBy(css = "input#min")
    private WebElement minuteField;

    @FindBy(css = "input#sec")
    private WebElement secondField;

    @FindBy(css = "input#p0txt")
    private WebElement locationField;

    @FindBy(css = "#ud0")
    private WebElement daysHoursMinutesSecondsCheck;

    @FindBy(css = ".txt-small")
    private WebElement createCountdownButton;

    @FindBy(css = ".picked-year")
    private WebElement calendarYear;

    public int selectRandomTimerDesign() {
        Random random = new Random();
        int randomIndex = random.nextInt(timerDesigns.size());

        WebElement design = timerDesigns.get(randomIndex);
        actor.click(design);

        return randomIndex;
    }

    public WebElement getTimerDesign(int index) {
        return timerDesigns.get(index);
    }


    public void eventName(String eventName){
        actor.sendKeys(eventName,countdownTitle);
    }

    public void clickCalendar() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));  // Espera de 30 segundos
        wait.until(ExpectedConditions.elementToBeClickable(calendarButton));  // Espera a que el calendario sea clickeable
        actor.click(calendarButton);  // Realiza el click
    }

    public void clickMonth() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));  // Espera de 30 segundos
        wait.until(ExpectedConditions.elementToBeClickable(calendarMonth));  // Espera a que el mes sea clickeable
        actor.click(calendarMonth);  // Realiza el click
    }

    public void clickFebruary() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));  // Espera de 30 segundos
        wait.until(ExpectedConditions.elementToBeClickable(februaryMonth));  // Espera a que febrero sea clickeable
        actor.click(februaryMonth);  // Realiza el click
    }

    public void click13() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));  // Espera de 30 segundos
        wait.until(ExpectedConditions.elementToBeClickable(day13));  // Espera a que el día 13 sea clickeable
        actor.click(day13);  // Realiza el click
    }

    public void clickYear() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));  // Espera de 30 segundos
        wait.until(ExpectedConditions.elementToBeClickable(calendarYear));  // Espera a que el año sea clickeable
        actor.click(calendarYear);  // Realiza el click
    }

    public void click2025() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));  // Espera de 30 segundos
        wait.until(ExpectedConditions.elementToBeClickable(year2025));  // Espera a que el año 2025 sea clickeable
        actor.click(year2025);  // Realiza el click
    }

    public void hour(String dateHour){
        actor.sendKeys(dateHour,hourField);
    }

    public void minute(String dateMinute){
        actor.sendKeys(dateMinute,minuteField);
    }

    public void second(String dateSecond){
        actor.sendKeys(dateSecond,secondField);
    }

    public void location(String dateLocation){
        actor.sendKeys(dateLocation,locationField);
    }

    public void daysHoursMinutesSeconds(){
        actor.click(daysHoursMinutesSecondsCheck);
    }

    public void fillDateData(String eventName, String dateHour, String dateMinute, String dateSecond,String dateLocation ){
        eventName(eventName);
        hour(dateHour);
        minute(dateMinute);
        second(dateSecond);
        location(dateLocation);
        actor.click(createCountdownButton);
        actor.click(createCountdownButton);
    }

    public FinalCountdown finalCountdown(String eventName, String dateHour, String dateMinute, String dateSecond, String dateLocation){
        fillDateData(eventName,dateHour,dateMinute,dateSecond,dateLocation);
        return new FinalCountdown(driver);
    }



}
