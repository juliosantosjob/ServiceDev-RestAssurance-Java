package automation.dev.serverest.api.usecases;

import automation.dev.serverest.api.base.BaseTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Map;
import java.util.Random;

import static automation.dev.serverest.api.services.GetUsersService.getUser;
import static org.apache.http.HttpStatus.SC_OK;

@Tag("regression")
@DisplayName("Feature: Teste de Obtenção de Usuário")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GetUserTest extends BaseTest {

    @Test
    @Tag("getUser")
    @DisplayName("Cenario 01: Deve obter um usuario na lista")
    public void getAUserInTheList() {
        Response response = getUser().then().statusCode(SC_OK).extract().response();
        List<Map<String, Object>> usuarios = response.jsonPath().getList("usuarios");

        int randomIndex = new Random().nextInt(usuarios.size());
        Map<String, Object> selectedUser = usuarios.get(randomIndex);

        Assertions.assertNotNull(selectedUser.get("nome"));
        Assertions.assertNotNull(selectedUser.get("email"));
        Assertions.assertNotNull(selectedUser.get("password"));
        Assertions.assertNotNull(selectedUser.get("administrador"));
    }
}