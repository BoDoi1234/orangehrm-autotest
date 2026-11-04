package demo.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginPage extends BasePage {
    private final ChromeDriver chromeDriver;
    private final By usernameTextBox = By.name("username");
    private final By passwordTextBox = By.name("password");
    private final By loginButton = By.cssSelector("button[class*='login-button']");
    private final By loginForm = By.xpath("//form[@method='post']");

    public LoginPage(ChromeDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
    }

    public void inputUsername(String username) {
        chromeDriver.findElement(usernameTextBox).clear();
        chromeDriver.findElement(usernameTextBox).sendKeys(username);
    }

    public void inputPassword(String password) {
        chromeDriver.findElement(passwordTextBox).clear();
        chromeDriver.findElement(passwordTextBox).sendKeys(password);
    }

    public void clickLoginButton() {
        chromeDriver.findElement(loginButton).click();
    }

    public void submitFormLogin() {
        chromeDriver.findElement(loginForm).submit();
    }

    public boolean checkLoginButtonIsEnabled() {
        return chromeDriver.findElement(loginButton).isEnabled();
    }

    public String getBackgroundColorOfLoginButton() {
        return getCssValueOf("background-color", chromeDriver.findElement(loginButton));
    }

    public String getColorOfLoginButton() {
        return getCssValueOf("color", chromeDriver.findElement(loginButton));
    }

    public String getAttributeNameOfUsername() {
        return chromeDriver.findElement(usernameTextBox).getAttribute("name");
    }
}
