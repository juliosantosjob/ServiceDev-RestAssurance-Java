package automation.dev.serverest.api.stepDefinitions;

import automation.dev.serverest.api.models.LoginModel;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static automation.dev.serverest.api.requests.LoginRequest.loginUser;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;

public class LoginSteps {
    LoginModel credentials;
    Response response;

    @Given("I send a POST request with valid credentials to the login endpoint")
    public void iSendAPostRequestWithValidCredentialsToTheLoginEndpoint() {
        credentials = new LoginModel("exemplo@example.com", "senha123");
        response = loginUser(credentials);
    }

    @Given("I send a POST request with an invalid email body to the login endpoint")
    public void iSendAPostRequestWithAnInvalidEmailBodyToTheLoginEndpoint() {
        credentials = new LoginModel("example.com", "senha123");
        response = loginUser(credentials);
    }

    @And("the API should return status code {int} OK")
    public void theApiShouldReturnStatusCodeOK(Integer expectedStatusCode) {
        assertEquals(expectedStatusCode.intValue(), SC_OK);
    }

    @And("the API should return status code {int} Bad Request")
    public void theApiShouldReturnStatusCodeBadRequest(Integer expectedStatusCode) {
        assertEquals(expectedStatusCode.intValue(), SC_BAD_REQUEST);
    }

    @Then("the response should contain a valid authentication token")
    public void theResponseShouldContainAValidAuthenticationToken() {
        String authorizationToken = response.jsonPath().getString("authorization");
        assertThat(authorizationToken, notNullValue());
    }

    @Then("the response message should be {string}")
    public void theResponseMessageShouldBe(String expectedMessage) {
        JsonPath jsonPath = new JsonPath(response.asString());
        String actualMessage = jsonPath.getString("message");

        assertEquals(expectedMessage, actualMessage);
    }

    @Then("the response message invalid email should be {string}")
    public void the_response_message_invalid_email_should_be(String expectedMessage) {
        JsonPath jsonPath = new JsonPath(response.asString());
        String actualMessage = jsonPath.getString("email");

        assertEquals(expectedMessage, actualMessage);
    }
}