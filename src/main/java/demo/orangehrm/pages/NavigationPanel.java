package demo.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class NavigationPanel extends BasePage {
    private final ChromeDriver chromeDriver;
    private final By adminMenuItem = By.linkText("Admin");

    public NavigationPanel(ChromeDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
    }


    public void clickAdminMenu() {
        chromeDriver.findElement(adminMenuItem).click();
    }
}
