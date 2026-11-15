package demo.orangehrm.pages.components;

import demo.orangehrm.pages.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TopHeader extends BasePage {
    private final Logger logger = LogManager.getLogger(TopHeader.class);
    private final By pageTitleHeading = By.cssSelector("span[class*='topbar-header-breadcrumb']");
    private final By profileImage = By.cssSelector("img[class*='userdropdown-img']");
    private final By userDropdown = By.xpath("//header[contains(@class, 'topbar')]//li[contains(@class, 'userdropdown')]");
    private static final String TOPBAR_NAVIGATION_BUTTON_XPATH = "//*[contains(@class, 'topbar-body')]/nav[@role='navigation']//a[text()='%s']";

    public TopHeader(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        String pageTitle = getText(pageTitleHeading);
        logger.info("Title of this Page is {}\n", pageTitle);
        return pageTitle;
    }

    public void clickNavigationButton(String buttonName) {
        By buttonLocator = getDynamicXpath(TOPBAR_NAVIGATION_BUTTON_XPATH, buttonName);
        logger.info("Navigate to {}\n", buttonName);
        click(buttonLocator);
    }

    public boolean getDisplayOfProfileImage() {
        boolean isDisplayed = isElementDisplayed(profileImage);
        logger.info("Profile Image {} display\n", isDisplayed ? "is" : "is not");
        return isDisplayed;
    }

    public UserMenu clickUserDropdown() {
        click(userDropdown);
        logger.info("Click User Dropdown Menu\n");
        return new UserMenu(driver);
    }
}
