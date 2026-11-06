package demo.orangehrm.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TopHeader extends BasePage {
    private final Logger logger = LogManager.getLogger(TopHeader.class);
    private final By pageTitleHeading = By.cssSelector("span[class*='topbar-header-breadcrumb']");
    private final By profileImage = By.cssSelector("img[class*='userdropdown-img']");

    public TopHeader(WebDriver driver) {
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
}
