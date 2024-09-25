package automation.dev.serverest.api.setupenv;

import static java.lang.System.getProperty;

public class Environment {
    private static final boolean CLOUD = Boolean.parseBoolean(getProperty("cloud", "true"));

    public static String getBaseUrl() {
        if (CLOUD) {
            return System.getenv("HOM_URL");
        } else {
            return Config.get("app.base.url.hom");
        }
    }
}
