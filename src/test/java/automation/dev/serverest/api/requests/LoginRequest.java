package automation.dev.serverest.api.requests;

import automation.dev.serverest.api.base.BaseTest;
import io.restassured.response.Response;

/**
 * @author Julio C. Santos
 */

public class LoginRequest extends BaseTest {

    /**
     * Sends a login request to the server.
     *
     * @param credentials The login credentials to authenticate the user.
     * @return Response containing the server's response after the login attempt.
     */

    public static Response login(Object credentials) {
        try {
            return requester
                    .body(credentials)
                    .when()
                    .post(LOGIN);
        } catch (Exception e) {
            throw new RuntimeException("Error on login: " + e.getMessage());
        }
    }
}
