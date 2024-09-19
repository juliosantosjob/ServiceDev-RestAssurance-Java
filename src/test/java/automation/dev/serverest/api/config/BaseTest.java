package automation.dev.serverest.api.config;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest implements Constants, Routes {
    protected static RequestSpecification requester;

    @BeforeEach
    public void setupRestAssured() {
        requester = RestAssured.given()
                .baseUri(Routes.APP_BASE_URL)
                .header("Content-Type", Constants.CONTENT_TYPE);

        ResponseSpecBuilder resBuilder = new ResponseSpecBuilder();
        resBuilder.expectResponseTime(Matchers.lessThan(Constants.MAX_TIMEOUT));
        RestAssured.responseSpecification = resBuilder.build();

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}