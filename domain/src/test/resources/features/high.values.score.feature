Feature: Deuce and Advantage are Scored Correctly
  Scenario:
    Given the score is 40:40
    When the receiver wins a point
    Then current score should be 40:A
  Scenario:
    Given the score is A:40
    When the receiver wins a point
    Then current score should be 40:40
