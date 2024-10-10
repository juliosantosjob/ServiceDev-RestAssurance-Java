package automation.dev.serverest.api.setupenv;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static Properties properties = new Properties();

    static {
        try (InputStream input = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("***** Could not find file \"config.properties\" in classpath. Make sure it exists. *****");
            }
            properties.load(input);
        } catch (IOException ex) {
            throw new RuntimeException("Error loading config.properties file", ex);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}