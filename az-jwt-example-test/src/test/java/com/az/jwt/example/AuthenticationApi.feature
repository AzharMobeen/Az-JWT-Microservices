Feature: user authentication to get JWT

  Background:
    * url baseUrl

  Scenario:TC_01 Success scenario where we should receive jwt.

    Given path '/authenticate'
    And request { "userName": "user", "password": "password"}
    When method POST
    And print 'Response: ', response
    And match response != ""
    Then status 200

  Scenario:TC_02 Failed scenario where we are passing wrong username.

    Given path '/authenticate'
    And request { "userName": "user1", "password": "password"}
    When method POST
    And print 'Response: ', response
    And match response.message == "Bad credentials"
    And match response.detail == "Provided username/password is invalid"
    Then status 401

