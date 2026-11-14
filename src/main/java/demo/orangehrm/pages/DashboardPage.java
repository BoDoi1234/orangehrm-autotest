package demo.orangehrm.pages;

import demo.orangehrm.pages.layout.BaseLayoutPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class DashboardPage extends BaseLayoutPage {
    private final Logger logger = LogManager.getLogger(DashboardPage.class);
    private final By widgetList = By.xpath("//*[contains(@class, 'dashboard-grid')]//*[contains(@class, 'widget-name')]//p");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getAllWidgetName() {
        List<String> widgetNameList = getTextList(widgetList);
        logger.info("Widget name list = {}\n", widgetNameList);
        return widgetNameList;
    }
}
