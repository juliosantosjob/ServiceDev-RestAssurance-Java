package automation.dev.serverest.api.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class EnvProp {
    private static final String PATH_PROP = "/src/test/resources/config/env.properties";
    private static final String LOAD_PATH = System.getProperty("user.dir") + PATH_PROP;
    private static final Properties properties = new Properties();

    static {
        try (FileInputStream inputStream = new FileInputStream(LOAD_PATH)) {
            properties.load(inputStream);
        } catch (IOException e) {
            System.err.println("Could not read file with params: \"" + LOAD_PATH + "\" " + e);
        }
    }

    public static String findEnv(String key) {
        return properties.getProperty(key);
    }
}