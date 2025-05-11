package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.InventoryPage;
import pages.LoginPage;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {
    private WebDriver driver;

     @BeforeEach
     public void setUp() {
    WebDriverManager.chromedriver().setup();

    // Create Chrome options
    ChromeOptions options = new ChromeOptions();

    // Incognito mode disables saved passwords and extensions
    options.addArguments("--incognito");

    // Create a random temp profile folder so Chrome is always fresh
    String tempProfile = System.getProperty("java.io.tmpdir") + System.nanoTime();
    options.addArguments("--user-data-dir=" + tempProfile);

    // Disable annoying features explicitly
    options.addArguments("--disable-notifications");
    options.addArguments("--disable-popup-blocking");
    options.addArguments("--disable-save-password-bubble");
    options.addArguments("--disable-infobars");
    options.addArguments("--no-default-browser-check");
    options.addArguments("--disable-extensions");
    options.addArguments("--disable-autofill-keyboard-accessory-view");
    options.addArguments("--disable-blink-features=AutomationControlled");

    driver = new ChromeDriver(options);
    driver.manage().window().maximize();
    driver.get("https://www.saucedemo.com/");
}



    @Test
    public void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        assertTrue(inventoryPage.isLoaded(), "Inventory page should be visible after login.");
    }

    @Test
    public void testInvalidLogin() {
      LoginPage loginPage = new LoginPage(driver);

    // Generate random username to simulate unknown login
      String randomUsername = "user" + System.currentTimeMillis();
      loginPage.login(randomUsername, "wrong_pass");

      assertTrue(loginPage.isLoginErrorVisible(), "Login failed as expected — error message is displayed.");
}


    @Test
    public void testAddItemToCart() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addFirstItemToCart();
        assertTrue(inventoryPage.isCartBadgeVisible(), "Cart badge appeared — item was added successfully.");
    }

    @Test
    public void testLogout() {
      LoginPage loginPage = new LoginPage(driver);
      loginPage.login("standard_user", "secret_sauce");

      InventoryPage inventoryPage = new InventoryPage(driver);
      inventoryPage.logout();

    // Optional, but good to explicitly check logout result
      assertTrue(driver.findElement(By.id("login-button")).isDisplayed(), "Login button is visible after logout.");
}

    @Test
    public void testNavigateToCartPage() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.goToCart();

        boolean cartTitleVisible = driver.findElement(By.className("title")).getText().equals("Your Cart");
        assertTrue(cartTitleVisible, "User was navigated to the cart page.");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}



