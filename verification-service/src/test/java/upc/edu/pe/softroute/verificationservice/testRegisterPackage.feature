Feature: Package Registration
  Scenario: Register a new package
    Given I have a new package with id 1 and status "travelling"
    When I register the new package
    Then the response status code should be 201
    And the response body should contain the registered package
