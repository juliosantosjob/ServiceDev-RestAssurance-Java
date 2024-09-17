    package automation.dev.serverest.api.requests;

    import automation.dev.serverest.api.base.BaseTest;
    import io.restassured.RestAssured;
    import io.restassured.response.Response;

    public class LoginService extends BaseTest {

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
