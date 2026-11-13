package demo.orangehrm.agoda_learn;

import demo.orangehrm.pages.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AgodaHeader extends BasePage {
    private final Logger logger = LogManager.getLogger(AgodaLandingPage.class);
    protected final static By userMenuButton = By.xpath("//header[@id='page-header']//button[contains(@data-element-name, 'menu')]");

    public AgodaHeader(WebDriver driver) {
        super(driver);
    }


}
