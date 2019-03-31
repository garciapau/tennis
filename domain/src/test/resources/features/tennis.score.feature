Feature: Winning a Point Increases Score Correctly
  Scenario:
    Given the score is 0:0
    When the server wins a point
    Then current score is 15:0