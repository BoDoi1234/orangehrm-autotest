package demo.orangehrm.pages.component;

import demo.orangehrm.pages.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Toast extends BasePage {
    private final Logger logger = LogManager.getLogger(Toast.class);
    private final By toastTitleLocator = By.xpath("//*[contains(@class, 'toast-content')]/*[contains(@class, 'toast-title')]");
    private final By toastMessageLocator = By.xpath("//*[contains(@class, 'toast-content')]/*[contains(@class, 'toast-message')]");

    public Toast(WebDriver driver) {
        super(driver);
    }

    public String getTypeOfToast() {
        String toastTitle = getText(toastTitleLocator);
        logger.info("Type of Toast: {}\n", toastTitle);
        return toastTitle;
    }

    public String getMessageOfToast() {
        String toastMessage = getText(toastMessageLocator);
        logger.info("Message of Toast: {}\n", toastMessage);
        return toastMessage;
    }
}
