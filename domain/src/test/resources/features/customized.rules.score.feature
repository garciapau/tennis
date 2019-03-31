Feature: Default rules loading
  Scenario:
    Given that I specify a set of rules to be used
    When I use the library
    Then it only applies the rules I have specified
  Scenario:
    Given that I specify a set of rules to be used
    When the server scores four points
    Then the server should win
