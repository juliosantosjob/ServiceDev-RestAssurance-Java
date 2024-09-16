package automation.dev.serverest.api.services;

import automation.dev.serverest.api.base.BaseTest;
import io.restassured.response.Response;

import static automation.dev.serverest.api.base.Routes.USERS;

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

    public Response registerUser(Object newUser) {
        try {
            return request
                    .header("Content-Type", "application/json")
                    .body(newUser)
                    .when()
                    .post(USERS);
        } catch (Exception e) {
            throw new RuntimeException("Failed to register user", e);
        }
    }
}