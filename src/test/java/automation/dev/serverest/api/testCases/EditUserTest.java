package automation.dev.serverest.api.testCases;

import automation.dev.serverest.api.support.BaseTest;
import automation.dev.serverest.api.models.NewUsersModel;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.*;

import static automation.dev.serverest.api.requests.EditUserRequest.editUser;
import static automation.dev.serverest.api.requests.RegisterUsersRequest.registerUser;
import static automation.dev.serverest.api.requests.DeleteUsersRequest.deleteUser;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.*;

@Tag("regression")
@Tag("editUserRegression")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EditUserTest extends BaseTest {
    private NewUsersModel newUsers;
    private final Faker faker = new Faker();
    private String userId;

    @BeforeEach
    public void initsetup() {
        newUsers = new NewUsersModel(
                faker.name().firstName(),
                faker.internet().emailAddress(),
                faker.internet().password(),
                Boolean.toString(true));

        userId = registerUser(newUsers)
                .then()
                .statusCode(SC_CREATED)
                .body(is(notNullValue()))
                .body("_id", notNullValue())
                .extract()
                .path("_id")
                .toString();
    }

    @AfterEach
    public void endsetup() {
        if (userId != null) {
            deleteUser(userId).then()
                    .statusCode(SC_OK);
        }
    }

    @Test
    @Order(1)
    @Tag("editSuccess")
    @DisplayName("Cenario 01: Deve realizar edição com sucesso")
    public void editUserSuccessful() {
        NewUsersModel updatedUser = new NewUsersModel(
                faker.name().firstName(),
                faker.internet().emailAddress(),
                faker.internet().password(),
                Boolean.toString(false));

        editUser(updatedUser, userId)
                .then()
                .statusCode(SC_OK)
                .body("message", equalTo("Registro alterado com sucesso"));
    }

    @Test
    @Order(2)
    @Tag("editFailure")
    @DisplayName("Cenario 02: Deve falhar ao realizar edição com todos os dados em branco")
    public void editUserWithInvalidData() {
        NewUsersModel invalidUser = new NewUsersModel("", "", "", "");
        editUser(invalidUser, userId)
                .then()
                .statusCode(SC_BAD_REQUEST)
                .body("nome", equalTo("nome não pode ficar em branco"));
    }

    @Test
    @Order(3)
    @Tag("editFailure")
    @DisplayName("Cenario 03: Deve criar um novo usuário ao tentar editar um usuário inexistente")
    public void editNonExistentUser() {
        NewUsersModel someUser = new NewUsersModel(
                faker.name().firstName(),
                faker.internet().emailAddress(),
                faker.internet().password(),
                Boolean.toString(true));

        editUser(someUser, "non-existent-id")
                .then()
                .statusCode(SC_CREATED)
                .body("message", equalTo("Cadastro realizado com sucesso"));
    }

    @Test
    @Order(4)
    @Tag("editFailure")
    @DisplayName("Cenario 04: Deve falhar ao realizar edição com campos nulos")
    public void editUserWithNullFields() {
        NewUsersModel nullFieldsUser = new NewUsersModel(null, null, null, null);
        editUser(nullFieldsUser, userId)
                .then()
                .statusCode(SC_BAD_REQUEST)
                .body("nome", equalTo("nome deve ser uma string"))
                .body("email", equalTo("email deve ser uma string"))
                .body("password", equalTo("password deve ser uma string"))
                .body("administrador", equalTo("administrador deve ser 'true' ou 'false'"));
    }
}