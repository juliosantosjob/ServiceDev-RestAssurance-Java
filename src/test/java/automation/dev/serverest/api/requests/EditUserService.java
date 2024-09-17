package automation.dev.serverest.api.requests;

import automation.dev.serverest.api.base.BaseTest;
import io.restassured.response.Response;

import static automation.dev.serverest.api.base.Routes.USERS;

public class EditUserService extends BaseTest {

    /**
     * Edits a user with the provided new data.
     *
     * @param newUser New user data.
     * @return Response from the request.
     * @throws RuntimeException If an error occurs while editing the user.
     */

    public Response editUser(Object newUser, String idUser) {
        try {
            return request
                    .header("Content-Type", "application/json")
                    .body(newUser)
                    .when()
                    .put(USERS.concat(idUser));
        } catch (Exception e) {
            throw new RuntimeException("Failed to register user", e);
        }
    }
}