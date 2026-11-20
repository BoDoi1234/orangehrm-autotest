package demo.orangehrm.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BasePage extends Abstraction {
    private final Logger logger = LogManager.getLogger(BasePage.class);
    protected final WebDriver driver;
    protected final WebDriverWait wait;
    public static final String testedLanguage = System.getProperty("language", "english");

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openUrl(String url) {
        driver.get(url);
        logger.info("Access to {}\n", url);
    }

    public void switchToWindow(int index) {
        List<String> activeWindows = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(activeWindows.get(index));
    }

    public void switchToWindow(String pageTitle) {
        Set<String> activeWindows = driver.getWindowHandles();

        for (String window : activeWindows) {
            String windowTitle = driver.switchTo().window(window).getTitle();
            assert windowTitle != null;
            if (windowTitle.toLowerCase().contains(pageTitle.toLowerCase())) {
                break;
            }
        }
    }

    public WebElement waitForElementPresence(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public WebElement waitForElementVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForElementClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public List<WebElement> waitForAllElementsVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public void waitForElementStaleness(WebElement element) {
        wait.until(ExpectedConditions.stalenessOf(element));
    }

    public boolean waitForUrlContains(String url) {
        return wait.until(ExpectedConditions.urlContains(url));
    }

    public boolean waitForTitleContains(String title) {
        return wait.until(ExpectedConditions.titleContains(title));
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getText(By locator) {
        return waitForElementVisibility(locator).getText();
    }

    public String getTextWhenChangeForm(By locator, String oldText) {
        wait.until(ExpectedConditions.not((ExpectedConditions.textToBe(locator, oldText))));

        return waitForElementVisibility(locator).getText();
    }

    public List<String> getTextList(By locator) {
        List<WebElement> tabList = waitForAllElementsVisibility(locator);
        List<String> result = new ArrayList<>();

        for (WebElement webElement : tabList) {
            result.add(webElement.getText());
        }

        return result;
    }

    public String getCssValueOf(By locator, String propertyName) {
        return waitForElementPresence(locator).getCssValue(propertyName);
    }

    public String getAttribute(By locator, String attributeName) {
        return waitForElementPresence(locator).getAttribute(attributeName);
    }

    public void clickBackButtonAndWaitForUrlContains(String expectedUrlPart) {
        driver.navigate().back();
        logger.info("Click back button and wait for URL contains: {}\n", expectedUrlPart);
        waitForUrlContains(expectedUrlPart);
    }

    public void inputTextInToTextBox(By locator, String text) {
        WebElement element = waitForElementVisibility(locator);
        element.clear();
        element.sendKeys(text);
    }

    public void click(By locator) {
        waitForElementClickable(locator).click();
    }

    public void submitForm(By locator) {
        waitForElementPresence(locator).submit();
    }

    public boolean isElementEnabled(By locator) {
        return waitForElementPresence(locator).isEnabled();
    }

    public boolean isElementDisplayed(By locator) {
        return waitForElementVisibility(locator).isDisplayed();
    }

    public boolean isElementSelected(By locator) {
        return waitForElementPresence(locator).isSelected();
    }

    protected By getDynamicXpath(String xpathTemplate, String... values) {
        return By.xpath(String.format(xpathTemplate, (Object[]) values));
    }
}
