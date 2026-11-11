package demo.orangehrm.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

public class TestDataHelper {
    private final static Logger logger = LogManager.getLogger(TestDataHelper.class);
    private final static Properties dataProperties = new Properties();
    private final static String dataLocation = "src/test/resources/data/";

    public static String getDataOf(String dataKey) {
        if (dataProperties.isEmpty()) {
            new TestDataHelper().loadTestData();
        }
        return dataProperties.getProperty(dataKey);
    }

    public void loadTestData() {
        String commonFilePath = dataLocation + "common.properties";

        String testedEnv = System.getProperty("testedEnv", "production");
        String filePath = switch (testedEnv) {
            case "dev" -> dataLocation + "dev.properties";
            case "staging" -> dataLocation + "staging.properties";
            case "production" -> dataLocation + "production.properties";
            default -> throw new RuntimeException(String.format("Unsupported test environment: %s", testedEnv));
        };

        Properties commonProperties = new FileUtil().readPropertiesFile(commonFilePath);
        Properties specificProperties = new FileUtil().readPropertiesFile(filePath);
        dataProperties.putAll(commonProperties);
        dataProperties.putAll(specificProperties);
        logger.info("Test Data: {}", dataProperties);
    }
}
