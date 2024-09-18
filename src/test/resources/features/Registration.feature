#language:en

  @regression
  Feature: Registration functionality

    Scenario: Successful login
      Given i send a POST request to the user registration API
      Then the system should return the status code 201 Created
      And the user should be registered successfully