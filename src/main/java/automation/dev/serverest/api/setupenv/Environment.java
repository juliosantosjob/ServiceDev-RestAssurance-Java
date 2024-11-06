package automation.dev.serverest.api.setupenv;

public class Environment {
    private static String HOM_URL = System.getenv("HOM_URL");

    public static String getBaseUrl() {
        if (HOM_URL == null) {
            return Config.getEnv("app.base.url.hom");
        }
        return HOM_URL;
    }
}