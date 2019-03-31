package com.acme.game.tennis.domain;

import com.acme.game.tennis.domain.core.ClassicGame;
import com.acme.game.tennis.domain.core.ClassicScoreBoard;
import com.acme.game.tennis.domain.model.Player;
import cucumber.api.java8.En;
import cucumber.runtime.java.StepDefAnnotation;
import org.junit.Assert;

import java.util.Optional;

@StepDefAnnotation
public class TennisScoreStepdefs implements En {
    private Game game;

    public TennisScoreStepdefs() {
        Given("^the score is (.*):(.*)$", (String server, String receiver) -> {
            ScoreBoard scoreBoard = ClassicScoreBoard.Builder.newInstance().server(server).receiver(receiver).build();
            game = ClassicGame.Builder.newInstance().score(scoreBoard).build();
        });
        When("^the server wins a point$", () -> {
            game.serverWinsPoint();
        });
        When("^the receiver wins a point$", () -> {
            game.receiverWinsPoint();
        });
        Then("^current score is (.*)$", (String expectedScore) -> {
            String currentScore = game.getCurrentScore();
            Assert.assertEquals(currentScore, expectedScore);
        });
        Then("^the server should win$", () -> {
            Optional<Player> winner = game.getWinner();
            Assert.assertTrue(winner.isPresent());
            Assert.assertEquals("server", winner.map(Player::getName).orElse("None"));
        });

    }
}
