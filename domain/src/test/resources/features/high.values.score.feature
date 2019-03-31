Feature: Deuce and Advantage are Scored Correctly
  Scenario:
    Given the score is 40:40
    When the receiver wins a point
    Then current score is 40:A