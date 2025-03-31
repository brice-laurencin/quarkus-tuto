Feature: I can read time
  Scenario: I can read time
    Given current time is "2021-03-04T01:02:03Z"
    When I asked for current time
    Then I get "2021-03-04T01:02:03Z"
