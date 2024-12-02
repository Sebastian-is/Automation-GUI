package com.endava.training.tests;

import com.endava.training.Page.SwagLabs.*;
import com.endava.training.dto.Form;
import com.endava.training.dto.User;
import com.endava.training.utils.ConfigManager;
import com.endava.training.utils.DataReader;
import com.endava.training.utils.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static com.endava.training.constants.TestDataConstants.*;
import static org.junit.jupiter.api.Assertions.*;

public class swagLabsTests {
    private WebDriver driver;
    private WebDriverManager webDriverManager;
    private final DataReader userData = new DataReader(ConfigManager.getProperty("loginTestData"));
    private final DataReader formData = new DataReader(ConfigManager.getProperty("formData"));
    private SwagLoginPage swagLoginPage;


    @BeforeEach
    public void setUp() {
        webDriverManager = new WebDriverManager();
        driver = webDriverManager.getDriver();
        swagLoginPage = new SwagLoginPage(driver);
        driver.get(ConfigManager.getProperty("LabsURL"));
    }

    @Test
    public void highestPrice() {
        User validUser= userData.getData(STANDARD_USER, User.class);

        DashBoardPage dashboardPage = swagLoginPage.successLoginUser(validUser.getUsername(), validUser.getPassword());

        assertTrue(dashboardPage.getDashboardTitle().contains("Swag Labs"));

        dashboardPage.clickFilterDropdown();
        assertTrue(dashboardPage.isFilterDropdownVisible(),"El dropdown deberia ser visible");

        dashboardPage.clickFilterHighLow();

        List<Double> prices = dashboardPage.getPricesAsDoubles();
        assertFalse(prices.isEmpty(), "La lista de precios no debería estar vacía.");

        Double highestPrice = prices.get(0);
        for (int i = 1; i < prices.size(); i++) {
            assertTrue(highestPrice >= prices.get(i),
                    String.format("El precio más alto %.2f no es mayor o igual que %.2f", highestPrice, prices.get(i)));
        }

        dashboardPage.clickFirstAddToCart();

        assertTrue(dashboardPage.removeButtonVisible(), "El boton de remover el objeto del carrito deberia ser visible");
        assertTrue(dashboardPage.shoppingCartBadgeVisible());

    }

    @Test
    public void multipleItemCart(){
        User validUser= userData.getData(STANDARD_USER, User.class);

        DashBoardPage dashboardPage = swagLoginPage.successLoginUser(validUser.getUsername(), validUser.getPassword());

        assertTrue(dashboardPage.getDashboardTitle().contains("Swag Labs"));

        int numberOfProductsToAdd = 3;

        for (int i = 0; i < numberOfProductsToAdd; i++) {
            dashboardPage.addRandomProductToCart();
        }

        int badgeCount = dashboardPage.getCartBadgeCount();
        assertEquals(numberOfProductsToAdd, badgeCount, "El carrito debería tener " + numberOfProductsToAdd + " artículos.");

        Cart cart = dashboardPage.goToCart();
        assertTrue(cart.getCartTitle().contains("Your Cart"));
        assertTrue(cart.verifyAmountOfItemsCart(numberOfProductsToAdd));

        List<Double> pricesCart = cart.getCartPrices();

        Checkout checkout = cart.goToCheckout();

        assertTrue(checkout.getCheckoutTitle().contains("Checkout: Your Information"));

        Form validForm = formData.getData(FILL_FORM, Form.class);

        AfterCheckout afterCheckout = checkout.fillForm(validForm.getFirstName(), validForm.getLastName(), validForm.getPostalCode());

        assertTrue(afterCheckout.checkCheckoutTitle().contains("Checkout: Overview"));

        double sum = 0;
        for (Double aDouble : pricesCart) {
            sum += aDouble;
        }
        String expectedTotal = "Item total: " + String.format("%.2f", sum);

        assertEquals(expectedTotal, afterCheckout.checkItemTotal());

        CheckoutComplete checkoutComplete = afterCheckout.clickFinishButton();

        assertTrue(checkoutComplete.checkFinalMessage().contains("Thank you for your order!"));

    }

    @AfterEach
    public void tearDown() {
        webDriverManager.quitDriver();
    }
}
