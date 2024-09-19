package automation.dev.serverest.api.requests;

import automation.dev.serverest.api.config.BaseTest;
import io.restassured.response.Response;

public class DeleteUsersRequest extends BaseTest {

    public static Response deleteUser(String idUser) {
        try {
            return requester
                    .when()
                    .delete(USERS.concat(idUser));
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete user" + e.getMessage());
        }
    }
}