package automation.dev.serverest.api.requests;

import automation.dev.serverest.api.support.BaseTest;
import io.restassured.response.Response;

public class GetUsersRequest extends BaseTest {

    /**
     * Método para obter todos os usuários
     *
     * @return Response da chamada
     */

    public static Response getUser() {
        try {
            return requester.get(USERS);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get users: " + e.getMessage());
        }
    }

    /**
     * Método para obter um usuário por ID
     *
     * @param idUser Id do usuário a ser obtido
     * @return Response da chamada
     */

    public static Response getUserById(String idUser) {
        try {
            String endpoint = String.format("%s%s", USERS, idUser);
            return requester.get(endpoint);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get user by ID: " + e.getMessage());
        }
    }
}
