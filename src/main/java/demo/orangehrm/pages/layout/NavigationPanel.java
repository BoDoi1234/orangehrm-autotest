package demo.orangehrm.pages.layout;

import demo.orangehrm.pages.admin.AdminPage;
import demo.orangehrm.pages.BasePage;
import demo.orangehrm.pages.leave.LeavePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationPanel extends BasePage {
    private final Logger logger = LogManager.getLogger(NavigationPanel.class);
    private static final String MENU_ITEM_XPATH = "//a[contains(@href, '%s')]";

    public NavigationPanel(WebDriver driver) {
        super(driver);
    }

    private void clickMenuByModule(String moduleName) {
        By menuLocator = getDynamicXpath(MENU_ITEM_XPATH, moduleName);
        click(menuLocator);
    }

    public AdminPage clickAdminMenu() {
        clickMenuByModule("viewAdminModule");
        logger.info("Navigate to Admin Page\n");
        return new AdminPage(driver);
    }

    public LeavePage clickLeaveMenu() {
        clickMenuByModule("viewLeaveModule");
        logger.info("Navigate to Leave Page\n");
        return new LeavePage(driver);
    }
}
