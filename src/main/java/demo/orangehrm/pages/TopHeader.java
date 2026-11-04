package demo.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class TopHeader extends BasePage {
    private final ChromeDriver chromeDriver;
    private final By pageTitleHeading = By.cssSelector("span[class*='topbar-header-breadcrumb']");
    private final By profileImage = By.cssSelector("img[class*='userdropdown-img']");

    public TopHeader(ChromeDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
    }


    public String getPageTitle() {
        return chromeDriver.findElement(pageTitleHeading).getText();
    }

    public boolean getDisplayOfProfileImage() {
        return chromeDriver.findElement(profileImage).isDisplayed();
    }
}
