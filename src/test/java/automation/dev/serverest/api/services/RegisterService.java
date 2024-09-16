package automation.dev.serverest.api.services;

import automation.dev.serverest.api.base.BaseTest;
import automation.dev.serverest.api.entities.NewUsersEntities;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

public class RegisterService extends BaseTest {

    public Response registerUser(Object newUser) {
        try {
            return request
                        .contentType(ContentType.JSON)
                        .body(newUser)
                    .when()
                        .post("/usuarios");

        } catch (Exception e) {
            throw new RuntimeException("Failed to register user", e);
        }
    }

    @Test
    public void vamosTestar() {
        NewUsersEntities newUser = new NewUsersEntities(
                "João",
                "test1111223@gmail.com",
                "ttest12",
                "false"
        );

        Response response = registerUser(newUser);
        response.then()
                .statusCode(201);
    }
}