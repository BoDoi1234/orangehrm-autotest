package demo.orangehrm.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class FileUtil {
    public Properties readPropertiesFile(String filePath) {
        Properties properties = new Properties();

        try {
            InputStream inputStream = new FileInputStream(filePath);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            properties.load(inputStreamReader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return properties;
    }

    public JsonNode readJsonFile(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(new File(filePath));
            return rootNode;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
