package automation.dev.serverest.api.testCases;

import automation.dev.serverest.api.support.BaseTest;
import automation.dev.serverest.api.models.LoginModel;

import automation.dev.serverest.api.models.NewUsersModel;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.*;

import static automation.dev.serverest.api.requests.DeleteUsersRequest.deleteUser;
import static automation.dev.serverest.api.requests.LoginRequest.loginUser;
import static automation.dev.serverest.api.requests.RegisterUsersRequest.registerUser;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;

@Tag("regression")
@Tag("loginRegression")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginTest extends BaseTest {
    private NewUsersModel newUsers;
    private LoginModel credential;
    private final Faker faker = new Faker();
    private String id_;

    @BeforeEach
    public void initsetup() {
        newUsers = new NewUsersModel(
                faker.name().firstName(),
                faker.internet().emailAddress(),
                faker.internet().password(),
                Boolean.toString(true)
        );
        credential = new LoginModel(
                newUsers.getEmail(),
                newUsers.getPassword()
        );
        id_ = registerUser(newUsers)
                .then()
                .extract()
                .path("_id")
                .toString();
    }

    @AfterAll
    public void endsetup() {
        deleteUser(id_).then()
                .statusCode(SC_OK);
    }

    @Test
    @Order(1)
    @Tag("loginSuccess")
    @DisplayName("Cenario 01: Deve realizar login com sucesso")
    public void loginSuccessful() {
        loginUser(credential)
                .then()
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
        loginUser(credential)
                .then()
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
        loginUser(credential)
                .then()
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
        loginUser(credential)
                .then()
                .statusCode(SC_UNAUTHORIZED)
                .body("message", equalTo("Email e/ou senha inválidos"));
    }

    @Test
    @Order(5)
    @Tag("loginEmptyPassword")
    @DisplayName("Cenario 05: Não deve realizar login com senha vazia")
    public void loginWithEmptyPassword() {
        credential.setPassword("");
        loginUser(credential)
                .then()
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
        loginUser(credential)
                .then()
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
        loginUser(credential)
                .then()
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
        loginUser(credential)
                .then()
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
        loginUser(credential)
                .then()
                .statusCode(SC_BAD_REQUEST)
                .body(is(notNullValue()))
                .body("password", equalTo("password deve ser uma string"));
    }
}
