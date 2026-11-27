package demo.orangehrm.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import demo.orangehrm.pages.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class TestDataHelper {
    private final static Logger logger = LogManager.getLogger(TestDataHelper.class);
    private final static Properties dataProperties = new Properties();
    private static JsonNode dataJson;
    private final static String dataLocation = "src/test/resources/data/";
    private final static String environmentDataLocation = String.format("%s%s", dataLocation, "environment/properties/");
    private final static String environmentDataJsonLocation = String.format("%s%s", dataLocation, "environment/json/");
    private final static String languageDataLocation = String.format("%s%s", dataLocation, "language/properties/");
    private final static String languageDataJsonLocation = String.format("%s%s", dataLocation, "language/json/");

    public static String getTestDataOf(String dataKeyPath) {
        if (dataJson == null) {
            TestDataHelper testDataHelper = new TestDataHelper();
            testDataHelper.loadEnvironmentTestData();
            testDataHelper.loadLanguageTestData();
        }

        String value = dataJson.at(String.format("/%s", dataKeyPath.replace(".", "/"))).asText();
        if (value.isEmpty()) {
            throw new RuntimeException("Key is not defined in json test data file!");
        }
        return value;
    }

    public static String getDataFromPropertiesOf(String dataKey) {
        if (dataProperties.isEmpty()) {
            TestDataHelper testDataHelper = new TestDataHelper();
            testDataHelper.loadEnvironmentTestDataProperties();
            testDataHelper.loadLanguageTestDataProperties();
        }
        return dataProperties.getProperty(dataKey);
    }

    private void loadEnvironmentTestData() {
        String commonFilePath = environmentDataJsonLocation + "common.json";

        String testedEnv = System.getProperty("testedEnv", "production");
        String filePath = switch (testedEnv) {
            case "dev" -> environmentDataJsonLocation + "dev.json";
            case "staging" -> environmentDataJsonLocation + "staging.json";
            case "production" -> environmentDataJsonLocation + "production.json";
            default -> throw new RuntimeException(String.format("Unsupported test environment: %s", testedEnv));
        };

        JsonNode rootNode = new FileUtil().readJsonFile(filePath);
        JsonNode rootNodeCommon = new FileUtil().readJsonFile(commonFilePath);
        dataJson = mergeJsonNode(rootNodeCommon, rootNode);
    }

    private JsonNode mergeJsonNode(JsonNode rootNodeCommon, JsonNode rootNode) {
        try {
            return new ObjectMapper().readerForUpdating(rootNodeCommon).readValue(rootNode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadLanguageTestData() {
        String testedLanguage = BasePage.testedLanguage;
        String filePath = languageDataJsonLocation + testedLanguage + ".json";

        JsonNode rootNode = new FileUtil().readJsonFile(filePath);
        dataJson = mergeJsonNode(dataJson, rootNode);
    }

    private void loadEnvironmentTestDataProperties() {
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

    private void loadLanguageTestDataProperties() {
        String testedLanguage = BasePage.testedLanguage;
        String filePath = languageDataLocation + testedLanguage + ".properties";

        Properties specificLanguageProperties = new FileUtil().readPropertiesFile(filePath);
        dataProperties.putAll(specificLanguageProperties);
        logger.info("Test Data of Language: {}\n", dataProperties);
    }
}
