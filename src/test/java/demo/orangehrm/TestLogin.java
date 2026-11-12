package demo.orangehrm;

import demo.orangehrm.pages.LoginPage;
import demo.orangehrm.pages.TopHeader;
import demo.orangehrm.utils.DriverUtils;
import demo.orangehrm.utils.TestDataHelper;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestLogin {
    private final DriverUtils driverUtils = new DriverUtils();
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeMethod
    void setUp() {
        // Init browser
        driver = driverUtils.initBrowser();

        // Open login url
        loginPage = new LoginPage(driver);
        String url = TestDataHelper.getDataOf("orange_url");
//        loginPage.openUrl("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        loginPage.openUrl(url);
    }

    @AfterMethod
    void tearDown() {
        driverUtils.quitBrowser(driver);
    }

    @Test
    void testLoginSuccessfully() {
        // Login account
        loginPage.inputUsername(TestDataHelper.getDataOf("admin_username"));
        loginPage.inputPassword(TestDataHelper.getDataOf("admin_password"));
        loginPage.clickLoginButton();

        verifyDashboardLoaded();
    }

    @Test
    void testLoginSuccessfullyUsingForm() {
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

    @DataProvider(name = "invalidLoginData")
    public Object[][] getInvalidLoginData() {
        return new Object[][] {
                {"Admin", "admin"}, // Wrong password
                {"Admin123", "admin123"}, // Wrong username
                {"Admin123", "admin"}, // Wrong password and username
        };
    }

    @Test(dataProvider = "invalidLoginData")
    void testLoginFailCase(String username, String password) {
        loginPage.inputUsername(username);
        loginPage.inputPassword(password);
        loginPage.clickLoginButton();

        Assert.assertEquals(loginPage.getMessageError(), "Invalid credentials");
    }
}
