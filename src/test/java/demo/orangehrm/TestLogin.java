package demo.orangehrm;

import demo.orangehrm.pages.BasePage;
import demo.orangehrm.pages.LoginPage;
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

        loginPage.inputUsername("Admin");
        loginPage.inputPassword("admin123");
        loginPage.clickLoginButton();

        // Verify: Url / Dashboard page / Profile image
        String currentUrl = basePage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("dashboard"));

        // getText()
        // isVisible()
    }
}
