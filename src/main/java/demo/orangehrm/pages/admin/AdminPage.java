package demo.orangehrm.pages.admin;

import demo.orangehrm.pages.layout.BaseLayoutPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.ByChained;

public class AdminPage extends BaseLayoutPage {
    private final Logger logger = LogManager.getLogger(AdminPage.class);
    private static final String RECORD_CHECKBOX_XPATH = "//*[contains(@class, 'table-body')]/*[%s]//input[contains(@type, 'checkbox')]";

    public AdminPage(WebDriver driver) {
        super(driver);
    }

    public boolean isRecordCheckboxChecked(String index) {
        By recordCheckbox = getDynamicXpath(RECORD_CHECKBOX_XPATH, index);
        return isElementSelected(recordCheckbox);
    }

    public void clickRecordCheckbox(String index) {
        By checkboxLocator = new ByChained(
                getDynamicXpath(RECORD_CHECKBOX_XPATH, index), By.xpath(".//parent::*")
        );
        click(checkboxLocator);
    }

    public boolean isFirstRecordCheckboxChecked() {
        boolean isSelected = isRecordCheckboxChecked("2");
        logger.info("First Record checkbox {} checked\n", isSelected ? "is" : "is not");
        return isSelected;
    }

    public void clickFirstRecordCheckbox() {
        clickRecordCheckbox("2");
        logger.info("Selected checkbox of First Record\n");
    }
}
