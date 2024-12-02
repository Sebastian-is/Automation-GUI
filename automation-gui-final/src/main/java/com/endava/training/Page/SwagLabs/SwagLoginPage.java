package com.endava.training.Page.SwagLabs;

import com.endava.training.actions.WebAction;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SwagLoginPage {
    private final WebDriver driver;
    WebAction actor;

    @FindBy(id = "user-name")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    public SwagLoginPage(WebDriver driver) {
        this.driver = driver;
        actor = new WebAction(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void enterUsername(String username) {actor.sendKeys(username, usernameInput);}

    public void enterPassword(String password) {
        actor.sendKeys(password, passwordInput);
    }

    public void enterPassword2(String password) { passwordInput.sendKeys(password);}

    public void clickLogin() { actor.click(loginButton); }

    public void loginUser(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public DashBoardPage successLoginUser(String username, String password) {
        loginUser(username, password);
        return new DashBoardPage(driver);
    }

    public void failureLoginUser(String username, String password){
        loginUser(username, password);
    }
}
