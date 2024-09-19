package automation.dev.serverest.api.requests;

import automation.dev.serverest.api.config.BaseTest;
import io.restassured.response.Response;

public class GetUsersRequest extends BaseTest {

    public static Response getUser() {
        try {
            return requester.get(USERS);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get users: " + e.getMessage());
        }
    }

    public Response getUserById(String idUser) {
        try {
            String endpoint = String.format("%s%s", USERS, idUser);
            return requester.get(endpoint);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get user by ID: " + e.getMessage());
        }
    }
}
