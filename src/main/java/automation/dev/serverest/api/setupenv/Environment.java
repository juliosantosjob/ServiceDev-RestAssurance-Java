package automation.dev.serverest.api.setupenv;

public class Environment {
    private static String environment = Config.get("app.environment");
    private static String baseUrl = "";

    public static String getBaseUrl() {
        if ("dev".equalsIgnoreCase(environment)) {
            baseUrl = Config.get("app.base.url.dev");
        } else if ("hom".equalsIgnoreCase(environment)) {
            baseUrl = Config.get("app.base.url.hom");
        } else if (baseUrl == null){
            baseUrl = System.getenv("APP_BASE_URL");
        } else {
            throw new IllegalArgumentException("Base URL is not set in config or environment variables");
        }
        return baseUrl;
    }
}