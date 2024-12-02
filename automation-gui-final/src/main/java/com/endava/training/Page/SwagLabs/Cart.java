package com.endava.training.Page.SwagLabs;

import com.endava.training.actions.WebAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.endava.training.actions.WebAction.*;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    WebAction actor;
    private final WebDriver driver;

    public Cart(WebDriver driver) {
        this.driver = driver;
        this.actor = new WebAction(driver);
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(css = ".title")
    private WebElement cartTitle;

    @FindBy(css = ".cart_item")
    private List<WebElement> cartItems;

    @FindBy(css = ".btn.btn_action.btn_medium.checkout_button")
    private WebElement checkoutButton;

    public String getCartTitle(){
        return actor.getText(cartTitle);
    }

    public boolean verifyAmountOfItemsCart(int numberOfProductsToAdd) {
        return cartItems.size() == numberOfProductsToAdd;
    }

    public Checkout goToCheckout(){
        actor.click(checkoutButton);
        return new Checkout(driver);
    }

    public List<Double> getCartPrices() {
        List<Double> prices = new ArrayList<>();
        for (WebElement item : cartItems) {
            WebElement priceItem = item.findElement(By.cssSelector(".inventory_item_price"));
            String priceText = priceItem.getText().replace("$", "");
            prices.add(Double.parseDouble(priceText));
        }
        return prices;
    }

}
