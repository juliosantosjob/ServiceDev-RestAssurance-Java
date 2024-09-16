package automation.dev.serverest.api.services;
import automation.dev.serverest.api.base.BaseTest;
import io.restassured.response.Response;

/**
 * @author Julio C. Santos
 */

public class UsersService extends BaseTest {

    /**
     * Fetches the list of registered users from the server.
     *
     * @return Response containing the server's response with the list of users.
     */

    public Response getRegisteredUsers() {
        try {
            return request.get("/usuarios");
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve users", e);
        }
    }
}