package demo.orangehrm.utils;

import demo.orangehrm.pages.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

public class TestDataHelper {
    private final static Logger logger = LogManager.getLogger(TestDataHelper.class);
    private final static Properties dataProperties = new Properties();
    private final static String dataLocation = "src/test/resources/data/";
    private final static String environmentDataLocation = String.format("%s%s", dataLocation, "test_environment/");
    private final static String languageDataLocation = String.format("%s%s", dataLocation, "language/");

    public static String getDataOf(String dataKey) {
        if (dataProperties.isEmpty()) {
            TestDataHelper testDataHelper = new TestDataHelper();
            testDataHelper.loadEnvironmentTestData();
            testDataHelper.loadLanguageTestData();
        }
        return dataProperties.getProperty(dataKey);
    }

    public void loadEnvironmentTestData() {
        String commonFilePath = environmentDataLocation + "common.properties";

        String testedEnv = System.getProperty("testedEnv", "production");
        String filePath = switch (testedEnv) {
            case "dev" -> environmentDataLocation + "dev.properties";
            case "staging" -> environmentDataLocation + "staging.properties";
            case "production" -> environmentDataLocation + "production.properties";
            default -> throw new RuntimeException(String.format("Unsupported test environment: %s", testedEnv));
        };

        Properties commonProperties = new FileUtil().readPropertiesFile(commonFilePath);
        Properties specificProperties = new FileUtil().readPropertiesFile(filePath);
        commonProperties.putAll(specificProperties);
        dataProperties.putAll(commonProperties);
        logger.info("Test Data of Environment: {}\n", commonProperties);
    }

    public void loadLanguageTestData() {
        String testedLanguage = BasePage.testedLanguage;
        String filePath = languageDataLocation + testedLanguage + ".properties";

        Properties specificLanguageProperties = new FileUtil().readPropertiesFile(filePath);
        dataProperties.putAll(specificLanguageProperties);
        logger.info("Test Data of Language: {}\n", dataProperties);
    }
}
