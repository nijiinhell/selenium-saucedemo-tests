package pages;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


/**
 * Represents the product listing page you land on after logging in.
 * This class lets us interact with items, the cart, and logout actions.
 */

public class InventoryPage {
    private final WebDriver driver;

    // The whole product container — we use this to verify we're on the correct page
    private final By inventoryContainer = By.id("inventory_container");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    // Checks if the inventory page loaded correctly by verifying the main product container
    public boolean isLoaded() {
        return driver.findElement(inventoryContainer).isDisplayed();
    }



    // Clicks the "Add to cart" button for the first product on the page
    // (All products have the same class, so we just grab the first match)
    public void addFirstItemToCart() {
        driver.findElement(By.cssSelector("button.btn_inventory")).click();
    }


    // After adding to cart, this badge should show up with the item count
    public boolean isCartBadgeVisible() {
        return driver.findElement(By.className("shopping_cart_badge")).isDisplayed();
    }


    // Opens the hamburger menu and logs the user out by clicking the logout option
    public void logout() {
    driver.findElement(By.id("react-burger-menu-btn")).click();

    // Wait for the logout option to become clickable, then click it
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    wait.until(ExpectedConditions.elementToBeClickable(By.id("logout_sidebar_link"))).click();

    // After logout, the login page should load, so we wait for the login button
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
}



    // Clicks the shopping cart icon to go to the cart page
    public void goToCart() {
        driver.findElement(By.className("shopping_cart_link")).click();
    }
}

