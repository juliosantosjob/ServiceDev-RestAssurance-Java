package automation.dev.serverest.api.setupenv;

public class Environment {
    private static String baseUrl;

    public static String getBaseUrl() {
        String urlFromConfig = Config.get("app.base.url.hom");

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
