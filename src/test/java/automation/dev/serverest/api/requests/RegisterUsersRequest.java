package automation.dev.serverest.api.requests;

import automation.dev.serverest.api.base.BaseTest;
import io.restassured.response.Response;

/**
 * @author Julio C. Santos
 */

public class RegisterUsersRequest extends BaseTest {

    /**
     * Registers a new user.
     *
     * @param newUser The new user object in JSON format.
     * @return The HTTP response.
     */

    public static Response registerUser(Object newUser) {
        try {
            return requester
                    .body(newUser)
                    .when()
                    .post(USERS);
        } catch (Exception e) {
            throw new RuntimeException("Failed to register user" + e.getMessage());
        }
    }
}