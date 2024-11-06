package automation.dev.serverest.api.setupenv;

public class Environment {
    private static String HOM_URL = Config.get("app.base.url.hom");
    private static String HOM_URL_ENV = System.getenv("HOM_URL");
    private static String baseUrl = "";

    public static String getBaseUrl() {
      try {
          baseUrl = (HOM_URL == null) ? HOM_URL_ENV : HOM_URL;
      } catch (Exception e) {
          throw new RuntimeException("Failed to get base URL: " +e);
      }
        return baseUrl;
    }
}
