package automation.dev.serverest.api.usecases;

import automation.dev.serverest.api.base.BaseTest;
import automation.dev.serverest.api.models.LoginModel;
import automation.dev.serverest.api.models.NewUsersModel;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static automation.dev.serverest.api.services.LoginUserService.loginUser;
import static automation.dev.serverest.api.utils.Helpers.*;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.*;

@Tag("regression")
@DisplayName("Feature: Teste de Login de Usuário")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginTest extends BaseTest {
    private NewUsersModel newUsers;
    private LoginModel credential;
    private Response response;
    private String id_;

    @BeforeEach
    public void initsetup() {
        newUsers = getRandomUser();
        credential = new LoginModel(
                newUsers.getEmail(),
                newUsers.getPassword()
        );
        id_ = createRandomUser(newUsers)
                .extract()
                .path("_id")
                .toString();
    }

    @AfterEach
    public void endsetup() {
        deleteUserById(id_);
    }

    @Test
    @Order(1)
    @Tag("loginSuccess")
    @DisplayName("Cenario 01: Deve realizar login com sucesso")
    public void loginSuccessful() {
        response = loginUser(credential);
        response.then()
                .statusCode(SC_OK)
                .body(is(notNullValue()))
                .body("message", equalTo("Login realizado com sucesso"))
                .body("authorization", notNullValue())
                .body("authorization", startsWith("Bearer "));
    }

    @Test
    @Order(2)
    @Tag("loginInvalidEmail")
    @DisplayName("Cenario 02: Não deve realizar login com email invalido")
    public void loginWithInvalidEmail() {
        credential.setEmail("invalid_email");
        response = loginUser(credential);
        response.then()
                .statusCode(SC_BAD_REQUEST)
                .body(is(notNullValue()))
                .body("email", equalTo("email deve ser um email válido"));
    }

    @Test
    @Order(3)
    @Tag("loginEmptyEmail")
    @DisplayName("Cenario 03: Não deve realizar login com email vazio")
    public void loginWithEmptyEmail() {
        credential.setEmail("");
        response = loginUser(credential);
        response.then()
                .statusCode(SC_BAD_REQUEST)
                .body(is(notNullValue()))
                .body("email", equalTo("email não pode ficar em branco"));
    }

    @Test
    @Order(4)
    @Tag("loginInvalidPassword")
    @DisplayName("Cenario 04: Não deve realizar login com senha inválida")
    public void loginWithInvalidPassword() {
        credential.setPassword("invalid_password");
        response = loginUser(credential);
        response.then()
                .statusCode(SC_UNAUTHORIZED)
                .body("message", equalTo("Email e/ou senha inválidos"));
    }

    @Test
    @Order(5)
    @Tag("loginEmptyPassword")
    @DisplayName("Cenario 05: Não deve realizar login com senha vazia")
    public void loginWithEmptyPassword() {
        credential.setPassword("");
        response = loginUser(credential);
        response.then()
                .statusCode(SC_BAD_REQUEST)
                .body(is(notNullValue()))
                .body("password", equalTo("password não pode ficar em branco"));
    }

    @Test
    @Order(6)
    @Tag("loginEmptyCredentials")
    @DisplayName("Cenario 06: Não deve realizar login com email e senha vazios")
    public void loginWithEmptyCredentials() {
        credential.setEmail("");
        credential.setPassword("");
        response = loginUser(credential);
        response.then()
                .statusCode(SC_BAD_REQUEST)
                .body(is(notNullValue()))
                .body("email", equalTo("email não pode ficar em branco"))
                .body("password", equalTo("password não pode ficar em branco"));
    }

    @Test
    @Order(7)
    @Tag("loginSpacesInCredentials")
    @DisplayName("Cenario 07: Não deve realizar login com email e senha com espaços em branco")
    public void loginWithSpacesInCredentials() {
        credential.setEmail(" name@example.com ");
        credential.setPassword(" senha123 ");
        response = loginUser(credential);
        response.then()
                .statusCode(SC_BAD_REQUEST)
                .body(is(notNullValue()))
                .body("email", equalTo("email deve ser um email válido"));
    }

    @Test
    @Order(8)
    @Tag("loginNullEmail")
    @DisplayName("Cenario 08: Não deve realizar login com email nulo")
    public void loginWithNullEmail() {
        credential.setEmail(null);
        response = loginUser(credential);
        response.then()
                .statusCode(SC_BAD_REQUEST)
                .body(is(notNullValue()))
                .body("email", equalTo("email deve ser uma string"));
    }

    @Test
    @Order(9)
    @Tag("loginNullPassword")
    @DisplayName("Cenario 09: Não deve realizar login com senha nula")
    public void loginWithNullPassword() {
        credential.setPassword(null);
        response = loginUser(credential);
        response.then()
                .statusCode(SC_BAD_REQUEST)
                .body(is(notNullValue()))
                .body("password", equalTo("password deve ser uma string"));
    }
}