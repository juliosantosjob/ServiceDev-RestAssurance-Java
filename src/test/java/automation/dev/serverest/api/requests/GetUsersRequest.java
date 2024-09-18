package automation.dev.serverest.api.requests;

import automation.dev.serverest.api.base.BaseTest;
import io.restassured.response.Response;

/**
 * @author Julio C. Santos
 */

public class GetUsersRequest extends BaseTest {

    /**
     * Fetches the list of registered users from the server.
     *
     * @return Response containing the server's response with the list of users.
     */

    public static Response getUser() {
        try {
            return requester.get(USERS);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get users" + e.getMessage());
        }
    }

    /**
     * Fetches the details of a user by their ID from the server.
     *
     * @param idUser The ID of the user to retrieve.
     * @return Response containing the server's response with the user's details.
     */

    public Response getUserById(String idUser) {
        try {
            return requester.get(USERS.concat(idUser));
        } catch (Exception e) {
            throw new RuntimeException("Failed to get users", e);
        }
    }
}