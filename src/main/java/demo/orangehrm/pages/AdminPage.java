package demo.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.pagefactory.ByChained;

public class AdminPage extends BasePage {
    private final ChromeDriver chromeDriver;
    private final By firstRecordCheckbox = By.xpath("//*[contains(@class, 'table-body')]/*[1]//input[@type='checkbox']");

    public AdminPage(ChromeDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
    }

    public boolean isFirstRecordCheckboxChecked() {
        return chromeDriver.findElement(firstRecordCheckbox).isSelected();
    }

    public void clickFirstRecordCheckbox() {
        By checkboxLocator = new ByChained(
                firstRecordCheckbox, By.xpath(".//parent::*")
        );
        chromeDriver.findElement(checkboxLocator).click();
//        chromeDriver.findElement(firstRecordCheckbox).findElement(By.xpath(".//parent::*")).click();
    }
}
