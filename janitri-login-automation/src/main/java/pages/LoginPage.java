package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private WebDriver driver;

    // Locators based on updated HTML
    private final By userIdInput = By.id("formEmail");
    private final By passwordInput = By.id("formPassword");
    private final By loginButton = By.cssSelector("button.login-button");
    private final By passwordToggle = By.cssSelector("img.passowrd-visible"); // Clickable img for toggle
    private final By pageTitle = By.cssSelector("p.sub-title");
    // You might need to update this once actual error message element is located.
    private final By errorMsg = By.xpath("//*[contains(text(),'Invalid') or contains(text(),'incorrect') or contains(@class,'error')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isLoginButtonEnabled() {
        WebElement btn = driver.findElement(loginButton);
        // Check for disabled attribute (if any) or CSS-based disabled class
        String disabledAttr = btn.getAttribute("disabled");
        if (disabledAttr != null) {
            return false;
        }
        String classes = btn.getAttribute("class");
        if (classes != null && classes.contains("disabled")) {
            return false;
        }
        return true;
    }

    public void enterUserId(String userId) {
        WebElement userField = driver.findElement(userIdInput);
        userField.clear();
        userField.sendKeys(userId);
    }

    public void enterPassword(String password) {
        WebElement passField = driver.findElement(passwordInput);
        passField.clear();
        passField.sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public void clickPasswordToggle() {
        driver.findElement(passwordToggle).click();
    }

    public String getPasswordType() {
        return driver.findElement(passwordInput).getAttribute("type");
    }

    public String getErrorMessage() {
        try {
            return driver.findElement(errorMsg).getText();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isPageTitleVisible() {
        try {
            return driver.findElement(pageTitle).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
