Feature: Look for accomodation

  Scenario Outline: Look for accomodation with valid params
    Given The airbnb web site is accessible
    And  The cookies policy is closed
    When The search form is filled up with a location "<location>"
    And  The search form is filled up with a checkin "<checkin>" and a checkout "<checkout>" date
    And  The search form is filled up with a number of guests  "<adults>" ,  "<children>" , "<babies>"
    Then The result page is displayed
    Examples:
      | location | checkin | checkout | adults | children | babies |
      | California, Estados Unidos  | February 5, 2020 | February 22, 2020 | 4 | 2| 1|


