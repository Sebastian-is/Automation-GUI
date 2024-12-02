package com.endava.training.Page.Calendar;

import com.endava.training.actions.WebAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class WorldClock {
    private final WebDriver driver;
    WebAction actor;


    public WorldClock(WebDriver driver) {
        this.driver = driver;
        actor = new WebAction(driver);
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(css = "table.zebra.fw.tb-theme tbody tr")
    List<WebElement> countriesTableRows;

    @FindBy(css = "#sort")
    private WebElement sortDropdown;

    @FindBy(xpath = "//select[@id='sort']/option[text()='Country']")
    private WebElement dropdownCountryOption;

    public boolean alphabeticalOrder() {
        int columnCount = countriesTableRows.get(0).findElements(By.tagName("td")).size();

        for (int col = 0; col < columnCount; col++) {
            List<String> columnCountries = new ArrayList<>();

            for (WebElement row : countriesTableRows) {
                List<WebElement> cells = row.findElements(By.tagName("td"));
                if (col < cells.size()) {
                    String countryText = cells.get(col).getText().trim().replaceAll("\\s+", " "); // Elimina espacios extra
                    columnCountries.add(countryText);
                }
            }

            if (col == 0) {
                List<String> sortedCountries = new ArrayList<>(columnCountries);
                sortedCountries.sort(String.CASE_INSENSITIVE_ORDER);

                System.out.println("Original column (" + (col + 1) + "): " + columnCountries);
                System.out.println("Sorted column (" + (col + 1) + "): " + sortedCountries);

                if (!sortedCountries.equals(columnCountries)) {
                    return false;
                }
            }
        }

        return true;
    }

    public void clickSortDropdown(){
        actor.click(sortDropdown);
    }
    public void clickCountryDropdown(){
        actor.click(dropdownCountryOption);
    }



}
