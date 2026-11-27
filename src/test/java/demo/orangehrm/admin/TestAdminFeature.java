package demo.orangehrm.admin;

import demo.orangehrm.pages.admin.AdminPage;
import demo.orangehrm.pages.dashboard.DashboardPage;
import demo.orangehrm.pages.authentication.LoginPage;
import demo.orangehrm.utils.DriverUtils;
import demo.orangehrm.utils.TestDataHelper;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestAdminFeature {
    private final DriverUtils driverUtils = new DriverUtils();
    private WebDriver driver;

    @BeforeMethod
    void setUp() {
        // Init browser
        driver = driverUtils.initBrowser();

        // Open login url
        String url = TestDataHelper.getDataFromPropertiesOf("orange_url");
        new LoginPage(driver).openUrl(url);
    }

    @AfterMethod
    void tearDown() {
        // Quit browser
        driverUtils.quitBrowser(driver);
    }

    @Test
    void testRecordCheckboxBehavior() {
        LoginPage loginPage = new LoginPage(driver);

        // Login as Admin User
        DashboardPage dashboardPage = loginPage.loginWithValidCredentials(TestDataHelper.getDataFromPropertiesOf("admin_username"),
                TestDataHelper.getDataFromPropertiesOf("admin_password"));

        // Navigate to Admin Page
        AdminPage adminPage = dashboardPage.getNavigationPanel().clickAdminMenu();

        boolean firstRecordCheckboxIsChecked = adminPage.isFirstRecordCheckboxChecked();
        Assert.assertFalse(firstRecordCheckboxIsChecked, "Default status of 1st record checkbox is checked");

        // Click 1st checkbox record
        adminPage.clickFirstRecordCheckbox();
        firstRecordCheckboxIsChecked = adminPage.isFirstRecordCheckboxChecked();
        Assert.assertTrue(firstRecordCheckboxIsChecked, "Status of 1st record checkbox is not checked");
    }
}
