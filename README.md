# Selenium UI Test Project – SauceDemo

This project automates testing of [saucedemo.com](https://www.saucedemo.com) using Java, JUnit, and Selenium WebDriver with the Page Object Model.

##  Features

Note: I used Private Window of Google because of security pop-up. When it tests with basic credentials google pop-ups that it's breached password etc. Please consider this teacher.

- Login test (valid and invalid)
- Add to cart test
- Logout test
- Navigate to cart test
- Random data used in testInvalidLogin
- Test ordering using `@Order`
- Explicit waits used with `WebDriverWait`
- Organized with Page Object Model
- Tests pass locally via Gradle

## Technologies
- Java 17
- Selenium WebDriver
- JUnit 5
- WebDriverManager
- Gradle

## Run Locally
```bash
gradle test

