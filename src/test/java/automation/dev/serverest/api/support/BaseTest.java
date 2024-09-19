package automation.dev.serverest.api.support;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest implements Constants, Routes {
    protected static RequestSpecification requester;

    @BeforeEach
    public void setupRestAssured() {
        setConfigRequestSpecification();
        setConfigResponseSpecification();
        setConfigLogging();
    }

    private void setConfigRequestSpecification() {
        requester = RestAssured.given()
                .baseUri(Routes.APP_BASE_URL)
                .header("Content-Type", Constants.CONTENT_TYPE);
    }

    private void setConfigResponseSpecification() {
        ResponseSpecBuilder resBuilder = new ResponseSpecBuilder();
        resBuilder.expectResponseTime(Matchers.lessThan(Constants.MAX_TIMEOUT));
        RestAssured.responseSpecification = resBuilder.build();
    }

    private void setConfigLogging() {
        RestAssured.config = RestAssured.config()
                .logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails());
    }
}
