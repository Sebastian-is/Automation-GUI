package com.endava.training.Page.SwagLabs;

import com.endava.training.actions.WebAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class DashBoardPage {
    WebAction actor;
    private final WebDriver driver;

    @FindBy(className = "app_logo")
    private WebElement postLoginTitle;

    @FindBy(css = ".product_sort_container")
    private WebElement filterDropdown;

    @FindBy(css = ".product_sort_container option[value='hilo']")
    private  WebElement filterHighLow;

    @FindBy(css = ".inventory_item_price")
    private List<WebElement> priceElements;

    @FindBy(css = ".btn.btn_primary.btn_small.btn_inventory")
    private List<WebElement> addToCartButtons;

    @FindBy(css = ".btn.btn_secondary.btn_small.btn_inventory")
    private WebElement removeButton;

    @FindBy(css = ".shopping_cart_badge")
    private WebElement shoppingCartBadge;

    @FindBy(css = ".inventory_item")
    private  List<WebElement> products;

    @FindBy(css = ".shopping_cart_link")
    private WebElement shoppingCart;


    public DashBoardPage(WebDriver driver) {
        this.driver = driver;
        this.actor = new WebAction(driver);
        PageFactory.initElements(this.driver, this);
    }

    public String getDashboardTitle() {
        return actor.getText(postLoginTitle);
    }

    public void clickFilterDropdown() {
        actor.click(filterDropdown);
    }

    public Cart goToCart() {
        actor.click(shoppingCart);
        return new Cart(driver);
    }


    public boolean isFilterDropdownVisible() {
        return actor.isVisible(filterDropdown);
    }

    public void clickFilterHighLow() {
        actor.click(filterHighLow);
    }

    public void clickFirstAddToCart(){
        actor.click(addToCartButtons.get(0));
    }

    public boolean removeButtonVisible(){
        return actor.isVisible(removeButton);
    }

    public boolean shoppingCartBadgeVisible(){
        return actor.isVisible(shoppingCartBadge);
    }

    public void addRandomProductToCart() {
        Random random = new Random();

        if (products.isEmpty()) {
            throw new IllegalStateException("No hay productos disponibles para añadir.");
        }

        List<WebElement> availableProducts = products.stream()
                .filter(product -> !product.findElements(By.cssSelector(".btn.btn_primary.btn_small.btn_inventory")).isEmpty())
                .collect(Collectors.toList());  // Usamos Collectors.toList() en lugar de toList()

        if (availableProducts.isEmpty()) {
            throw new IllegalStateException("No hay productos disponibles para añadir al carrito.");
        }

        int randomIndex = random.nextInt(availableProducts.size());
        WebElement randomProduct = availableProducts.get(randomIndex);

        WebElement addButton = randomProduct.findElement(By.cssSelector(".btn.btn_primary.btn_small.btn_inventory"));
        addButton.click();

        System.out.println("Añadido producto #" + (randomIndex + 1));
    }




    public List<Double> getPricesAsDoubles() {
        return priceElements.stream()
                .map(WebElement::getText)
                .map(price -> price.replace("$", ""))
                .map(Double::parseDouble)
                .collect(Collectors.toList());
    }

    public int getCartBadgeCount() {
        if (actor.isVisible(shoppingCartBadge)) {
            String badgeText = actor.getText(shoppingCartBadge);
            return Integer.parseInt(badgeText);
        }
        return 0;
    }



}
