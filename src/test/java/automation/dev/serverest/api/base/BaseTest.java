package automation.dev.serverest.api.base;

import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import org.hamcrest.Matchers;

public class BaseTest implements Constants {

    @Before
    public static void setupRestAssured() {
        RestAssured.baseURI = APP_BASE_URL;
        RestAssured.requestSpecification = RestAssured.given()
                .header("Content-Type", CONTENT_TYPE);

        // Define um timeout maximo para a execução de cada chamada
        ResponseSpecBuilder resBuilder = new ResponseSpecBuilder();
        resBuilder.expectResponseTime(Matchers.lessThan(MAX_TIMEOUT));
        RestAssured.responseSpecification = resBuilder.build();

        // Habilita os logs da chamada caso ocorra algum erro
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}