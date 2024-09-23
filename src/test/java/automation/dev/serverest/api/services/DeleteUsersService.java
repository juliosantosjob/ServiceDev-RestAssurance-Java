package automation.dev.serverest.api.services;

import automation.dev.serverest.api.base.BaseTest;
import io.restassured.response.Response;

public class DeleteUsersService extends BaseTest {

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