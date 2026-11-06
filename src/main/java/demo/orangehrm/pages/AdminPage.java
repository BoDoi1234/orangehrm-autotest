package demo.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.ByChained;

public class AdminPage extends BasePage {
    private final By firstRecordCheckbox = By.xpath("//*[contains(@class, 'table-body')]/*[2]//input[contains(@type, 'checkbox')]");

    public AdminPage(WebDriver driver) {
        super(driver);
    }

    public boolean isFirstRecordCheckboxChecked() {
        return isElementSelected(firstRecordCheckbox);
    }

    public void clickFirstRecordCheckbox() {
        By checkboxLocator = new ByChained(
                firstRecordCheckbox, By.xpath(".//parent::*")
        );
        click(checkboxLocator);
//        chromeDriver.findElement(firstRecordCheckbox).findElement(By.xpath(".//parent::*")).click();
    }
}
