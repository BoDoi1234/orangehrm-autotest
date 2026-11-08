package demo.orangehrm.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private final Logger logger = LogManager.getLogger(LoginPage.class);
    private final By loginErrorContent = By.xpath("//*[contains(@class, 'login-form')]//*[contains(@class, 'content--error')]");
    private final By usernameTextBox = By.name("username");
    private final By passwordTextBox = By.name("password");
    private final By loginButton = By.cssSelector("button[class*='login-button']");
    private final By loginForm = By.xpath("//form[@method='post']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void inputUsername(String username) {
        sendKeys(usernameTextBox, username);
        logger.info("Type {} in username textbox", username);
    }

    public void inputPassword(String password) {
        sendKeys(passwordTextBox, password);
        logger.info("Type {} in password textbox", password);
    }

    public void clickLoginButton() {
        click(loginButton);
        logger.info("Click login button\n");
    }

    public void submitFormLogin() {
        submitForm(loginForm);
        logger.info("Submit form login\n");
    }

    public boolean checkLoginButtonIsEnabled() {
        boolean isEnabled = isElementEnabled(loginButton);
        logger.info("Login button {} enabled\n", isEnabled ? "is" : "is not");
        return isEnabled;
    }

    public String getMessageError() {
        String messageError = getText(loginErrorContent);
        logger.info("Message error is: {}\n", messageError);
        return messageError;
    }

    public String getBackgroundColorOfLoginButton() {
        String backgroundColorOfLoginButton = getCssValueOf(loginButton, "background-color");
        logger.info("Background color of Login button is {}\n", backgroundColorOfLoginButton);
        return backgroundColorOfLoginButton;
    }

    public String getColorOfLoginButton() {
        String colorOfLoginButton = getCssValueOf(loginButton, "color");
        logger.info("Color of Login button is {}\n", colorOfLoginButton);
        return colorOfLoginButton;
    }

    public String getAttributeNameOfUsername() {
        String attributeNameOfUsername = getAttribute(usernameTextBox, "name");
        logger.info("Attribute of Username is {}\n", attributeNameOfUsername);
        return attributeNameOfUsername;
    }
}
