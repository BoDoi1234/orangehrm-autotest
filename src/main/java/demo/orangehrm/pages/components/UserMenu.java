package demo.orangehrm.pages.components;

import demo.orangehrm.pages.BasePage;
import demo.orangehrm.pages.authentication.LoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserMenu extends BasePage {
    private final Logger logger = LogManager.getLogger(UserMenu.class);
    private final By logoutButton = By.xpath("//header[contains(@class, 'topbar')]//li[contains(@class, 'userdropdown')]//a[contains(@href, 'logout')]");

    public UserMenu(WebDriver driver) {
        super(driver);
    }

    public LoginPage clickLogoutButton() {
        click(logoutButton);
        logger.info("Click Logout Button -> Navigate to Login Page\n");
        return new LoginPage(driver);
    }
}
