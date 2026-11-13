package demo.orangehrm.agoda_learn;

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
    private AgodaLandingPage agodaLandingPage;

    @BeforeMethod
    void setUp() {
        // Init browser
        driver = driverUtils.initBrowser();

        // Open url
        agodaLandingPage = new AgodaLandingPage(driver);
        agodaLandingPage.openUrl(TestDataHelper.getDataOf("agoda_url"));

        // Set test language
        agodaLandingPage.setAgodaAppLanguage(BasePage.testedLanguage);
    }

    @AfterMethod(alwaysRun = true)
    void tearDown() {
        driverUtils.quitBrowser(driver);
    }

    @Test
    void testLandingPageDisplay() {
        // Verify Welcome Message
        String actualWelcomeMsg = agodaLandingPage.getWelcomeMessage();
        String designWelcomeMessage = TestDataHelper.getDataOf("agoda_landing_page_welcome_message");
        Assert.assertEquals(actualWelcomeMsg.toUpperCase(), designWelcomeMessage);

        // Verify Tab List
        List<String> actualTabList = agodaLandingPage.getTabListName();
        List<String> expectedTabList = List.of(TestDataHelper.getDataOf("agoda_landing_page_tab_name_list").split(", "));
        Assert.assertEquals(actualTabList, expectedTabList);
    }
}
