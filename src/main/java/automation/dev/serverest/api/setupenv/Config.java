package automation.dev.serverest.api.setupenv;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static String PATH_PROP = "/src/main/resources/config.properties";
    private static String PATH_FULL = System.getProperty("user.dir") + PATH_PROP;
    private static Properties properties = new Properties();

    static {
        try {
            properties.load(new FileInputStream(PATH_FULL));
        } catch (IOException ex) {
            throw new RuntimeException("Error loading \"config.properties\" file", ex);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}