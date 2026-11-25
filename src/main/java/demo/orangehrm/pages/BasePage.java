package demo.orangehrm.pages;

import demo.orangehrm.enums.Directions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class BasePage extends Abstraction {
    private final Logger logger = LogManager.getLogger(BasePage.class);
    protected final WebDriver driver;
    protected final JavascriptExecutor js;
    protected final WebDriverWait wait;
    protected final Actions actions;
    private final int explicitWaitDuration = 10;
    public static final String testedLanguage = System.getProperty("language", "english");
    private final By youtubeSpinner = By.xpath("//tp-yt-paper-spinner[@id=\"spinner\"][@active]");

    public BasePage(WebDriver driver) {
        this.driver = driver;
        js = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWaitDuration));
        actions = new Actions(driver);
    }

    public Actions getActions() {
        return actions;
    }

    public void openUrl(String url) {
        driver.get(url);
        waitForPageReady();
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

    public void waitForElementInvisibility(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public WebElement waitForElementPresence(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public WebElement waitForElementVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForElementToBeClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public List<WebElement> waitForAllElementsVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public void waitForPageReady() {
        wait.until(d -> Objects.requireNonNull(((JavascriptExecutor) d).executeScript("return document.readyState")).equals("complete"));
        wait.until(d -> (Boolean) ((JavascriptExecutor) d).executeScript(
                "return (typeof jQuery === 'undefined' || (jQuery.active === 0))"
        ));
    }

    /**
     *  Wait until spinner appears within @param appear
     *  Wait until spinner disappear within @param disappear
     */
    public void waitForYoutubeSpinner(Duration appear, Duration disappear) {
        try {
            new WebDriverWait(driver, appear).until(ExpectedConditions.visibilityOfElementLocated(youtubeSpinner));
            logger.info("Youtube spinner appears. Wait until it disappears...");

            new WebDriverWait(driver, disappear).until(ExpectedConditions.invisibilityOfElementLocated(youtubeSpinner));
            logger.info("Youtube spinner disappear.");
        } catch (TimeoutException e) {
            logger.info("Youtube spinner does not appear within {} seconds(s) ! Continue", appear.getSeconds());
        }
    }

    public void waitForYoutubeSpinner() {
        try {
            waitForElementVisibility(youtubeSpinner);
            logger.info("Youtube spinner appears. Wait until it disappears...");

            waitForElementInvisibility(youtubeSpinner);
            logger.info("Youtube spinner disappear.");
        } catch (TimeoutException e) {
            logger.info("Youtube spinner does not appear within {} seconds(s) ! Continue", explicitWaitDuration);
        }
    }

    public boolean waitForUrlContains(String url) {
        return wait.until(ExpectedConditions.urlContains(url));
    }

    public boolean waitForTitleContains(String title) {
        return wait.until(ExpectedConditions.titleContains(title));
    }

    public void waitForAttributeContains(WebElement element, String attributeName, String expectedValue) {
        wait.until(ExpectedConditions.attributeContains(element, attributeName, expectedValue));
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

    public void clickBackAndWaitForPopState() {
        js.executeScript(
                "window.__backCompleted = false;" +
                        "window.addEvenListener('popstate', function() { window.__backCompleted = true; }, { once: true })"
        );

        driver.navigate().back();
        logger.info("Click back button\n");

        wait.until(webDriver -> Boolean.TRUE.equals(
                ((JavascriptExecutor) webDriver).executeAsyncScript("return window.__backCompleted;")
        ));
    }

    public void inputTextInToTextBox(By locator, String text) {
        WebElement element = waitForElementVisibility(locator);
        element.clear();
        element.sendKeys(text);
    }

    @Override
    public void click(By locator) {
        try {
            waitForElementToBeClickable(locator).click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click()", waitForElementPresence(locator));
        }
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

    public void scrollTo(Directions directions) {
        switch (directions) {
            case TOP:
                scrollToTop();
                break;
            case BOTTOM:
                scrollToBottom();
                break;
            default:
                throw new RuntimeException("Invalid directions: " + directions);
        }
    }

    private void scrollToTop() {
        String scrollToTopScript = "window.scrollTo(0, 0)";
        js.executeScript(scrollToTopScript);
    }

    private void scrollToBottom() {
        // Scroll to bottom: If before and after height are equal, then it is bottom
        long beforeScrollHeight = 0, afterScrollHeight = -1;

        while (afterScrollHeight != beforeScrollHeight) {
            beforeScrollHeight = getMaxScrollHeight();

            String scrollByHeightScript = String.format("window.scrollTo(0, %d)", getMaxScrollHeight());
            js.executeScript(scrollByHeightScript);
            waitForYoutubeSpinner(Duration.ofSeconds(5), Duration.ofSeconds(10));
            waitForPageReady();

            afterScrollHeight = getMaxScrollHeight();
        }
    }

    private long getMaxScrollHeight() {
        String maxScrollHeightScript = "Math.max(document.body.scrollHeight, document.documentElement.scrollHeight)";
        return (Long) Objects.requireNonNull(js.executeScript(String.format("return %s", maxScrollHeightScript)));
    }

    /**
     *  Scroll by x, y offset
     *  @param deltaX
     *  @param deltaY
     */
    public void scrollByOffset(int deltaX, int deltaY) {
        String scrollToBottom = String.format("window.scrollTo(%d, %d)", deltaX, deltaY);
        js.executeScript(scrollToBottom);
    }

    public void scrollInsideElementTo(WebElement element, Directions directions) {
        switch (directions) {
            case TOP:
                scrollInsideElementToTop(element);
                break;
            case BOTTOM:
                scrollInsideElementToBottom(element);
                break;
            default:
                throw new RuntimeException("Invalid directions: " + directions);
        }
    }

    private void scrollInsideElementToTop(WebElement element) {
        js.executeScript("arguments[0].scrollTop = 0", element);
    }

    private void scrollInsideElementToBottom(WebElement element) {
        long beforeScrollHeight = 0, afterScrollHeight = -1;

        while (afterScrollHeight != beforeScrollHeight) {
            beforeScrollHeight = getElementScrollHeight(element);

            String scrollByHeightScript = String.format("arguments[0].scrollTop = %d", getElementScrollHeight(element));
            js.executeScript(scrollByHeightScript, element);
            waitForPageReady();

            afterScrollHeight = getElementScrollHeight(element);
        }
    }

    private long getElementScrollHeight(WebElement element) {
        String script = "return arguments[0].scrollHeight";
        return (Long) Objects.requireNonNull(js.executeScript(script, element));
    }

    public void zoomInOut(String rate) {
        js.executeScript(String.format("document.body.style.zoom = '%s'", rate));
    }

    protected By getDynamicXpath(String xpathTemplate, String... values) {
        return By.xpath(String.format(xpathTemplate, (Object[]) values));
    }
}
