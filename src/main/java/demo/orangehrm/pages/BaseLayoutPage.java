package demo.orangehrm.pages;

import demo.orangehrm.pages.layout.NavigationPanel;
import demo.orangehrm.pages.layout.TopHeader;
import demo.orangehrm.pages.layout.UserMenu;
import org.openqa.selenium.WebDriver;

public class BaseLayoutPage extends BasePage {
    protected final TopHeader topHeader;
    protected final NavigationPanel navigationPanel;

    public BaseLayoutPage(WebDriver driver) {
        super(driver);
        topHeader = new TopHeader(driver);
        navigationPanel = new NavigationPanel(driver);
    }

    public TopHeader getTopHeader() {
        return topHeader;
    }

    public NavigationPanel getNavigationPanel() {
        return navigationPanel;
    }

    public LoginPage logoutAccount() {
        UserMenu userMenu = topHeader.clickUserDropdown();
        return userMenu.clickLogoutButton();
    }
}
