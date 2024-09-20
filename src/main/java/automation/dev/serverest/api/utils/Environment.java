package automation.dev.serverest.api.utils;

public class Environment {
    public static String getBaseUrl() {
        String environment = Config.get("app.environment");
        if ("dev".equalsIgnoreCase(environment)) {
            return Config.get("app.base.url.dev");
        } else if ("hom".equalsIgnoreCase(environment)) {
            return Config.get("app.base.url.hom");
        }
        throw new IllegalArgumentException("Ambiente inválido: " + environment);
    }
}