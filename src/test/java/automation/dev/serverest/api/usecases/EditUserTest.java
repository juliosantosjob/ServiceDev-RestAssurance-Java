package automation.dev.serverest.api.usecases;

import automation.dev.serverest.api.base.BaseTest;
import automation.dev.serverest.api.models.NewUsersModel;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static automation.dev.serverest.api.services.EditUserService.editUser;
import static automation.dev.serverest.api.utils.Helpers.*;
import static automation.dev.serverest.api.utils.Reports.attachmentsAllure;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.*;

@Tag("regression")
@Tag("edituserRegression")
@DisplayName("Feature: Testes de Edição de Usuário")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EditUserTest extends BaseTest {
    private NewUsersModel randomUsers = getRandomUser();
    private Response response;
    private String userId;

    @BeforeEach
    public void initsetup() {
        userId = createRandomUser(randomUsers)
                .extract()
                .path("_id")
                .toString();
    }

    @AfterEach
    public void endsetup() {
        deleteUserById(userId);
        attachmentsAllure(response);
    }

    @Test
    @Order(1)
    @Tag("editUserSuccess")
    @DisplayName("Cenario 01: Deve realizar edição com sucesso")
    public void editUserSuccessful() {
        response = editUser(randomUsers, userId);
        response.then()
                .statusCode(SC_OK)
                .body("message", equalTo("Registro alterado com sucesso"));
    }

    @Test
    @Order(2)
    @Tag("editUserInvalidData")
    @DisplayName("Cenario 02: Deve falhar ao realizar edição com todos os dados em branco")
    public void editUserWithInvalidData() {
        NewUsersModel invalidUser = new NewUsersModel("", "", "", "");
        response = editUser(invalidUser, userId);
        response.then()
                .statusCode(SC_BAD_REQUEST)
                .body("nome", equalTo("nome não pode ficar em branco"));
    }

    @Test
    @Order(3)
    @Tag("editUserNonExistent")
    @DisplayName("Cenario 03: Deve criar um novo usuário ao tentar editar um usuário inexistente")
    public void editNonExistentUser() {
        NewUsersModel someUser = getRandomUser();
        response = editUser(someUser, "non_existent_id");
        response.then()
                .statusCode(SC_CREATED)
                .body("message", equalTo("Cadastro realizado com sucesso"));
    }

    @Test
    @Order(4)
    @Tag("editUserNullFields")
    @DisplayName("Cenario 04: Deve falhar ao realizar edição com campos nulos")
    public void editUserWithNullFields() {
        NewUsersModel nullFieldsUser = new NewUsersModel(null, null, null, null);
        response = editUser(nullFieldsUser, userId);
        response.then()
                .statusCode(SC_BAD_REQUEST)
                .body("nome", equalTo("nome deve ser uma string"))
                .body("email", equalTo("email deve ser uma string"))
                .body("password", equalTo("password deve ser uma string"))
                .body("administrador", equalTo("administrador deve ser 'true' ou 'false'"));
    }
}