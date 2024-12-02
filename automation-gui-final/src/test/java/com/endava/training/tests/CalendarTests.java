package com.endava.training.tests;

import com.endava.training.Page.Calendar.*;
import com.endava.training.dto.CalendarDate;
import com.endava.training.utils.ConfigManager;
import com.endava.training.utils.DataReader;
import com.endava.training.utils.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.endava.training.constants.TestDataConstants.DATE;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CalendarTests {
    private WebDriver driver;
    private WebDriverManager webDriverManager;
    private final DataReader dateData = new DataReader(ConfigManager.getProperty("dateData"));
    private CalendarStartPage calendarStartPage;


    @BeforeEach
    public void setUp() {
        webDriverManager = new WebDriverManager();
        driver = webDriverManager.getDriver();
        calendarStartPage = new CalendarStartPage(driver);
        driver.get(ConfigManager.getProperty("CalendarURL"));
    }

    @Test
    public void stopTimer() {
        calendarStartPage.hoverTimers();
        Stopwatch stopwatch = calendarStartPage.goToStopwatch();
        assertTrue(stopwatch.getTitle().contains("Online Stopwatch"));

        stopwatch.clickStartButton();

        stopwatch.splitSecond();

        stopwatch.stopTimer("10");

        stopwatch.clickExport();

        //stopwatch.clickDownload();

    }

    @Test
    public void timerToDate() {
        calendarStartPage.hoverTimers();
        CountdownTodate countdownTodate = calendarStartPage.goToCountdownToDate();

        int selectedIndex = countdownTodate.selectRandomTimerDesign();
        WebElement selectedDesign = countdownTodate.getTimerDesign(selectedIndex);
        String classes = selectedDesign.getAttribute("class");

        assertTrue(classes.contains("active"));

        countdownTodate.clickCalendar();
        countdownTodate.clickYear();
        countdownTodate.click2025();
        countdownTodate.clickMonth();
        countdownTodate.clickFebruary();
        countdownTodate.click13();

        CalendarDate validDate = (CalendarDate) dateData.getData(DATE, CalendarDate.class);

        FinalCountdown finalCountdown = countdownTodate.finalCountdown(
                validDate.getEvent_name(),
                validDate.getHour(),
                validDate.getMinute(),
                validDate.getSecond(),
                validDate.getLocation()
        );

        String initialDays = cleanValue(finalCountdown.getDays());
        String initialHours = cleanValue(finalCountdown.getHours());
        String initialMinutes = cleanValue(finalCountdown.getMinutes());
        String initialSeconds = cleanValue(finalCountdown.getSeconds());

        assertTrue(finalCountdown.isTimerDecreased(initialDays, initialHours, initialMinutes, initialSeconds));
    }


    private String cleanValue(String text) {
        return text.replaceAll("\\D+", "");
    }

    @Test
    public void worldClockAlphabeticOrder(){
        WorldClock worldClock = calendarStartPage.goToWorldClock();

        assertTrue(worldClock.alphabeticalOrder());

        worldClock.clickSortDropdown();
        worldClock.clickCountryDropdown();

        assertTrue(worldClock.alphabeticalOrder());

    }

    @AfterEach
    public void tearDown() {
        System.out.println("Cerrando WebDriver...");
        webDriverManager.quitDriver();
    }





}
