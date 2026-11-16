package demo.orangehrm.pages.layout;

import demo.orangehrm.pages.BasePage;
import demo.orangehrm.pages.authentication.LoginPage;
import org.openqa.selenium.WebDriver;

public class BaseLayoutPage extends BasePage {
    protected final TopbarHeader topbarHeader;
    protected final TopbarNavigationPanel topbarNavigationPanel;
    protected final NavigationPanel navigationPanel;

    public BaseLayoutPage(WebDriver driver) {
        super(driver);
        topbarHeader = new TopbarHeader(driver);
        topbarNavigationPanel = new TopbarNavigationPanel(driver);
        navigationPanel = new NavigationPanel(driver);
    }

    public TopbarHeader getTopHeader() {
        return topbarHeader;
    }

    public TopbarNavigationPanel getTopbarNavigationPanel() {
        return topbarNavigationPanel;
    }

    public NavigationPanel getNavigationPanel() {
        return navigationPanel;
    }

    public LoginPage logoutAccount() {
        UserMenu userMenu = topbarHeader.clickUserDropdown();
        return userMenu.clickLogoutButton();
    }
}
