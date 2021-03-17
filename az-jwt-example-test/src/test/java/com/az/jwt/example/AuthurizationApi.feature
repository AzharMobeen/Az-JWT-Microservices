Feature: JWT verification

  Background:
    * url baseUrl

  Scenario:TC_01 Success scenario where response status 200.
# First we will get JWT
    Given path '/authenticate'
    And request { "userName": "user", "password": "password"}
    When method POST
    And print 'Response: ', response
# Now we need to pass jwt and get success response
    And print response.jwt
    Given header Authorization = 'Bearer '+response.jwt
    Given path '/hello'
    When method GET
    And print 'Response: ', response
    And match response == "JWT Authorization working fine"
    Then status 200

  Scenario:TC_02 Failed scenario where we are passing wrong JWT.
    Given header Authorization = 'Bearer '
    Given path '/hello'
    When method GET
    And print 'Response: ', response
    And match response.message == "Authorization value is invalid"
    And match response.detail == "Authorization value in header should be  followed by Bearer JWT"
    Then status 500

  Scenario:TC_03 Failed scenario where we are not passing Authorization in request header.
    Given path '/hello'
    When method GET
    And print 'Response: ', response
    And match response.message == "Authorization is missing"
    And match response.detail == "Authorization is required in header for this URI"
    Then status 500

  Scenario:TC_04 Failed scenario where we are passing expired JWT.
    # provided jwt value is expired one.
    Given header Authorization = 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjAyOTYzNTc2LCJpYXQiOjE2MDI5NjE3NzZ9.nT1esZCzegWPE0uWYZJhWNV1S7WIt5uN7kwlCm6sBGA'
    Given path '/hello'
    When method GET
    And print 'Response: ', response
    And match response.message == "Provided jwt is expired"
    Then status 504