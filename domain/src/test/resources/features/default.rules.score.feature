Feature: Default rules loading
  Scenario:
    Given that I don’t specify any specific ruleset
    When I use the library
    Then it applies the default rules specified in Feature 1
