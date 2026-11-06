package demo.orangehrm.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationPanel extends BasePage {
    private final Logger logger = LogManager.getLogger(NavigationPanel.class);
    private final By adminMenuItem = By.xpath("//a[contains(@href, 'viewAdminModule')]");

    public NavigationPanel(WebDriver driver) {
        super(driver);
    }

    public AdminPage clickAdminMenu() {
        click(adminMenuItem);
        logger.info("Navigate to Admin Page\n");
        return new AdminPage(driver);
    }
}
