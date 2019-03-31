Feature: Winning Points are Scored Correctly
  Scenario:
    Given the score is 40:30
    When the server wins 1 points
    Then the server should win
  Scenario:
    Given the score is 40:A
    When the receiver wins 1 points
    Then the receiver should win
  Scenario:
    Given the score is 15:15
    When the receiver wins 1 points
    Then nobody has won yet
  Scenario:
    Given the score is 0:0
    When the server wins 4 points
    Then the server should win
  Scenario:
    Given the score is 0:0
    And the server wins 4 points
    When the server wins 1 points
    Then is unexpected scenario is just ignored
