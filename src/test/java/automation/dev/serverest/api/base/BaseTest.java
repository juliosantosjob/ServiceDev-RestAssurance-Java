package automation.dev.serverest.api.base;

import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;

public class BaseTest implements Constants, Routes {
    protected static RequestSpecification requester;

    @Before
    public static void setupRestAssured() {
        // Objeto de solicitação rest-assurance
        requester = RestAssured
                .given()
                .baseUri(APP_BASE_URL)
                .header("Content-Type", CONTENT_TYPE);

        // Define um timeout maximo para a execução de cada chamada
        ResponseSpecBuilder resBuilder = new ResponseSpecBuilder();
        resBuilder.expectResponseTime(Matchers.lessThan(MAX_TIMEOUT));
        RestAssured.responseSpecification = resBuilder.build();

        // Habilita os logs da chamada caso ocorra algum erro
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}