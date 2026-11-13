package demo.orangehrm.agoda_learn;

import demo.orangehrm.pages.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AgodaLandingPage extends BasePage {
    private final Logger logger = LogManager.getLogger(AgodaLandingPage.class);
    private final By welcomeMsg = By.cssSelector("h1");
    private final By tabNameList = By.xpath("//div[@role='tablist']/div//p");
    private final By closeDownloadButton = By.xpath("//button[contains(@data-element-name, \"app-download\")]");

    public AgodaLandingPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void openUrl(String url) {
        driver.get(url);
        WebElement closeDownloadAppBtn = waitForElementVisibility(closeDownloadButton);
        if (closeDownloadAppBtn.isDisplayed()) {
            closeDownloadAppBtn.click();
        }
    }

    public String getWelcomeMessage() {
        String welcomeMessage = getText(welcomeMsg);
        logger.info("Welcome Message is {}\n", welcomeMessage);
        return welcomeMessage;
    }

    public List<String> getTabListName() {
        List<String> tabListName = getTextList(tabNameList);
        logger.info("Tab list name = {}\n", tabListName);
        return tabListName;
    }

    /**
     *  Check:
     *      If current language same as testedLanguage then do nothing
     *      Else set application language to testedLanguage
     *
     *  @param testedLanguage expected language to be set
     */
    public void setAgodaAppLanguage(String testedLanguage) {
        click(AgodaHeader.userMenuButton);
        String currentLanguage = getText(AgodaUserMenu.selectedLanguageText).trim();
        String convertedLanguage = convertLanguageToUIDisplay(testedLanguage);

        if (!currentLanguage.equalsIgnoreCase(convertedLanguage)) {
            click(AgodaUserMenu.changeLanguageButton);
            By targetLanguageLocator = By.xpath(String.format("//p[text()='%s']", convertedLanguage));
            click(targetLanguageLocator);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else {
            click(AgodaHeader.userMenuButton);
            logger.info("Language is already {}\n", convertedLanguage);
        }
    }

    private String convertLanguageToUIDisplay(String language) {
        return switch (language.toLowerCase()) {
            case "english" -> "English";
            case "vietnamese" -> "Tiếng Việt";
            case "chinese" -> "简体中文";
            case "german" -> "Deutsch";
            case "italian" -> "Italiano";
            case "korean" -> "한국어";
            case "japanese" -> "日本語";
            default -> throw new RuntimeException(String.format("Not supported %s language", language));
        };
    }
}
