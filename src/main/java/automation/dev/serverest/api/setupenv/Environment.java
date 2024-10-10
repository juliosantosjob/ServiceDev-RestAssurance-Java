package automation.dev.serverest.api.setupenv;

public class Environment {
    private static String HOM_URL = Config.get("app.base.url.hom");
    private static String HOM_URL_ENV = System.getenv("HOM_URL");

    public static String getBaseUrl() {

        if (HOM_URL_ENV != null) {
            return HOM_URL_ENV;
        }

        if (HOM_URL != null) {
            return HOM_URL;
        }

        System.err.println("***** Please set the \"HOM_URL\" environment variable or configure \"app.base.url.hom\" *****");
        return null;
    }
}
