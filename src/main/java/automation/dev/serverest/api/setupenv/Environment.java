package automation.dev.serverest.api.setupenv;

public class Environment {
    public static String getBaseUrl() {
        String environment = Config.get("app.environment");
        String baseUrl = null;

        if ("dev".equalsIgnoreCase(environment)) {
            baseUrl = Config.get("app.base.url.dev");
        } else if ("hom".equalsIgnoreCase(environment)) {
            baseUrl = Config.get("app.base.url.hom");
        }

        if (baseUrl == null) {
            baseUrl = System.getenv("APP_BASE_URL");
        }

        if (baseUrl == null) {
            throw new IllegalArgumentException("Base URL is not set in config or environment variables");
        }

        return baseUrl;
    }
}