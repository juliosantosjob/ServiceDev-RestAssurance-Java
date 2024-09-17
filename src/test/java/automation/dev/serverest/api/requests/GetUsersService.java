package automation.dev.serverest.api.requests;
import automation.dev.serverest.api.base.BaseTest;
import io.restassured.response.Response;

import static automation.dev.serverest.api.base.Routes.USERS;

/**
 * @author Julio C. Santos
 */

public class GetUsersService extends BaseTest {

    /**
     * Fetches the list of registered users from the server.
     *
     * @return Response containing the server's response with the list of users.
     */

    public Response getUser() {
        try {
            return request.get(USERS);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get users", e);
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
            return request.get(USERS.concat(idUser));
        } catch (Exception e) {
            throw new RuntimeException("Failed to get users", e);
        }
    }
}