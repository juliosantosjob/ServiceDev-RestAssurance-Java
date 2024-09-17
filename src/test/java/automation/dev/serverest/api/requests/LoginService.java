    package automation.dev.serverest.api.requests;

    import automation.dev.serverest.api.base.BaseTest;
    import io.restassured.RestAssured;
    import io.restassured.response.Response;

    public class LoginService extends BaseTest {

      /**
       * Sends a login request to the server.
       *
       * @param credentials The login credentials to authenticate the user.
       * @return Response containing the server's response after the login attempt.
       */

        public static Response login(Object credentials) {
            try {
                return RestAssured.given()
                        .body(credentials)
                        .when()
                        .post(LOGIN);
            } catch (Exception e) {
                throw new RuntimeException("Error on login: " + e.getMessage());
            }
        }
    }
