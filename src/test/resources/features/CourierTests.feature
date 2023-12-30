Feature: Test the 'GET' Method from '/courier'

  Scenario: Validate perform a /GET with deliveries registered, returning a list of deliveries found.
    When I get all deliveries to Courier "1"
    Then I validate that the code status is 200

  Scenario: Validate perform a /POST to Courier endpoint, returning a 'Method Not Allowed' error.
    When I send a post call to courier with id "1"
    Then I validate that the code status is 405
    And I validate that the response contains the error message: "Method Not Allowed"

#  Scenario: Validate perform a /GET to Courier statement
#    When I get statements to courier with id "1", start date "2023-12-27" and end date "2023-12-28"
#    Then I validate that the code status is 200

