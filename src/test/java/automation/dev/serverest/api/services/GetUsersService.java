package automation.dev.serverest.api.services;
import automation.dev.serverest.api.base.BaseTest;
import io.restassured.response.Response;
import org.junit.Test;

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

    public Response getRegisteredUsers() {
        try {
            return request.get(USERS);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get users", e);
        }
    }

    public Response getUserById(String idUser) {
        try {
            return request.get(USERS.concat(idUser));
        } catch (Exception e) {
            throw new RuntimeException("Failed to get users", e);
        }
    }
}