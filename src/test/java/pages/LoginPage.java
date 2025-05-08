package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Represents the login page of saucedemo.com.
 * Handles all the interactions with the login fields and button.
 */
public class LoginPage {
    private final WebDriver driver;

    // Grab the input fields and button by their IDs — easy and reliable
    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Type the username into the login form
    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    // Type the password into the form
    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    // Click the login button
    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    // Quick shortcut: login using both fields and the button in one go
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    // Check if an error message is displayed after failed login
    public boolean isLoginErrorVisible() {
       return driver.findElement(By.cssSelector("h3[data-test='error']")).isDisplayed();
    }
}
