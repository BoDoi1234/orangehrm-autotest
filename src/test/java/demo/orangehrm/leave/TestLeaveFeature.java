package demo.orangehrm.leave;

import demo.orangehrm.pages.dashboard.DashboardPage;
import demo.orangehrm.pages.component.Calendar;
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

    @Test(description = "Apply for Leave Exceeding Balance & Verify Insufficient Balance Handling")
    void testApplyLeaveExceedingBalance() {
        LoginPage loginPage = new LoginPage(driver);

        // 1. Login as ESS User
        DashboardPage dashboardPage = loginPage.loginWithValidCredentials(TestDataHelper.getDataOf("ess_username"),
                TestDataHelper.getDataOf("ess_password"));

        // 2. Navigate to Leave Page > Apply
        LeavePage leavePage = dashboardPage.getNavigationPanel().clickLeaveMenu();
        ApplyLeavePage applyLeavePage = leavePage.clickApplyButton();

        // 3. Select Leave Type: US - Vacation
        applyLeavePage.selectLeaveType("US - Vacation");

        // 4. Select From Date and To Date
        Calendar calendar = applyLeavePage.clickFromDateSelector(); // From Date
        calendar.selectDay("16");

        calendar = applyLeavePage.clickToDateSelector(); // To Date
        calendar.nextMonth();
        calendar.selectDay("4");

        // 5.Observe balance error and attempt submit
        String leaveBalanceError = applyLeavePage.getLeaveBalanceTextAfterSelectDate();
        Assert.assertEquals(leaveBalanceError, "Balance not sufficient");
    }
}
