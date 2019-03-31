package com.acme.game.tennis.domain;

import cucumber.api.java8.En;
import cucumber.runtime.java.StepDefAnnotation;
import org.junit.Assert;

@StepDefAnnotation
public class TennisScoreStepdefs implements En {
    public TennisScoreStepdefs() {
        Given("^the score is (\\d+):(\\d+)$", (Integer arg0, Integer arg1) -> {
        });
        When("^the server wins a point$", () -> {
        });
        Then("^current score is (\\d+):(\\d+)$", (Integer arg0, Integer arg1) -> {
            Assert.assertTrue(false);
        });
    }
}
