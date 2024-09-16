package automation.dev.serverest.api.base;

import io.restassured.http.ContentType;

import static automation.dev.serverest.api.utils.EnvProp.findEnv;

public interface Constants {
    Long MAX_TIMEOUT = 3000L;
    String APP_BASE_URL = findEnv("BASE_URL");
    ContentType APP_CONTENT_TYPE = ContentType.JSON;
}
