package demo.orangehrm;

import demo.orangehrm.pages.AdminPage;
import demo.orangehrm.pages.LoginPage;
import demo.orangehrm.pages.NavigationPanel;
import demo.orangehrm.utils.DriverUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestAdminFeature {
    private AdminPage adminPage;
    private DriverUtils driverUtils;
    private WebDriver driver;

    @BeforeMethod
    void setUp() {
        // Init browser
        driverUtils = new DriverUtils();
        driver = driverUtils.initBrowser();

        // Open login url
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openUrl("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        // Login account
        loginPage.inputUsername("Admin");
        loginPage.inputPassword("admin123");
        loginPage.clickLoginButton();

        // Open Admin Page
        NavigationPanel navigationPanel = new NavigationPanel(driver);
        navigationPanel.clickAdminMenu();
        adminPage = new AdminPage(driver);
    }

    @AfterMethod
    void tearDown() {
        // Quit browser
        driverUtils.quitBrowser(driver);
    }

    @Test
    void testRecordCheckboxBehavior() {
        boolean firstRecordCheckboxIsChecked = adminPage.isFirstRecordCheckboxChecked();
        Assert.assertFalse(firstRecordCheckboxIsChecked, "Default status of 1st record checkbox is checked");

        // Click 1st checkbox record
        adminPage.clickFirstRecordCheckbox();
        firstRecordCheckboxIsChecked = adminPage.isFirstRecordCheckboxChecked();
        Assert.assertTrue(firstRecordCheckboxIsChecked, "Status of 1st record checkbox is not checked");
    }
}
