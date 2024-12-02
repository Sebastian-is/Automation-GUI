package com.endava.training.Page.SwagLabs;

import com.endava.training.actions.WebAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class AfterCheckout {
    WebAction actor;
    private final WebDriver driver;

    public AfterCheckout(WebDriver driver) {
        this.driver = driver;
        this.actor = new WebAction(driver);
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(css = ".title")
    private WebElement afterCheckoutTitle;

    @FindBy(css = ".summary_subtotal_label")
    private WebElement itemTotal;

    @FindBy(css = "#finish")
    private WebElement finishButton;

    public String checkCheckoutTitle(){
        return actor.getText(afterCheckoutTitle);
    }

    public String checkItemTotal(){
        String rawText = actor.getText(itemTotal);
        return rawText.replace("$", "").trim();
    }

    public CheckoutComplete clickFinishButton(){
        actor.click(finishButton);
        return new CheckoutComplete(driver);
    }





}
