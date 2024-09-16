package automation.dev.serverest.api.services;

import automation.dev.serverest.api.base.BaseTest;
import io.restassured.response.Response;

import static automation.dev.serverest.api.base.Routes.USERS;

/**
 * @author Julio C. Santos
 */

public class RemoveUsersService extends BaseTest {

    /**
     * Deletes a user with the given ID.
     *
     * @param idUser The ID of the user to be deleted.
     * @return The response from the DELETE request.
     */

    public Response deleteUser(String idUser) {
        try {
            return request
                    .header("Content-Type", "application/json")
                    .when()
                    .delete(USERS.concat(idUser));
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete user", e);
        }
    }
}