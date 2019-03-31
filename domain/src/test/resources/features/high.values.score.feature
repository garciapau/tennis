Feature: Deuce and Advantage are Scored Correctly
  Scenario:
    Given the score is 40:40
    When the receiver wins 1 points
    Then current score should be 40:A
  Scenario:
    Given the score is A:40
    When the receiver wins 1 points
    Then current score should be 40:40
  Scenario:
    Given the score is 40:A
    When the server wins 1 points
    Then current score should be 40:40
