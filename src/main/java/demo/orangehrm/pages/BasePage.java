package demo.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openUrl(String url) {
        driver.get(url);
    }

    public WebElement waitForElementPresence(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public WebElement waitForElementClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getText(By locator) {
        return waitForElementPresence(locator).getText();
    }

    public String getCssValueOf(By locator, String propertyName) {
        return waitForElementPresence(locator).getCssValue(propertyName);
    }

    public String getAttribute(By locator, String attributeName) {
        return waitForElementPresence(locator).getAttribute(attributeName);
    }

    public void sendKeys(By locator, String text) {
        WebElement element = waitForElementPresence(locator);
        element.clear();
        element.sendKeys(text);
    }

    public void click(By locator) {
        waitForElementClickable(locator).click();
    }

    public void submitForm(By locator) {
        waitForElementPresence(locator).submit();
    }

    public boolean isElementEnable(By locator) {
        return waitForElementPresence(locator).isEnabled();
    }

    public boolean isElementDisplay(By locator) {
        return waitForElementPresence(locator).isDisplayed();
    }

    public boolean isElementSelected(By locator) {
        return waitForElementPresence(locator).isSelected();
    }
}
