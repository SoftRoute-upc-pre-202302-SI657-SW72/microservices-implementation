Feature: Update Package Status
  Scenario: Update the status of an existing package
    Given I have an existing package with id 1 and status "travelling"
    When I update the package status to "delivered"
    Then the returned package should have the status "delivered"