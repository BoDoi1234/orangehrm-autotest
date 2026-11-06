package demo.orangehrm;

import demo.orangehrm.pages.LoginPage;
import demo.orangehrm.pages.TopHeader;
import demo.orangehrm.utils.DriverUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestLogin {
    private LoginPage loginPage;
    private DriverUtils driverUtils;
    private WebDriver driver;

    @BeforeMethod
    void setUp() {
        // Init browser
        driverUtils = new DriverUtils();
        driver = driverUtils.initBrowser();

        // Open login url
        loginPage = new LoginPage(driver);
        loginPage.openUrl("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @AfterMethod
    void tearDown() {
        driverUtils.quitBrowser(driver);
    }

    @Test
    void testLoginSuccessfully() {
        // Login account
        loginPage.inputUsername("Admin");
        loginPage.inputPassword("admin123");
        loginPage.clickLoginButton();

        verifyDashboardLoaded();
    }

    @Test
    void loginSuccessfullyUsingForm() {
        // Login account
        loginPage.inputUsername("Admin");
        loginPage.inputPassword("admin123");
        loginPage.submitFormLogin();

        verifyDashboardLoaded();
    }

    private void verifyDashboardLoaded() {
        // Verify: Url / Dashboard page / Profile image
        String currentUrl = loginPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("dashboard"));

        // getText()
        TopHeader topHeader = new TopHeader(driver);
        Assert.assertEquals(topHeader.getPageTitle(), "Dashboard");

        // isVisible()
        Assert.assertTrue(topHeader.getDisplayOfProfileImage(), "Profile Image is not displayed");
    }

    @Test
    void verifyLoginFormCssStyle() {
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
