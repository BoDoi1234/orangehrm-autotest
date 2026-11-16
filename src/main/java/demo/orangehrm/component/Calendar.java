package demo.orangehrm.component;

import demo.orangehrm.pages.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.ByChained;

public class Calendar extends BasePage {
    private final Logger logger = LogManager.getLogger(Calendar.class);
    private final By calendarSelector = By.xpath("//*[contains(@class, 'calendar-wrapper')]");
    private final By nextMonthButton = new ByChained(calendarSelector, By.xpath(".//button/i[contains(@class, 'right')]"));
    private static final String DAY_XPATH = ".//*[contains(@class, 'dates')]//*[text()='%s']";

    public Calendar(WebDriver driver) {
        super(driver);
    }

    public void nextMonth() {
        click(nextMonthButton);
        logger.info("Click Next Month Button\n");
    }

    public void selectDay(String day) {
        By dayLocator = new ByChained(calendarSelector, getDynamicXpath(DAY_XPATH, day));
        click(dayLocator);
        logger.info("Select Day: {}\n", day);
    }
}
