package automation.dev.serverest.api.setupenv;

import static java.lang.System.getProperty;

public class Environment {
    private static final String CLOUD = getProperty("cloud", "true");

    public static String getBaseUrl() {
        if (CLOUD.equals(null) || CLOUD.equals("true")) {
            return System.getenv("HOM_URL");
        } else {
            return Config.get("app.base.url.hom");
        }
    }
}
