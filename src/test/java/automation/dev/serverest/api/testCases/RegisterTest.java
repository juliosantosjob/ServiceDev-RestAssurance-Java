package automation.dev.serverest.api.testCases;

import automation.dev.serverest.api.support.BaseTest;
import automation.dev.serverest.api.models.NewUsersModel;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.*;

import static automation.dev.serverest.api.requests.DeleteUsersRequest.deleteUser;
import static automation.dev.serverest.api.requests.RegisterUsersRequest.registerUser;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.*;

@Tag("regression")
@Tag("registerRegression")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RegisterTest extends BaseTest {
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
    @Tag("registerSuccess")
    @DisplayName("Cenario 01: Deve realizar cadastro com sucesso ")
    public void registrationSuccessful() {
        userId = registerUser(newUsers)
                .then()
                .statusCode(SC_CREATED)
                .body(is(notNullValue()))
                .body("message", equalTo("Cadastro realizado com sucesso"))
                .body("_id", notNullValue())
                .extract()
                .path("_id")
                .toString();
    }

    @Test
    @Order(2)
    @Tag("registerFailure")
    @DisplayName("Cenario 02: Deve falhar ao realizar cadastro com e-mail inválido")
    public void registrationWithInvalidEmail() {
        newUsers.setEmail(faker.internet().emailAddress()
                .replace("@", ""));
        registerUser(newUsers)
                .then()
                .statusCode(SC_BAD_REQUEST)
                .body(is(notNullValue()))
                .body("email", equalTo("email deve ser um email válido"));
    }

    @Test
    @Order(3)
    @Tag("registerFailure")
    @DisplayName("Cenario 03: Deve falhar ao realizar cadastro com nome em branco")
    public void registrationWithEmptyName() {
        newUsers.setNome("");
        registerUser(newUsers)
                .then()
                .statusCode(SC_BAD_REQUEST)
                .body(is(notNullValue()))
                .body("nome", equalTo("nome não pode ficar em branco"));
    }

    @Test
    @Order(4)
    @Tag("registerFailure")
    @DisplayName("Cenario 04: Deve falhar ao realizar cadastro com email em branco")
    public void registrationWithEmptyEmail() {
        newUsers.setEmail("");
        registerUser(newUsers)
                .then()
                .statusCode(SC_BAD_REQUEST)
                .body(is(notNullValue()))
                .body("email", equalTo("email não pode ficar em branco"));
    }

    @Test
    @Order(5)
    @Tag("registerFailure")
    @DisplayName("Cenario 05: Deve falhar ao realizar cadastro com senha em branco")
    public void registrationWithEmptyPassword() {
        newUsers.setPassword("");
        registerUser(newUsers)
                .then()
                .statusCode(SC_BAD_REQUEST)
                .body(is(notNullValue()))
                .body("password", equalTo("password não pode ficar em branco"));
    }
}