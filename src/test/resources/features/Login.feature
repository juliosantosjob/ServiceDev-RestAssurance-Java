#language:en

  Feature: Login

    @regression
    Scenario: Successful login
      Given a POST request with valid credentials to the login endpoint
      Then the API should return status code 200 OK
      And the response should contain a valid authentication token