package automation.dev.serverest.api.setupenv;

import static java.lang.System.getProperty;

public class Environment {
    private static String environment = Config.get("app.environment");
    private static String baseUrl;
    private static String setEnv = getProperty("gotit", "hom-act");

    public static String getBaseUrl() {
        if (setEnv.equals("dev")) {
            baseUrl = Config.get("app.base.url.dev");
        } else if (setEnv.equals("hom")) {
            baseUrl = Config.get("app.base.url.hom");
        } else if (setEnv.equals("hom-act")){
            baseUrl = System.getenv("HOM_BASE_URL");
        } else {
            throw new IllegalArgumentException("Base URL is not set in config or environment variables");
        }
        return baseUrl;
    }
}