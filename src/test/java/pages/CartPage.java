package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Represents the cart page where added products are listed.
 * We use it to verify that items were added successfully.
 */
public class CartPage {
    private final WebDriver driver;

    // Selector for any cart item — we'll check if at least one exists
    private final By cartItem = By.className("cart_item");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    // Returns true if there's at least one item listed in the cart
    public boolean hasItems() {
        return driver.findElements(cartItem).size() > 0;
    }
}

