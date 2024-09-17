package automation.dev.serverest.api.requests;

import automation.dev.serverest.api.base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;

/**
 * @author Julio C. Santos
 */

public class RegisterUsersService extends BaseTest {

    /**
     * Registers a new user.
     *
     * @param newUser The new user object in JSON format.
     * @return The HTTP response.
     */

    public static Response registerUser(Object newUser) {
        try {
            return RestAssured.given()
                    .baseUri(APP_BASE_URL)
                    .header("Content-Type", CONTENT_TYPE)
                    .body(newUser)
                    .when()
                    .post(USERS);
        } catch (Exception e) {
            throw new RuntimeException("Failed to register user" + e.getMessage());
        }
    }
}