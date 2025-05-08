package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import pages.InventoryPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This test checks if logging into saucedemo.com works correctly.
 * We go from the login page to the products page, and confirm everything looks good.
 */
public class LoginTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        // Set up the Chrome browser using WebDriverManager (no manual driver needed)
        WebDriverManager.chromedriver().setup();

        // Launch a new Chrome browser
        driver = new ChromeDriver();

        // Maximize just to avoid layout issues during testing
        driver.manage().window().maximize();

        // Open the site — we start at the login page
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void testSuccessfulLogin() {
        // Try to log in using valid demo credentials
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        // If login works, we should land on the inventory page
        InventoryPage inventoryPage = new InventoryPage(driver);

        // Make sure the inventory page is actually visible
        assertTrue(inventoryPage.isLoaded(), "We should be on the inventory page after login.");
    }

    @AfterEach
    public void tearDown() {
        // Close the browser so it doesn't stay open after tests
        if (driver != null) {
            driver.quit();
        }
    }
}
   
    @Test
    public void testInvalidLogin() {
    // Try logging in with incorrect credentials
       LoginPage loginPage = new LoginPage(driver);
       loginPage.login("wrong_user", "wrong_pass");

    // Check if the error message is displayed
       assertTrue(loginPage.isLoginErrorVisible(), "Login failed as expected — error message is displayed.");
    }

    @Test
    public void testAddItemToCart() {
    // Log in first
      LoginPage loginPage = new LoginPage(driver);
      loginPage.login("standard_user", "secret_sauce");

    // On the inventory page, add the first product to the cart
      InventoryPage inventoryPage = new InventoryPage(driver);
      inventoryPage.addFirstItemToCart();
      inventoryPage.goToCart();

    // On the cart page, verify that the item appears
      CartPage cartPage = new CartPage(driver);
      assertTrue(cartPage.hasItems(), "Item was successfully added to the cart.");
}

    @Test
    public void testLogout() {
    // Log in first
      LoginPage loginPage = new LoginPage(driver);
      loginPage.login("standard_user", "secret_sauce");

    // Log out using the side menu
      InventoryPage inventoryPage = new InventoryPage(driver);
      inventoryPage.logout();

    // After logout, we should be back at the login page (check login button visible)
      assertTrue(driver.findElement(By.id("login-button")).isDisplayed(), "Logout successful — login button is visible.");
}

    @Test
    public void testNavigateToCartPage() {
    // Log in
      LoginPage loginPage = new LoginPage(driver);
      loginPage.login("standard_user", "secret_sauce");

      InventoryPage inventoryPage = new InventoryPage(driver);
      inventoryPage.goToCart();

    // Verify we're on the cart page by checking for the cart title
    boolean cartTitleVisible = driver.findElement(By.className("title")).getText().equals("Your Cart");
      assertTrue(cartTitleVisible, "User was navigated to the cart page.");
}

