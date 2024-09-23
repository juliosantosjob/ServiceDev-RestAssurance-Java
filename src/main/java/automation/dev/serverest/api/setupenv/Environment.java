package automation.dev.serverest.api.setupenv;

public class Environment {
    private static String environment = Config.get("app.environment");
    private static String baseUrl;
    private static String env;


    public static String getBaseUrl() {
        if (env.equals("dev")) {
            baseUrl = Config.get("app.base.url.dev");
        } else if (env.equals("hom")) {
            baseUrl = Config.get("app.base.url.hom");
        } else if (env.equals("hom-act")){
            baseUrl = System.getenv("HOM_BASE_URL");
        } else {
            throw new IllegalArgumentException("Base URL is not set in config or environment variables");
        }
        return baseUrl;
    }
}