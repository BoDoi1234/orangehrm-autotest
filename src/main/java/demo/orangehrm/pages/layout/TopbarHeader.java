package demo.orangehrm.pages.layout;

import demo.orangehrm.pages.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TopbarHeader extends BasePage {
    private final Logger logger = LogManager.getLogger(TopbarHeader.class);
    private final By pageTitleHeading = By.cssSelector("span[class*='topbar-header-breadcrumb']");
    private final By profileImage = By.cssSelector("img[class*='userdropdown-img']");
    private final By userDropdown = By.xpath("//header[contains(@class, 'topbar')]//li[contains(@class, 'userdropdown')]");

    public TopbarHeader(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        String pageTitle = getText(pageTitleHeading);
        logger.info("Title of this Page is {}\n", pageTitle);
        return pageTitle;
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
