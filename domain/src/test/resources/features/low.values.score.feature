Feature: Winning a Point Increases Score Correctly
  Scenario:
    Given the score is 0:0
    When the server wins 1 points
    Then current score should be 15:0
  Scenario:
    Given the score is 15:15
    When the receiver wins 1 points
    Then current score should be 15:30
  Scenario:
    Given the score is 30:30
    When the server wins 1 points
    Then current score should be 40:30
  Scenario:
    Given the score is 0:0
    When the server wins 3 points
    Then current score should be 40:0
