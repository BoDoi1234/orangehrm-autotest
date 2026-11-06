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
        driver.manage().window().maximize();
        driver.get(url);
    }

    public WebElement waitForElementVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForElementClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getText(By locator) {
        return waitForElementVisible(locator).getText();
    }

    public String getCssValueOf(By locator, String propertyName) {
        return waitForElementVisible(locator).getCssValue(propertyName);
    }

    public String getAttribute(By locator, String attributeName) {
        return waitForElementVisible(locator).getAttribute(attributeName);
    }

    public void sendKeys(By locator, String text) {
        WebElement element = waitForElementVisible(locator);
        element.clear();
        element.sendKeys(text);
    }

    public void click(By locator) {
        waitForElementClickable(locator).click();
    }

    public void submitForm(By locator) {
        waitForElementVisible(locator).submit();
    }

    public boolean isElementEnable(By locator) {
        return waitForElementVisible(locator).isEnabled();
    }

    public boolean isElementDisplay(By locator) {
        return waitForElementVisible(locator).isDisplayed();
    }
}
