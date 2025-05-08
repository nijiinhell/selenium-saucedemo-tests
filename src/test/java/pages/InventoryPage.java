package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Represents the inventory (product list) page after login.
 * We use this to confirm that the login was successful.
 */
public class InventoryPage {
    private final WebDriver driver;

    // This is the main area that shows up after logging in — if we see this, we're in
    private final By inventoryContainer = By.id("inventory_container");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    // Check if the inventory container is visible — that's how we know login worked
    public boolean isLoaded() {
        return driver.findElement(inventoryContainer).isDisplayed();
    }
}

