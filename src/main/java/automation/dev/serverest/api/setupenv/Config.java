package automation.dev.serverest.api.setupenv;

import java.io.FileInputStream;
import java.util.Properties;

public class Config {
    private static String PATH_PROP = "/src/main/resources/config.properties";
    private static String LOAD_PATH = System.getProperty("user.dir") + PATH_PROP;
    private static Properties properties = new Properties();

    private static Properties loadProp() {
        try {
            properties.load(new FileInputStream(LOAD_PATH));

        } catch (Exception e) {
            System.out.println("**** WARNING: Missing config.properties. **** " + e);
        }
        return properties;
    }

    public static String get(String env) {
        return loadProp().getProperty(env);
    }
}