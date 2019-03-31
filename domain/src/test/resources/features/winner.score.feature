Feature: Winning Points are Scored Correctly
  Scenario:
    Given the score is 40:30
    When the server wins a point
    Then the server should win
  Scenario:
    Given the score is 40:A
    When the receiver wins a point
    Then the receiver should win
  Scenario:
    Given the score is 15:15
    When the receiver wins a point
    Then nobody has won yet
  Scenario:
    Given the score is 0:0
    And the server wins a point
    And the server wins a point
    And the server wins a point
    When the server wins a point
    Then the server should win
