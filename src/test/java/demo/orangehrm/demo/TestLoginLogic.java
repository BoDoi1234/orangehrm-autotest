package demo.orangehrm.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestLoginLogic {
    private static Logger logger;
    private static ChromeDriver chromeDriver;

    @BeforeAll
    static void setUp() {
        logger = LogManager.getLogger(TestLoginLogic.class);

        logger.info("Init Web Driver\n");
        chromeDriver = new ChromeDriver();
    }

    @BeforeAll
    static void openOrangeHRM() {
        logger.info("Prepare access web");
        chromeDriver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        chromeDriver.manage().window().maximize();
        logger.info("Accessed OrangeHRMDemo Page\n");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

//    @AfterAll
//    static void closeSession() {
//        chromeDriver.quit();
//    }

    @Test
    void TestLogin() {
        WebElement username = chromeDriver.findElement(By.cssSelector("input[name = 'username']"));
        WebElement password = chromeDriver.findElement(By.xpath("//input[@name = 'password']"));

        username.sendKeys("Admin");
        logger.info("Typed 'Admin' to username");
        password.sendKeys("admin123");
        logger.info("Typed 'admin123' to password\n");

        WebElement loginButton = chromeDriver.findElement(By.xpath("//button[@type = 'submit' and text() = ' Login ']"));
        loginButton.click();
        logger.info("Login successful\n");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        logger.info("Check logo OrangeHRM");
        WebElement oxdBranch = chromeDriver.findElement(By.xpath("//a[@href = 'https://www.orangehrm.com/']"));
        Assertions.assertTrue(oxdBranch.isEnabled());
        logger.info("Logo OrangeHRM is enabled");
    }
}
