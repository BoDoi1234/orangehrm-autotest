package demo.orangehrm.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BasePage {
    private ChromeDriver chromeDriver;

    public ChromeDriver getChromeDriver() {
        return chromeDriver;
    }

    public void setChromeDriver(ChromeDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
    }

    public void openUrl(String url) {
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
        setChromeDriver(driver);
    }

    public String getCurrentUrl() {
        return chromeDriver.getCurrentUrl();
    }

    public String getTitle() {
        return chromeDriver.getTitle();
    }

    public String getCssValueOf(String cssAttribute, WebElement webElement) {
        return webElement.getCssValue(cssAttribute);
    }
}
