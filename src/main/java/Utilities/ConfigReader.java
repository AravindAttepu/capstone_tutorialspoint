package Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private Properties properties;

    public ConfigReader(String filePath) {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            properties = new Properties();
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
