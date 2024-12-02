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
        // Paso 1: Navegar al temporizador
        calendarStartPage.hoverTimers();
        CountdownTodate countdownTodate = calendarStartPage.goToCountdownToDate();

        // Paso 2: Seleccionar un diseño de temporizador aleatorio
        int selectedIndex = countdownTodate.selectRandomTimerDesign();
        WebElement selectedDesign = countdownTodate.getTimerDesign(selectedIndex);
        String classes = selectedDesign.getAttribute("class");

        // Verificar que el diseño seleccionado esté activo
        assertTrue(classes.contains("active"));

        // Paso 3: Configurar la fecha y hora del evento
        countdownTodate.clickCalendar();
        countdownTodate.clickYear();
        countdownTodate.click2025();
        countdownTodate.clickMonth();
        countdownTodate.clickFebruary();
        countdownTodate.click13();

        // Obtener datos válidos de prueba
        CalendarDate validDate = (CalendarDate) dateData.getData(DATE, CalendarDate.class);

        // Crear el temporizador final
        FinalCountdown finalCountdown = countdownTodate.finalCountdown(
                validDate.getEvent_name(),
                validDate.getHour(),
                validDate.getMinute(),
                validDate.getSecond(),
                validDate.getLocation()
        );

        // Paso 4: Capturar los valores iniciales del temporizador
        String initialDays = cleanValue(finalCountdown.getDays());
        System.out.println("Initial Days: " + initialDays);
        String initialHours = cleanValue(finalCountdown.getHours());
        System.out.println("Initial Hours: " + initialHours);
        String initialMinutes = cleanValue(finalCountdown.getMinutes());
        System.out.println("Initial Minutes: " + initialMinutes);
        String initialSeconds = cleanValue(finalCountdown.getSeconds());
        System.out.println("Initial Seconds: " + initialSeconds);

        // Paso 5: Verificar que el temporizador ha disminuido
        assertTrue(finalCountdown.isTimerDecreased(initialDays, initialHours, initialMinutes, initialSeconds));
    }

    // Método de limpieza de valores
    private String cleanValue(String text) {
        return text.replaceAll("\\D+", ""); // Elimina todo lo que no sea un dígito
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
