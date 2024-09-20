package automation.dev.serverest.api.requests;

import automation.dev.serverest.api.support.BaseTest;
import automation.dev.serverest.api.models.LoginModel;
import io.restassured.response.Response;

public class LoginRequest extends BaseTest {

    /**
     * Método para realizar login de um usuário
     *
     * @param credentials Objeto contendo as credenciais de login (email e senha)
     * @return Response da chamada
     */

    public static Response loginUser(LoginModel credentials) {
        try {
            return requester
                    .body(credentials)
                    .when()
                    .post(LOGIN);
        } catch (Exception e) {
            throw new RuntimeException("Error logging in: " + e.getMessage());
        }
    }
}
