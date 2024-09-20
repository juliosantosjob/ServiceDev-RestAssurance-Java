package automation.dev.serverest.api.tests;

import automation.dev.serverest.api.models.NewUsersModel;
import automation.dev.serverest.api.support.BaseTest;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Map;
import java.util.Random;

import static automation.dev.serverest.api.requests.GetUsersRequest.getUser;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GetUserTest extends BaseTest {

    @Test
    @Tag("getUser")
    @DisplayName("Cenario 01: Deve obter um usuario na lista")
    public void getAUserInTheList() {
        Response response = getUser().then().statusCode(SC_OK).extract().response();
        List<Map<String, Object>> usuarios = response.jsonPath().getList("usuarios");

        int randomIndex = new Random().nextInt(usuarios.size());
        getUser()
                .then()
                .statusCode(SC_OK)
                .body("usuarios[" + randomIndex + "].nome", notNullValue())
                .body("usuarios[" + randomIndex + "].email", notNullValue())
                .body("usuarios[" + randomIndex + "].password", notNullValue())
                .body("usuarios[" + randomIndex + "].administrador", notNullValue());
    }
}
