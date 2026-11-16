package demo.orangehrm.leave;

import demo.orangehrm.pages.dashboard.DashboardPage;
import demo.orangehrm.pages.layout.Calendar;
import demo.orangehrm.pages.leave.ApplyLeavePage;
import demo.orangehrm.pages.leave.LeavePage;
import demo.orangehrm.pages.authentication.LoginPage;
import demo.orangehrm.utils.DriverUtils;
import demo.orangehrm.utils.TestDataHelper;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestLeaveFeature {
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
        // Quit browser
        driverUtils.quitBrowser(driver);
    }

    private DashboardPage loginAs(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputUsername(username);
        loginPage.inputPassword(password);

        return loginPage.clickLoginButton();
    }

    @Test
    void testApplyLeaveExceedingBalance() {
        // 1. Login as ESS User
        DashboardPage dashboardPage = loginAs(TestDataHelper.getDataOf("ess_username"),
                TestDataHelper.getDataOf("ess_password"));

        // 2. Navigate to Leave Page > Apply
        LeavePage leavePage = dashboardPage.getNavigationPanel().clickLeaveMenu();
        ApplyLeavePage applyLeavePage = leavePage.clickApplyButton();

        // 3. Select Leave Type: US - Vacation
        applyLeavePage.selectLeaveType("US - Vacation");

        // 4. Select From Date and To Date
        Calendar calendar = applyLeavePage.clickFromDateSelector();
        calendar.selectDay("16");

        calendar = applyLeavePage.clickToDateSelector();
        calendar.nextMonth();
        calendar.selectDay("4");

        // 5.Observe balance error and attempt submit
        String leaveBalanceText = applyLeavePage.getLeaveBalanceTextAfterChange();
        Assert.assertEquals(leaveBalanceText, "Balance not sufficient");
    }
}
