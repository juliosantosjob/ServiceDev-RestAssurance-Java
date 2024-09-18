package automation.dev.serverest.api.requests;

import automation.dev.serverest.api.base.BaseTest;
import io.restassured.response.Response;

/**
 * @author Julio C. Santos
 */

public class DeleteUsersRequest extends BaseTest {

    /**
     * Deletes a user with the given ID.
     *
     * @param idUser The ID of the user to be deleted.
     * @return The response from the DELETE request.
     */

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