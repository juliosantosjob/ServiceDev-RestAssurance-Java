    package automation.dev.serverest.api.services;

    import automation.dev.serverest.api.base.BaseTest;
    import automation.dev.serverest.api.models.NewUsersModel;
    import io.restassured.response.Response;

    public class EditUserService extends BaseTest {

        /**
         * Metodo para editar usuarios
         *
         * @param newUser Objeto java com os dados do usuario
         * @param idUser Id da conta do usuario para realizar a edição
         * @return Response da chamada
         */

        public static Response editUser(NewUsersModel newUser, String idUser) {
            try {
                return requester
                        .body(newUser)
                        .when()
                        .put(USERS.concat(idUser));
            } catch (Exception e) {
                throw new RuntimeException("Failed to register user" + e.getMessage());
            }
        }
    }