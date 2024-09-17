package automation.dev.serverest.api.requests;

import automation.dev.serverest.api.base.BaseTest;
import io.restassured.response.Response;

import static automation.dev.serverest.api.base.Routes.LOGIN;
import static automation.dev.serverest.api.base.Routes.USERS;

public class LoginService extends BaseTest {

    /**
     * Service to handle user login requests.
     *
     * @param credentials The user's login credentials.
     * @return The HTTP response from the login request.
     */

    public Response login(Object credentials) {
        try {
            return request
                    .body(credentials)
                    .when()
                    .post(LOGIN);
        } catch (Exception e) {
            throw new RuntimeException("Failed to register user", e);
        }
    }
}
