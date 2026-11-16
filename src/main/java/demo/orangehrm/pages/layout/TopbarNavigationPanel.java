package demo.orangehrm.pages.layout;

import demo.orangehrm.pages.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TopbarNavigationPanel extends BasePage {
    private final Logger logger = LogManager.getLogger(TopbarNavigationPanel.class);
    private static final String TOPBAR_NAVIGATION_PARENT_BUTTON_XPATH = "//*[contains(@class, 'topbar-body')]/nav[@role='navigation']//span[text()='%s']/parent::*";
    private static final String TOPBAR_NAVIGATION_BUTTON_XPATH = "//*[contains(@class, 'topbar-body')]/nav[@role='navigation']//a[text()='%s']";

    public TopbarNavigationPanel(WebDriver driver) {
        super(driver);
    }

    public void clickParentButton(String parentButtonName) {
        By parentButtonLocator = getDynamicXpath(TOPBAR_NAVIGATION_PARENT_BUTTON_XPATH, parentButtonName);
        click(parentButtonLocator);
    }

    public void clickNavigationButton(String buttonName) {
        By buttonLocator = getDynamicXpath(TOPBAR_NAVIGATION_BUTTON_XPATH, buttonName);
        click(buttonLocator);
    }
}
