package demo.orangehrm.learn;

import demo.orangehrm.pages.BasePage;
import demo.orangehrm.utils.DriverUtils;
import demo.orangehrm.utils.TestDataHelper;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class TestMultipleLanguages {
    private final DriverUtils driverUtils = new DriverUtils();
    private WebDriver driver;

    @BeforeMethod
    void setUp() {
        // Init browser
        driver = driverUtils.initBrowser();

        // Open url
        AgodaLandingPage agodaLandingPage = new AgodaLandingPage(driver);
        agodaLandingPage.openUrl(TestDataHelper.getDataFromPropertiesOf("agoda_url"));

        // Set test language
        agodaLandingPage.setAgodaAppLanguage(BasePage.testedLanguage);
    }

    @AfterMethod(alwaysRun = true)
    void tearDown() {
        driverUtils.quitBrowser(driver);
    }

    @Test
    void testLandingPageDisplay() {
        AgodaLandingPage agodaLandingPage = new AgodaLandingPage(driver);

        // Verify Welcome Message
        String actualWelcomeMsg = agodaLandingPage.getWelcomeMessage();
        String designWelcomeMessage = TestDataHelper.getDataFromPropertiesOf("agoda_landing_page_welcome_message");
        Assert.assertEquals(actualWelcomeMsg.toUpperCase(), designWelcomeMessage);

        // Verify Tab List
        List<String> actualTabList = agodaLandingPage.getAllTabName();
        List<String> expectedTabList = List.of(TestDataHelper.getDataFromPropertiesOf("agoda_landing_page_tab_name_list").split(", "));
        Assert.assertEquals(actualTabList, expectedTabList);
    }

    @Test
    void testLandingPageDisplayUsingJsonTestData() {
        AgodaLandingPage agodaLandingPage = new AgodaLandingPage(driver);

        // Verify Welcome Message
        String actualWelcomeMsg = agodaLandingPage.getWelcomeMessage();
        String designWelcomeMessage = TestDataHelper.getTestDataOf("agoda.pages.landing.welcome_message");
        Assert.assertEquals(actualWelcomeMsg.toUpperCase(), designWelcomeMessage);

        // Verify Tab List
        List<String> actualTabList = agodaLandingPage.getAllTabName();
        List<String> expectedTabList = List.of(TestDataHelper.getTestDataOf("agoda.pages.landing.tab_name_list").split(", "));
        Assert.assertEquals(actualTabList, expectedTabList);
    }
}
