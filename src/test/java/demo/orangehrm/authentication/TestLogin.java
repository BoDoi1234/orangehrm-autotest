package demo.orangehrm.authentication;

import demo.orangehrm.pages.dashboard.DashboardPage;
import demo.orangehrm.pages.authentication.LoginPage;
import demo.orangehrm.utils.DriverUtils;
import demo.orangehrm.utils.TestDataHelper;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class TestLogin {
    private final DriverUtils driverUtils = new DriverUtils();
    private WebDriver driver;

    @BeforeMethod
    void setUp() {
        // Init browser
        driver = driverUtils.initBrowser();

        // Open login url
        String url = TestDataHelper.getDataOf("orange_url");
        new LoginPage(driver).openUrl(url);
    }

    @AfterMethod
    void tearDown() {
        driverUtils.quitBrowser(driver);
    }

    private void verifyDashboardLoaded(DashboardPage dashboardPage) {
        // Verify: Url / Dashboard page / Profile image
        String currentUrl = dashboardPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("dashboard"));

        // getText()
        Assert.assertEquals(dashboardPage.getTopHeader().getPageTitle(), "Dashboard");

        // isVisible()
        Assert.assertTrue(dashboardPage.getTopHeader().getDisplayOfProfileImage(), "Profile Image is not displayed");
    }

    @Test(description = "Admin Login, Dashboard Widget Verification, and Logout")
    void testAuthenticationWithAdminAccountAndLogout() {
        LoginPage loginPage = new LoginPage(driver);

        // Login with Admin account
        DashboardPage dashboardPage = loginPage.loginWithValidCredentials(TestDataHelper.getDataOf("admin_username"),
                TestDataHelper.getDataOf("admin_password"));

        // Verify Dashboard Widgets
        List<String> actualWidgetNameList = dashboardPage.getAllWidgetName();
        List<String> expectedWidgetNameList = List.of(TestDataHelper.getDataOf("admin_dashboard_page_widget_name_list").split(", "));
        Assert.assertTrue(actualWidgetNameList.containsAll(expectedWidgetNameList));

        // Logout account
        loginPage = dashboardPage.logoutAccount();
        String currentUrl = loginPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("login"));

        // Click back button and verify url
        loginPage.clickBackButton();
        Assert.assertTrue(loginPage.waitForUrlContains("login"));
    }

    @Test(description = "UI_TC")
    void verifyLoginFormCssStyle() {
        LoginPage loginPage = new LoginPage(driver);

        // Check background login button color
        String loginButtonBackgroundColor = loginPage.getBackgroundColorOfLoginButton();
        Assert.assertEquals(loginButtonBackgroundColor, "rgba(21, 244, 233, 1)");

        // Check color login button
        String loginButtonColor = loginPage.getColorOfLoginButton();
        Assert.assertEquals(loginButtonColor, "rgba(6, 0, 0, 1)");

        String usernameAttributeName = loginPage.getAttributeNameOfUsername();
        Assert.assertEquals(usernameAttributeName, "username");
    }
}
