package automation.dev.serverest.api.requests;

import automation.dev.serverest.api.config.BaseTest;
import automation.dev.serverest.api.models.NewUsersModel;
import io.restassured.response.Response;

public class EditUserRequest extends BaseTest {

    public static Response editUser(NewUsersModel newUser, String idUser) {
        try {
            return requester
                    .body(newUser)
                    .when()
                    .put(USERS.concat(idUser));
        } catch (Exception e) {
            throw new RuntimeException("Failed to register user" + e.getMessage());
        }
    }
}