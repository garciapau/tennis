Feature: Winning Points are Scored Correctly
  Scenario:
    Given the score is 40:30
    When the server wins a point
    Then the server should win
  Scenario:
    Given the score is 40:A
    When the receiver wins a point
    Then the receiver should win
