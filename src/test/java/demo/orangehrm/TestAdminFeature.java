package demo.orangehrm;

import demo.orangehrm.pages.AdminPage;
import demo.orangehrm.pages.BasePage;
import demo.orangehrm.pages.LoginPage;
import demo.orangehrm.pages.NavigationPanel;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestAdminFeature {
    @Test
    void testRecordCheckboxBehavior() throws InterruptedException {
        // Open login url
        BasePage basePage = new BasePage();
        basePage.openUrl("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        // Login account
        ChromeDriver chromeDriver = basePage.getChromeDriver();
        LoginPage loginPage = new LoginPage(chromeDriver);
        Thread.sleep(5000);

        loginPage.inputUsername("Admin");
        loginPage.inputPassword("admin123");
        loginPage.clickLoginButton();
        Thread.sleep(5000);

        // Open Admin Page
        NavigationPanel navigationPanel = new NavigationPanel(chromeDriver);
        navigationPanel.clickAdminMenu();
        Thread.sleep(5000);

        AdminPage adminPage = new AdminPage(chromeDriver);
        boolean firstRecordCheckboxIsChecked = adminPage.isFirstRecordCheckboxChecked();
        Assert.assertFalse(firstRecordCheckboxIsChecked, "Default status of 1st record checkbox is checked");

        // Click 1st checkbox record
        adminPage.clickFirstRecordCheckbox();
        firstRecordCheckboxIsChecked = adminPage.isFirstRecordCheckboxChecked();
        Assert.assertTrue(firstRecordCheckboxIsChecked, "Status of 1st record checkbox is not checked");
    }
}
