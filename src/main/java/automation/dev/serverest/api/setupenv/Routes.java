package automation.dev.serverest.api.setupenv;

import static automation.dev.serverest.api.setupenv.Environment.getBaseUrl;

public interface Routes {
    String APP_BASE_URL = getBaseUrl();
    String USERS = "/usuarios/";
    String LOGIN = "/login/";
}