package automation.dev.serverest.api.base;

import io.restassured.http.ContentType;

import static automation.dev.serverest.api.utils.EnvProp.findProperty;

public interface Constants {
    Long MAX_TIMEOUT = 3000L;
    String APP_BASE_URL = findProperty("BASE_URL");
    ContentType APP_CONTENT_TYPE = ContentType.JSON;
}
