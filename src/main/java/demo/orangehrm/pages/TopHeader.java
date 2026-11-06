package demo.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TopHeader extends BasePage {
    private final By pageTitleHeading = By.cssSelector("span[class*='topbar-header-breadcrumb']");
    private final By profileImage = By.cssSelector("img[class*='userdropdown-img']");

    public TopHeader(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        return getText(pageTitleHeading);
    }

    public boolean getDisplayOfProfileImage() {
        return isElementDisplay(profileImage);
    }
}
