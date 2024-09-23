package automation.dev.serverest.api.services;

import automation.dev.serverest.api.base.BaseTest;
import io.restassured.response.Response;

public class GetUsersService extends BaseTest {

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
