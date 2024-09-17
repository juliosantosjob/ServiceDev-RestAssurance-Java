#language:en

  @regression
  Feature: Login functionality

    Scenario: Successful login
      Given I send a POST request with valid credentials to the login endpoint
      Then the API should return status code 200 OK
      And the response should contain a valid authentication token
      And the response message should be "Login realizado com sucesso"

    Scenario: Login with invalid email
      Given I send a POST request with an invalid email body to the login endpoint
      Then the API should return status code 400 Bad Request
      And the response message should be "email deve ser um email válido"