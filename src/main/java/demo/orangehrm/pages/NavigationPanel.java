package demo.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationPanel extends BasePage {
    private final By adminMenuItem = By.xpath("//a[contains(@href, 'admin')]");

    public NavigationPanel(WebDriver driver) {
        super(driver);
    }

    public void clickAdminMenu() {
        click(adminMenuItem);
    }
}
