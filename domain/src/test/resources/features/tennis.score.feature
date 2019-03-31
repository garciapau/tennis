Feature: Winning a Point Increases Score Correctly
  Scenario:
    Given the score is 0:0
    When the server wins a point
    Then current score is 15:0
  Scenario:
    Given the score is 15:15
    When the receiver wins a point
    Then the score is 15:30