Feature: Winning Points are Scored Correctly
  Scenario:
    Given the score is 40:30
    When the server wins a point
    Then the server should win
