package demo.orangehrm.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.ByChained;

public class AdminPage extends BasePage {
    private final Logger logger = LogManager.getLogger(AdminPage.class);
    private final By firstRecordCheckbox = By.xpath("//*[contains(@class, 'table-body')]/*[2]//input[contains(@type, 'checkbox')]");

    public AdminPage(WebDriver driver) {
        super(driver);
    }

    public boolean isFirstRecordCheckboxChecked() {
        boolean isSelected = isElementSelected(firstRecordCheckbox);
        logger.info("First Record checkbox {} checked\n", isSelected ? "is" : "is not");
        return isSelected;
    }

    public void clickFirstRecordCheckbox() {
        By checkboxLocator = new ByChained(
                firstRecordCheckbox, By.xpath(".//parent::*")
        );
        click(checkboxLocator);
        logger.info("Selected checkbox of First Record\n");
    }
}
