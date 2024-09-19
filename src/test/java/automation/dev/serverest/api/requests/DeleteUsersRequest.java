package automation.dev.serverest.api.requests;

import automation.dev.serverest.api.support.BaseTest;
import io.restassured.response.Response;

public class DeleteUsersRequest extends BaseTest {

    /**
     * Metodo para deletar usuarios
     *
     * @param idUser Id da conta que será deletada
     * @return Response da chamada
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