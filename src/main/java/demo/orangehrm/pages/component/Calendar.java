package demo.orangehrm.pages.component;

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
    private final By previousMonthButton = new ByChained(calendarSelector, By.xpath(".//button/i[contains(@class, 'left')]"));
    private final By calendarSelectorMonth = new ByChained(calendarSelector, By.xpath(".//li[contains(@class, 'selector-month')]"));
    private final By calendarSelectorYear = new ByChained(calendarSelector, By.xpath(".//li[contains(@class, 'selector-year')]"));
    private static final String DAY_XPATH = ".//*[contains(@class, 'dates')]//*[text()='%s']";
    private static final String CALENDAR_DROPDOWN_XPATH = ".//ul[@role='menu']";
    private static final String MONTH_XPATH = "./*[text()='%s']";
    private static final String YEAR_XPATH = "./*[text()='%s']";

    public Calendar(WebDriver driver) {
        super(driver);
    }

    public void previousMonth() {
        click(previousMonthButton);
        logger.info("Click Previous Month Button\n");
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

    public void selectMoth(String month) {
        click(calendarSelectorMonth);
        logger.info("Click Month button\n");

        By monthLocator = new ByChained(calendarSelectorMonth, getDynamicXpath(MONTH_XPATH, month));
        click(monthLocator);
    }
}
