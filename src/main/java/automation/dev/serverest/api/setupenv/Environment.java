package automation.dev.serverest.api.setupenv;

import java.security.InvalidParameterException;

import static java.lang.System.getProperty;

public class Environment {
    private static final String CURRENT_ENVIRONMENT = getProperty("env", "hom");

    public static String getBaseUrl() {
        switch (CURRENT_ENVIRONMENT) {
            case "dev":
                return Config.get("app.base.url.dev");
            case "hom":
                return Config.get("app.base.url.hom");
            case "dev-act":
                return System.getenv("DEV_URL");
            case "hom-act":
                return System.getenv("HOM_URL");
            default:
                throw new InvalidParameterException("Invalid environment: " + CURRENT_ENVIRONMENT +
                        ". Available options are \"dev\", \"hom\", \"dev-act\", \"hom-act\".");
        }
    }
}