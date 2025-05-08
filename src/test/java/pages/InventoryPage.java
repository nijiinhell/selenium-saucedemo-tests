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
    
    // Add the first product on the page to the cart
    public void addFirstItemToCart() {
        driver.findElement(By.cssSelector(".inventory_item button")).click();
    }

    // Navigate to the cart page
    public void goToCart() {
        driver.findElement(By.className("shopping_cart_link")).click();
    }
}


    // Opens the sidebar menu and clicks the logout link
    public void logout() {
      driver.findElement(By.id("react-burger-menu-btn")).click();

    // Wait briefly for the menu to slide open (in real tests, use WebDriverWait)
      try {
          Thread.sleep(500); // quick wait just for stability
      } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
      }

      driver.findElement(By.id("logout_sidebar_link")).click();
}

    // Clicks on the cart icon to go to the cart page
    public void goToCart() {
      driver.findElement(By.className("shopping_cart_link")).click();
}

