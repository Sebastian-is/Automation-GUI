package com.endava.training.Page.SwagLabs;

import com.endava.training.actions.WebAction;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutComplete {
    WebAction actor;
    private final WebDriver driver;

    public CheckoutComplete(WebDriver driver) {
        this.driver = driver;
        this.actor = new WebAction(driver);
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(css = ".complete-header")
    private WebElement thankYouText;

    public String checkFinalMessage(){
        return actor.getText(thankYouText);
    }

}
