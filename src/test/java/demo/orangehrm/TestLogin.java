package demo.orangehrm;

import demo.orangehrm.pages.BasePage;
import demo.orangehrm.pages.LoginPage;
import demo.orangehrm.pages.TopHeader;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestLogin {
    @Test
    void testLoginSuccessfully() throws InterruptedException {
        // Open login url
        BasePage basePage = new BasePage();
        basePage.openUrl("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        // Login account
        ChromeDriver chromeDriver = basePage.getChromeDriver();
        LoginPage loginPage = new LoginPage(chromeDriver);
        Thread.sleep(5000);

        // Verify login button is enabled event username and password are not filled
        boolean loginButtonIsEnabled = loginPage.checkLoginButtonIsEnabled();
        Assert.assertTrue(loginButtonIsEnabled, "Login Button is not enabled");

        loginPage.inputUsername("Admin");
        loginPage.inputPassword("admin123");
        loginPage.clickLoginButton();
        Thread.sleep(5000);

        // Verify: Url / Dashboard page / Profile image
        String currentUrl = basePage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("dashboard"));

        // getText()
        TopHeader topHeader = new TopHeader(chromeDriver);
        String currentPageTitle = topHeader.getPageTitle();
        Assert.assertEquals(currentPageTitle, "Dashboard");

        // isVisible()
        boolean profileImageDisplay = topHeader.getDisplayOfProfileImage();
        Assert.assertTrue(profileImageDisplay, "Profile Image is not displayed");
    }

    @Test
    void loginSuccessfullyUsingForm() throws InterruptedException {
        // Open login url
        BasePage basePage = new BasePage();
        basePage.openUrl("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        // Login account
        ChromeDriver chromeDriver = basePage.getChromeDriver();
        LoginPage loginPage = new LoginPage(chromeDriver);
        Thread.sleep(5000);

        // Verify login button is enabled event username and password are not filled
        boolean loginButtonIsEnabled = loginPage.checkLoginButtonIsEnabled();
        Assert.assertTrue(loginButtonIsEnabled, "Login Button is not enabled");

        loginPage.inputUsername("Admin");
        loginPage.inputPassword("admin123");
        loginPage.submitFormLogin();
        Thread.sleep(5000);

        // Verify: Url / Dashboard page / Profile image
        String currentUrl = basePage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("dashboard"));

        // getText()
        TopHeader topHeader = new TopHeader(chromeDriver);
        String currentPageTitle = topHeader.getPageTitle();
        Assert.assertEquals(currentPageTitle, "Dashboard");

        // isVisible()
        boolean profileImageDisplay = topHeader.getDisplayOfProfileImage();
        Assert.assertTrue(profileImageDisplay, "Profile Image is not displayed");
    }

    @Test
    void verifyLoginFormCssStyle() throws InterruptedException {
        // Open login url
        BasePage basePage = new BasePage();
        basePage.openUrl("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        // Login account
        ChromeDriver chromeDriver = basePage.getChromeDriver();
        LoginPage loginPage = new LoginPage(chromeDriver);
        Thread.sleep(5000);

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
