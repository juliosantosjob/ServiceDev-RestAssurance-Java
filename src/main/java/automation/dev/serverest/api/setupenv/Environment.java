package automation.dev.serverest.api.setupenv;

public class Environment {
    private static String baseUrl;
    private static String urlFromConfig = Config.get("app.base.url.hom");

    public static String getBaseUrl() {
        if (urlFromConfig != null) {
            baseUrl = urlFromConfig;
        } else {
            baseUrl = System.getenv("APP_BASE_URL");
            if (baseUrl == null) {
                throw new RuntimeException("APP_BASE_URL environment variable not defined!");
            }
        }
        return baseUrl;
    }
}