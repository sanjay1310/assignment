package tests;

import base.BaseTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.time.Duration;

public class LoginPageTest extends BaseTest {

    @Test
    public void testLoginButtonDisabledWhenFieldsAreEmpty() {
        LoginPage lp = new LoginPage(driver);
        // Login button should be disabled or not enabled when fields are empty
        Assert.assertFalse(lp.isLoginButtonEnabled(), "Login button should be disabled when fields are empty");
    }

    @Test
    public void testInvalidLoginShowErrorMsg() {
        LoginPage lp = new LoginPage(driver);
        lp.enterUserId("invalidUser");
        lp.enterPassword("invalidPass");
        lp.clickLogin();

        // Use explicit wait for error to appear (adjust timeout as needed)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(driver -> !lp.getErrorMessage().isEmpty());

        String error = lp.getErrorMessage();
        System.out.println("Displayed error: " + error);
        Assert.assertTrue(error != null && !error.isEmpty(), "Error message should be shown for invalid login.");
    }

    @Test
    public void testPasswordMaskedbutton() {
        LoginPage lp = new LoginPage(driver);

        lp.enterPassword("testPassword");
        Assert.assertEquals(lp.getPasswordType(), "password", "Password should be masked initially");

        lp.clickPasswordToggle();
        Assert.assertEquals(lp.getPasswordType(), "text", "Password should be unmasked after clicking toggle");

        lp.clickPasswordToggle();
        Assert.assertEquals(lp.getPasswordType(), "password", "Password should be masked again after clicking toggle");
    }

    @Test
    public void testPresenceOfPageElements() {
        LoginPage lp = new LoginPage(driver);

        Assert.assertTrue(lp.isPageTitleVisible(), "Page title (subtitle) should be visible");
        Assert.assertTrue(driver.findElement(By.id("formEmail")).isDisplayed(), "User ID input should be visible");
        Assert.assertTrue(driver.findElement(By.id("formPassword")).isDisplayed(), "Password input should be visible");
        Assert.assertTrue(driver.findElement(By.cssSelector("button.login-button")).isDisplayed(), "Login button should be visible");
        Assert.assertTrue(driver.findElement(By.cssSelector("img.passowrd-visible")).isDisplayed(), "Password visibility toggle should be visible");
    }
}
