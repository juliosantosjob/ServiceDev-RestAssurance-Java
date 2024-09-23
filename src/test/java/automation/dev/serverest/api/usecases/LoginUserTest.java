package automation.dev.serverest.api.usecases;

import automation.dev.serverest.api.base.BaseTest;
import automation.dev.serverest.api.models.LoginModel;
import automation.dev.serverest.api.models.NewUsersModel;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static automation.dev.serverest.api.services.LoginUserService.loginUser;
import static automation.dev.serverest.api.utils.Helpers.*;
import static automation.dev.serverest.api.utils.Reports.attachmentsAllure;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.*;

@Tag("regression")
@Tag("loginUserRegression")
@DisplayName("Feature: Teste de Login de Usuário")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginUserTest extends BaseTest {
    private NewUsersModel dynamicUser_;
    private LoginModel credentials;
    private Response response;
    private String id_;

    @BeforeEach
    public void initsetup() {
        dynamicUser_ = getRandomUser();
        credentials = new LoginModel(
                dynamicUser_.getEmail(),
                dynamicUser_.getPassword()
        );
        id_ = createAndGetRandomUserId(dynamicUser_);
    }

    @AfterEach
    public void endsetup() {
        deleteUserById(id_);
        attachmentsAllure(response);
    }

    @Test
    @Order(1)
    @Tag("loginSuccess")
    @DisplayName("Cenario 01: Deve realizar login com sucesso")
    public void loginSuccessful() {
        response = loginUser(credentials);
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
        credentials.setEmail("invalid_email");
        response = loginUser(credentials);
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
        credentials.setEmail("");
        response = loginUser(credentials);
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
        credentials.setPassword("invalid_password");
        response = loginUser(credentials);
        response.then()
                .statusCode(SC_UNAUTHORIZED)
                .body("message", equalTo("Email e/ou senha inválidos"));
    }

    @Test
    @Order(5)
    @Tag("loginEmptyPassword")
    @DisplayName("Cenario 05: Não deve realizar login com senha vazia")
    public void loginWithEmptyPassword() {
        credentials.setPassword("");
        response = loginUser(credentials);
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
        credentials.setEmail("");
        credentials.setPassword("");
        response = loginUser(credentials);
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
        credentials.setEmail(" name@example.com ");
        credentials.setPassword(" senha123 ");
        response = loginUser(credentials);
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
        credentials.setEmail(null);
        response = loginUser(credentials);
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
        credentials.setPassword(null);
        response = loginUser(credentials);
        response.then()
                .statusCode(SC_BAD_REQUEST)
                .body(is(notNullValue()))
                .body("password", equalTo("password deve ser uma string"));
    }
}