package automation.dev.serverest.api.setupenv;

import static java.lang.System.getProperty;

public class Environment {
    private static String mainEnv = getProperty("env", "hom-act");

    public static String getBaseUrl() {
        String baseUrl = null;

        switch (mainEnv) {
            case "dev":
                baseUrl = Config.get("app.base.url.dev");
                break;
            case "hom":
                baseUrl = Config.get("app.base.url.hom");
                break;
            case "hom-act":
                baseUrl = System.getenv("HOM_BASE_URL");
                break;
        }

        if (baseUrl == null || baseUrl.isEmpty()) {
            throw new IllegalArgumentException("Base URL is not set in config or environment variables");
        }
        return baseUrl;
    }
}