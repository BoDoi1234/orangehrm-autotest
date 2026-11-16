package demo.orangehrm.pages.leave;

import demo.orangehrm.pages.layout.BaseLayoutPage;
import demo.orangehrm.component.Calendar;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.ByChained;

public class LeavePage extends BaseLayoutPage {
    private final Logger logger = LogManager.getLogger(LeavePage.class);
    private static final String LABEL_XPATH = "//form//label[text()='%s']/parent::*/following-sibling::*";
    private final By leaveTypeSelector = new ByChained(getLocatorByLabelXpath("Leave Type"), By.xpath("./*[contains(@class, 'select-wrapper')]"));
    private final By fromDateSelector = new ByChained(getLocatorByLabelXpath("From Date"), By.xpath("./*[contains(@class, 'date-wrapper')]"));
    private final By toDateSelector = new ByChained(getLocatorByLabelXpath("To Date"), By.xpath("./*[contains(@class, 'date-wrapper')]"));

    public LeavePage(WebDriver driver) {
        super(driver);
    }

    public ApplyLeavePage clickApplyButton() {
        topbarNavigationPanel.clickNavigationButton("Apply");
        logger.info("Click apply button\n");
        return new ApplyLeavePage(driver);
    }

    protected By getLocatorByLabelXpath(String label) {
        return getDynamicXpath(LABEL_XPATH, label);
    }

    public void clickLeaveTypeSelector() {
        click(leaveTypeSelector);
        logger.info("Click leave type select\n");
    }

    public void selectLeaveType(String leaveType) {
        clickLeaveTypeSelector();
        By leaveTypeDropDown = new ByChained(leaveTypeSelector, getDynamicXpath("./*[@role='listbox']//*[text()='%s']", leaveType));

        click(leaveTypeDropDown);
        logger.info("Select Leave Type: {}\n", leaveType);
    }

    public Calendar clickFromDateSelector() {
        click(fromDateSelector);
        logger.info("Click From Date Input\n");
        return new Calendar(driver);
    }

    public Calendar clickToDateSelector() {
        click(toDateSelector);
        logger.info("Click To Date Input\n");
        return new Calendar(driver);
    }
}
