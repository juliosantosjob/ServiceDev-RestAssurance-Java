package automation.dev.serverest.api.requests;

import automation.dev.serverest.api.config.BaseTest;
import automation.dev.serverest.api.models.LoginModel;
import io.restassured.response.Response;

public class LoginRequest extends BaseTest {

    public static Response loginUser(LoginModel credentials) {
        try {
            return requester
                    .body(credentials)
                    .when()
                    .post(LOGIN);
        } catch (Exception e) {
            throw new RuntimeException("Error logging in: " + e.getMessage());
        }
    }
}
