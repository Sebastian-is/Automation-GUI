package com.endava.training.Page.SwagLabs;

import com.endava.training.actions.WebAction;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Checkout {

    WebAction actor;
    private final WebDriver driver;

    public Checkout(WebDriver driver) {
        this.driver = driver;
        this.actor = new WebAction(driver);
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(css = ".title")
    private WebElement checkoutTitle;

    @FindBy(css = "#first-name")
    private WebElement firstNameInput;

    @FindBy(css = "#last-name")
    private WebElement lastNameInput;

    @FindBy(css = "#postal-code")
    private WebElement postalCodeInput;

    @FindBy(css = "#continue")
    private WebElement continueButton;

    public String getCheckoutTitle(){
        return actor.getText(checkoutTitle);
    }

    public void clickContinue(){
        actor.click(continueButton);
    }

    public void enterFirstName(String firstName){
        actor.sendKeys(firstName, firstNameInput);
    }

    public void enterLastName(String lastName){
        actor.sendKeys(lastName, lastNameInput);
    }

    public void enterPostalCode(String postalCode){
        actor.sendKeys(postalCode, postalCodeInput);
    }

    public AfterCheckout fillForm(String firstName, String lastName, String postalCode){
        enterFirstName(firstName);
        enterLastName(lastName);
        enterPostalCode(postalCode);
        clickContinue();

        return new AfterCheckout(driver);
    }

}
