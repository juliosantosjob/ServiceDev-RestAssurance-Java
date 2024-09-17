package automation.dev.serverest.api.base;

import io.cucumber.java.Before;
import io.restassured.RestAssured;

public class BaseTest {
    // Configs
    protected static String CONTENT_TYPE = "application/json";
    protected static Long MAX_TIMEOUT = 3000L;

    // Routes
    protected static String  APP_BASE_URL =  "https://serverest.dev";
    protected static String USERS = "/usuarios/";
    protected static String LOGIN = "/login/";

    @Before
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}