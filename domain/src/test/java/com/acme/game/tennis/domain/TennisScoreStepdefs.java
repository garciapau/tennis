package com.acme.game.tennis.domain;

import com.acme.game.tennis.domain.core.ClassicGame;
import com.acme.game.tennis.domain.model.Score;
import cucumber.api.java8.En;
import cucumber.runtime.java.StepDefAnnotation;
import org.junit.Assert;

@StepDefAnnotation
public class TennisScoreStepdefs implements En {
    private Game game;

    public TennisScoreStepdefs() {
        Given("^the score is (\\d+):(\\d+)$", (Integer server, Integer receiver) -> {
            Score score = Score.Builder.newInstance().server(server).receiver(receiver).build();
            game = ClassicGame.Builder.newInstance().score(score).build();
        });
        When("^the server wins a point$", () -> {
        });
        Then("^current score is (\\d+):(\\d+)$", (Integer server, Integer receiver) -> {
            Assert.assertEquals(game.currentScore().getServer(), server);
            Assert.assertEquals(game.currentScore().getReceiver(), receiver);
        });
    }
}
