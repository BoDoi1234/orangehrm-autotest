package demo.orangehrm.pages.component;

import demo.orangehrm.pages.BasePage;
import demo.orangehrm.utils.DateHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ByChained;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.YearMonth;

public class Calendar extends BasePage {
    private final Logger logger = LogManager.getLogger(Calendar.class);
    private final By calendarSelector = By.xpath("//*[contains(@class, 'calendar-wrapper')]");
    private final By nextMonthButton = new ByChained(calendarSelector, By.xpath(".//button/i[contains(@class, 'right')]"));
    private final By previousMonthButton = new ByChained(calendarSelector, By.xpath(".//button/i[contains(@class, 'left')]"));
    private final By calendarSelectorMonth = new ByChained(calendarSelector, By.xpath(".//li[contains(@class, 'selector-month')]"));
    private final By calendarSelectorYear = new ByChained(calendarSelector, By.xpath(".//li[contains(@class, 'selector-year')]"));
    private static final String DAY_XPATH = ".//*[contains(@class, 'dates')]//*[text()='%s']";
    private static final String MONTH_XPATH = ".//li[text()='%s']";
    private static final String YEAR_XPATH = ".//li[text()='%s']";

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
        int dayInt = Integer.parseInt(day.trim());
        if (dayInt < 1 || dayInt > 31) {
            throw new IllegalArgumentException("Day must be between 1 and 31: " + day);
        }

        String currentMonthName = getText(new ByChained(calendarSelectorMonth, By.xpath(".//p")));
        int currentYear = Integer.parseInt(getText(new ByChained(calendarSelectorYear, By.xpath(".//p"))));
        int currentMonth = DateHelper.getMonthNumber(currentMonthName);

        int maxDaysInMonth = YearMonth.of(currentYear, currentMonth).lengthOfMonth();
        if (dayInt > maxDaysInMonth) {
            throw new IllegalArgumentException(
                    String.format("Invalid day %d for %s %d (max is %d days)", dayInt, currentMonthName, currentYear, maxDaysInMonth)
            );
        }

        String normalizedDay = String.valueOf(dayInt);
        By dayLocator = new ByChained(calendarSelector, getDynamicXpath(DAY_XPATH, normalizedDay));
        click(dayLocator);
        logger.info("Select Day: {}\n", normalizedDay);
    }

    public void selectMonth(int month) {
        click(calendarSelectorMonth);
        logger.info("Click Month button\n");

        String monthName = DateHelper.getMonthName(month);
        By monthLocator = new ByChained(calendarSelectorMonth, getDynamicXpath(MONTH_XPATH, monthName));
        click(monthLocator);
        logger.info("Select Month: {}\n", month);
    }

    public void selectYear(String year) {
        click(calendarSelectorYear);
        logger.info("Click Year button\n");

        By yearLocator = new ByChained(calendarSelectorYear, getDynamicXpath(YEAR_XPATH, year));
        WebElement yearElement = driver.findElement(yearLocator);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", yearElement);
        yearElement.click();
        logger.info("Select Year: {}\n", year);
    }

    public void selectDate(String year, String month, String day) {
        int y = Integer.parseInt(year.trim());
        int m = Integer.parseInt(month.trim());
        int d = Integer.parseInt(day.trim());

        try {
            LocalDate.of(y, m, d);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException(String.format("Invalid calendar date: %s-%s-%s", year, month, day), e);
        }

        selectYear(String.valueOf(y));
        selectMonth(m);
        selectDay(String.valueOf(d));
    }

    /**
     *
     *  @param isoDate: type yyyy-MM-dd
     */
    public void selectDate(String isoDate) {
        LocalDate date = LocalDate.parse(isoDate);
        selectDate(
                String.valueOf(date.getYear()),
                String.valueOf(date.getMonthValue()),
                String.valueOf(date.getDayOfMonth())
        );
    }
}
