package demo.orangehrm.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverUtils {
    private final Logger logger = LogManager.getLogger(DriverUtils.class);

    public WebDriver initBrowser() {
        logger.info("Start Initial Browser");
        String browserName = System.getProperty("browser", "chrome");

        WebDriver driver = switch (browserName) {
            case "chrome" -> new ChromeDriver();
            case "firefox" -> new FirefoxDriver();
            case "edge" -> new EdgeDriver();
            default ->
                    throw new RuntimeException(String.format("Unsupported browser: %s. Allow ['chrome', 'firefox', 'edge']", browserName));
        };

        driver.manage().window().maximize();
        return driver;
    }

    public void quitBrowser(WebDriver driver) {
        if (driver != null) {
            logger.info("End Session: {}\n", driver.getWindowHandle());
            driver.quit();
        }
    }
}
