package demo.orangehrm.pages.leave;

import demo.orangehrm.pages.component.Toast;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.ByChained;

public class ApplyLeavePage extends LeavePage {
    private final Logger logger = LogManager.getLogger(ApplyLeavePage.class);
    private final By leaveBalanceSelector = new ByChained(getLocatorByLabel("Leave Balance"), By.xpath("./p"));

    public ApplyLeavePage(WebDriver driver) {
        super(driver);
    }

    public String getLeaveBalanceText() {
        String leaveBalanceText = getText(leaveBalanceSelector);
        logger.info("Leave Balance Text: {}\n", leaveBalanceText);
        return leaveBalanceText;
    }

    public String getLeaveBalanceTextAfterSelectDate() {
        String oldLeaveBalanceText = getLeaveBalanceText();
        String leaveBalanceText = getTextWhenChangeForm(leaveBalanceSelector, oldLeaveBalanceText);
        logger.info("Leave Balance Text After Select Date: {}\n", leaveBalanceText);
        return leaveBalanceText;
    }

    public Toast applyLeaveBalance() {
        submitForm();
        return new Toast(driver);
    }
}
