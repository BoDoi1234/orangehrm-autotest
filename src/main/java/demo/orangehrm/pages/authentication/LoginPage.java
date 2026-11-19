package demo.orangehrm.pages.authentication;

import demo.orangehrm.pages.BasePage;
import demo.orangehrm.pages.dashboard.DashboardPage;
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

    private void inputUsername(String username) {
        inputTextInToTextBox(usernameTextBox, username);
        logger.info("Type {} in username textbox", username);
    }

    private void inputPassword(String password) {
        inputTextInToTextBox(passwordTextBox, password);
        logger.info("Type {} in password textbox", password);
    }

    private void clickLoginButton() {
        click(loginButton);
        logger.info("Click login button\n");
    }

    private void submitFormLogin() {
        submitForm(loginForm);
        logger.info("Submit form login\n");
    }

    private void performLogin(String username, String password) {
        inputUsername(username);
        inputPassword(password);
        clickLoginButton();
    }

    public DashboardPage loginWithValidCredentials(String username, String password) {
        performLogin(username, password);
        waitForUrlContains("dashboard");
        logger.info("Login successful -> Navigate to Dashboard Page\n");
        return new DashboardPage(driver);
    }

    public LoginPage loginWithInvalidCredentials(String username, String password) {
        performLogin(username, password);
        waitForElementVisibility(loginErrorContent);
        logger.info("Login failed -> Stay on Login Page\n");
        return this;
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
