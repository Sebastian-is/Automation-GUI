package com.endava.training.Page.Calendar;

import com.endava.training.actions.WebAction;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class FinalCountdown {
    private final WebDriver driver;
    private final WebDriverWait wait;
    WebAction actor;


    public FinalCountdown(WebDriver driver) {
        this.driver = driver;
        actor = new WebAction(driver);
        PageFactory.initElements(this.driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(css = ".csvg-countdown__content")
    private WebElement countdownContent;

    @FindBy(css = ".csvg-countdown__content .csvg-digit[data-tad-bind='days']")
    private WebElement daysElement;

    @FindBy(css = ".csvg-countdown__content .csvg-digit[data-tad-bind='hours']")
    private WebElement hoursElement;

    @FindBy(css = ".csvg-countdown__content .csvg-digit[data-tad-bind='minutes']")
    private WebElement minutesElement;

    @FindBy(css = ".csvg-countdown__content .csvg-digit[data-tad-bind='seconds']")
    private WebElement secondsElement;

    public String getDays(){
        return actor.getText(daysElement);
    }

    public String getHours(){
        return actor.getText(hoursElement);
    }

    public String getMinutes(){
        return actor.getText(minutesElement);
    }

    public String getSeconds(){
        return actor.getNumericText(secondsElement);
    }

    private String cleanValue(String text) {
        return text.replaceAll("\\D+", ""); // Elimina todo lo que no sea un dígito.
    }



    public boolean isTimerDecreased(String initialDays, String initialHours, String initialMinutes, String initialSeconds) {
        try {
            // Espera hasta que al menos uno de los valores cambie.
            boolean hasChanged = wait.until(driver -> {
                String currentDays = cleanValue(getDays());
                String currentHours = cleanValue(getHours());
                String currentMinutes = cleanValue(getMinutes());
                String currentSeconds = cleanValue(getSeconds());

                return !currentDays.equals(initialDays) ||
                        !currentHours.equals(initialHours) ||
                        !currentMinutes.equals(initialMinutes) ||
                        !currentSeconds.equals(initialSeconds);
            });

            // Imprime los valores iniciales y actuales para depuración.
            String finalDays = cleanValue(getDays());
            String finalHours = cleanValue(getHours());
            String finalMinutes = cleanValue(getMinutes());
            String finalSeconds = cleanValue(getSeconds());

            System.out.println("Initial Values: Days=" + initialDays + ", Hours=" + initialHours + ", Minutes=" + initialMinutes + ", Seconds=" + initialSeconds);
            System.out.println("Final Values: Days=" + finalDays + ", Hours=" + finalHours + ", Minutes=" + finalMinutes + ", Seconds=" + finalSeconds);

            // Devuelve el resultado directamente (ya sabemos que al menos uno cambió).
            System.out.println("Has any timer field changed? " + hasChanged);
            return hasChanged;

        } catch (Exception e) {
            // Si ocurre un error, imprime el stack trace y devuelve false.
            e.printStackTrace();
            return false;
        }
    }





}
