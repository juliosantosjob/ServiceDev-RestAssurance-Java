package automation.dev.serverest.api.requests;

import automation.dev.serverest.api.base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;

/**
 * @author Julio C. Santos
 */

public class DeleteUsersService extends BaseTest {

    /**
     * Deletes a user with the given ID.
     *
     * @param idUser The ID of the user to be deleted.
     * @return The response from the DELETE request.
     */

    public static Response deleteUser(String idUser) {
        try {
            return RestAssured.given()
                    .baseUri(APP_BASE_URL)
                    .header("Content-Type", CONTENT_TYPE)
                    .when()
                    .delete(USERS.concat(idUser));
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete user" + e.getMessage());
        }
    }
}