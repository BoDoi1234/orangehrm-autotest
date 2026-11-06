package demo.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private final By usernameTextBox = By.name("username");
    private final By passwordTextBox = By.name("password");
    private final By loginButton = By.cssSelector("button[class*='login-button']");
    private final By loginForm = By.xpath("//form[@method='post']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void inputUsername(String username) {
        sendKeys(usernameTextBox, username);
    }

    public void inputPassword(String password) {
        sendKeys(passwordTextBox, password);
    }

    public void clickLoginButton() {
        click(loginButton);
    }

    public void submitFormLogin() {
        submitForm(loginForm);
    }

    public boolean checkLoginButtonIsEnabled() {
        return isElementEnable(loginButton);
    }

    public String getBackgroundColorOfLoginButton() {
        return getCssValueOf(loginButton, "background-color");
    }

    public String getColorOfLoginButton() {
        return getCssValueOf(loginButton, "color");
    }

    public String getAttributeNameOfUsername() {
        return getAttribute(usernameTextBox, "name");
    }
}
