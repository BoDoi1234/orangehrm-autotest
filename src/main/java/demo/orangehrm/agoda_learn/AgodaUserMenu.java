package demo.orangehrm.agoda_learn;

import demo.orangehrm.pages.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AgodaUserMenu extends BasePage {
    private final Logger logger = LogManager.getLogger(AgodaLandingPage.class);
    protected final static By changeLanguageButton = By.xpath("//button[contains(@aria-describedby,'language')]");
    protected final static By selectedLanguageText = By.xpath("//button[contains(@aria-describedby,'language')]/span");

    public AgodaUserMenu(WebDriver driver) {
        super(driver);
    }
}
