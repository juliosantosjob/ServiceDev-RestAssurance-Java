package automation.dev.serverest.api.requests;

import automation.dev.serverest.api.config.BaseTest;
import automation.dev.serverest.api.models.NewUsersModel;
import io.restassured.response.Response;

public class RegisterUsersRequest extends BaseTest {

    public static Response registerUser(NewUsersModel newUser) {
        try {
            return requester
                    .body(newUser)
                    .when()
                    .post(USERS);
        } catch (Exception e) {
            throw new RuntimeException("Failed to register user: " + e.getMessage());
        }
    }
}
