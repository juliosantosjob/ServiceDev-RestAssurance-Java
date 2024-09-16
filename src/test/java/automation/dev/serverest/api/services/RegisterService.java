package automation.dev.serverest.api.services;

import automation.dev.serverest.api.base.BaseTest;
import io.restassured.response.Response;

/**
 * @author Julio C. Santos
 */

public class RegisterService extends BaseTest {

    /**
     * Registers a new user with the API.
     *
     * @param newUser The new user object in JSON format.
     *
     * @return The HTTP response.
     */

    public Response registerUser(Object newUser) {
        try {
            return request
                    .header("Content-Type", "application/json")
                    .body(newUser)
                    .when()
                    .post("/usuarios");
        } catch (Exception e) {
            throw new RuntimeException("Failed to register user", e);
        }
    }
}