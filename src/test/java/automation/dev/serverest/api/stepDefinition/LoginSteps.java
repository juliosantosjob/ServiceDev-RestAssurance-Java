package automation.dev.serverest.api.stepDefinition;

import automation.dev.serverest.api.entities.LoginEntities;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

import static automation.dev.serverest.api.requests.LoginService.login;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;

public class LoginSteps {
    LoginEntities payload;
    Response response;

    @Given("a POST request with valid credentials to the login endpoint")
    public void a_post_request_with_valid_credentials_to_the_login_endpoint() {
        payload = new LoginEntities("exemplo@example.com", "senha123");
        response = login(payload);
    }

    @Then("the API should return status code {int} OK")
    public void the_api_should_return_status_code_ok(Integer expectedStatusCode) {
        assertEquals(expectedStatusCode.intValue(), response.getStatusCode());
    }

    @And("the response should contain a valid authentication token")
    public void the_response_should_contain_a_valid_authentication_token() {
        String authorizationHeader = response.jsonPath().getString("authorization");
        String messageHeader = response.jsonPath().getString("message");

        assertThat(authorizationHeader, notNullValue());
        assertEquals("Login realizado com sucesso", messageHeader);
    }
}
