package automation.dev.serverest.api.usecases;

import automation.dev.serverest.api.base.BaseTest;
import automation.dev.serverest.api.models.NewUsersModel;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Map;
import java.util.Random;

import static automation.dev.serverest.api.utils.Helpers.*;
import static automation.dev.serverest.api.utils.Reports.attachmentsAllure;

@Tag("regression")
@Tag("getUserRegression")
@DisplayName("Feature: Teste de Obtenção de Usuário")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GetUserTest extends BaseTest {
    private Response response;

    @AfterEach
    public void endSetup() {
        attachmentsAllure(response);
    }

    @Test
    @Tag("getUser")
    @DisplayName("Cenario 01: Deve obter um usuario na lista")
    public void getAUserInTheList() {
        response = getUserList();
        List<Map<String, Object>> usuarios = response.jsonPath().getList("usuarios");

        int randomIndex = new Random().nextInt(usuarios.size());
        Map<String, Object> selectedUser = usuarios.get(randomIndex);

        Assertions.assertNotNull(selectedUser.get("nome"));
        Assertions.assertNotNull(selectedUser.get("email"));
        Assertions.assertNotNull(selectedUser.get("password"));
        Assertions.assertNotNull(selectedUser.get("administrador"));
    }
}