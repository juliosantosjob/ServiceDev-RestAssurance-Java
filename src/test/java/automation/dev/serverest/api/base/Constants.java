package automation.dev.serverest.api.base;

import io.restassured.http.ContentType;

public interface Constants {
    ContentType APP_CONTENT_TYPE = ContentType.JSON;
    Long MAX_TIMEOUT = 3000L;
}
