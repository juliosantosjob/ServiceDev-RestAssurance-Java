package automation.dev.serverest.api.base;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.Before;

public class BaseTest implements Constants {
    protected static RequestSpecification request;

    @Before
    public void setUp() {
        // Configura a base URI diretamente no given()
        RestAssured.baseURI = APP_BASE_URL;
        request = RestAssured.given();

        // Definindo ContentTypeJson como padrão para todos os testes
        RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
        reqBuilder.setContentType(APP_CONTENT_TYPE);
        RestAssured.requestSpecification = reqBuilder.build();

        // Define um timeout maximo para a execução de cada chamada
        ResponseSpecBuilder resBuilder = new ResponseSpecBuilder();
        resBuilder.expectResponseTime(Matchers.lessThan(MAX_TIMEOUT));
        RestAssured.responseSpecification = resBuilder.build();

        // Habilita os logs da chamada caso ocorra algum erro
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}