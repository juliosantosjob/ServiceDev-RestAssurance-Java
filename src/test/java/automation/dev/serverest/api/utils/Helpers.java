package automation.dev.serverest.api.utils;

import automation.dev.serverest.api.base.BaseTest;
import automation.dev.serverest.api.models.NewUsersModel;
import com.github.javafaker.Faker;
import io.restassured.response.ValidatableResponse;

import static automation.dev.serverest.api.services.DeleteUsersService.deleteUser;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class Helpers extends BaseTest {
    private static final Faker faker = new Faker();

    public static NewUsersModel getRandomUser() {
        NewUsersModel newUser = new NewUsersModel();

        newUser.setNome(faker.name().firstName());
        newUser.setEmail(faker.internet().emailAddress());
        newUser.setPassword(faker.internet().password());
        newUser.setAdministrador(Boolean.toString(true));

        return newUser;
    }

    public static ValidatableResponse createRandomUser(NewUsersModel newUsers) {
        return requester
                .body(newUsers)
                .when()
                .post(USERS)
                .then()
                .statusCode(SC_CREATED)
                .body(is(notNullValue()))
                .body("_id", notNullValue());
    }

    public static void deleteUserById(String userId) {
        if (userId != null) {
            deleteUser(userId)
                    .then()
                    .statusCode(SC_OK);
        }
    }
}
