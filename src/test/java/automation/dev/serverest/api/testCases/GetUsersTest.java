package automation.dev.serverest.api.testCases;

import automation.dev.serverest.api.support.BaseTest;
import automation.dev.serverest.api.models.NewUsersModel;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.*;

import static automation.dev.serverest.api.requests.GetUsersRequest.getUser;
import static automation.dev.serverest.api.requests.GetUsersRequest.getUserById;
import static automation.dev.serverest.api.requests.RegisterUsersRequest.registerUser;
import static automation.dev.serverest.api.requests.DeleteUsersRequest.deleteUser;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.*;

@Tag("regression")
@Tag("getUserRegression")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GetUsersTest extends BaseTest {
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
    @Tag("getUserSuccess")
    @DisplayName("Cenario 01: Deve obter o primeiro usuário com sucesso")
    public void getFirstUserSuccessful() {

        String resposta = getUser().then().extract().asString();
        System.out.println(resposta);
    }

    @Test
    @Order(2)
    @Tag("getUserSuccess")
    @DisplayName("Cenario 02: Deve obter um usuário específico com sucesso")
    public void getUserByIdSuccessful() {
        getUserById(userId)
                .then()
                .statusCode(SC_OK)
                .body("nome", equalTo(newUsers.getNome()))
                .body("email", equalTo(newUsers.getEmail()))
                .body("password", equalTo(newUsers.getPassword()))
                .body("administrador", equalTo(newUsers.getAdministrador()));
    }

    @Test
    @Order(3)
    @Tag("getUserFailure")
    @DisplayName("Cenario 03: Deve falhar ao tentar obter um usuário inexistente")
    public void getNonExistentUser() {
        getUserById("non-existent-id")
                .then()
                .statusCode(SC_NOT_FOUND)
                .body("message", equalTo("Usuário não encontrado"));
    }
}
