package demo.orangehrm;

import demo.orangehrm.pages.DashboardPage;
import demo.orangehrm.pages.LoginPage;
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

    private DashboardPage loginAs(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputUsername(username);
        loginPage.inputPassword(password);
        return loginPage.clickLoginButton();
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

    @Test(description = "TC_E2E_01")
    void testAuthenticationWithAdminAccountAndLogout() {
        // Login with Admin account
        DashboardPage dashboardPage = loginAs(TestDataHelper.getDataOf("admin_username"),
                TestDataHelper.getDataOf("admin_password"));

        List<String> actualWidgetNameList = dashboardPage.getAllWidgetName();
        List<String> expectedWidgetNameList = List.of(TestDataHelper.getDataOf("admin_dashboard_page_widget_name_list").split(", "));

        Assert.assertTrue(actualWidgetNameList.containsAll(expectedWidgetNameList));

        LoginPage loginPage = dashboardPage.logoutAccount();
        String currentUrl = loginPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("login"));

        loginPage.clickBackButtonOfBrowser();
        currentUrl = loginPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("login"));
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
