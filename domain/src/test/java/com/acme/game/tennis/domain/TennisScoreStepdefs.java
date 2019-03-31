package com.acme.game.tennis.domain;

import com.acme.game.tennis.domain.core.ClassicGame;
import com.acme.game.tennis.domain.core.ClassicScoreBoard;
import com.acme.game.tennis.domain.core.CustomDirectScoreBoard;
import com.acme.game.tennis.domain.core.CustomResetScoreBoard;
import com.acme.game.tennis.domain.model.Player;
import cucumber.api.java8.En;
import cucumber.runtime.java.StepDefAnnotation;
import org.junit.Assert;

import java.util.Optional;

@StepDefAnnotation
public class TennisScoreStepdefs implements En {
    private Game game;

    public TennisScoreStepdefs() {
        Given("^the score is (.*):(.*)$", (String serverScore, String receiverScore) -> {
            ScoreBoard classicScoreBoard = ClassicScoreBoard.Builder.newInstance().server(serverScore).receiver(receiverScore).build();
            game = ClassicGame.Builder.newInstance().score(classicScoreBoard).build();
        });
        When("^the server wins (\\d+) points$", (Integer numPoints) -> {
            for (int i = 0; i < numPoints; i++) { game.serverWinsPoint(); }
        });
        When("^the receiver wins (\\d+) points$", (Integer numPoints) -> {
            for (int i = 0; i < numPoints; i++) { game.receiverWinsPoint(); }
        });
        Then("^current score should be (.*)$", (String expectedScore) -> {
            String currentScore = game.getCurrentScore();
            Assert.assertEquals(currentScore, expectedScore);
        });
        Then("^the (.*) should win$", (String expectedWinner) -> {
            Optional<Player> winner = game.getWinner();
            Assert.assertTrue(winner.isPresent());
            Assert.assertEquals(expectedWinner, winner.map(Player::getName).orElse("None"));
        });
        Then("^nobody has won yet$", () -> {
            Optional<Player> winner = game.getWinner();
            Assert.assertFalse(winner.isPresent());
        });
        Given("^that I don’t specify any specific ruleset$", () ->
            game = ClassicGame.Builder.newInstance().build());
        When("^I use the library$", () -> {
            game.receiverWinsPoint();
            game.receiverWinsPoint();
            game.receiverWinsPoint();
            game.receiverWinsPoint();
        });
        Then("^it applies the default rules specified in Feature 1$", () -> {
            Optional<Player> winner = game.getWinner();
            Assert.assertTrue(winner.isPresent());
            Assert.assertEquals("receiver", winner.map(Player::getName).orElse("None"));
        });
        Given("^that I specify the custom direct set of rules to be used$", () -> {
            ScoreBoard directScoreBoard = CustomDirectScoreBoard.Builder.newInstance().build();
            game = ClassicGame.Builder.newInstance().score(directScoreBoard).build();
        });
        Then("^it only applies the rules I have specified$", () ->
            Assert.assertTrue(game.getScoreBoard() instanceof CustomDirectScoreBoard));
        When("^the server scores four points$", () -> {
            game.serverWinsPoint();
            game.serverWinsPoint();
            game.serverWinsPoint();
            game.serverWinsPoint();
        });
        Given("^that I specify the custom reset set of rules to be used$", () -> {
            ScoreBoard resetScoreBoard = CustomResetScoreBoard.Builder.newInstance().build();
            game = ClassicGame.Builder.newInstance().score(resetScoreBoard).build();
        });
        When("^the players have deuce$", () -> {
            game.serverWinsPoint();
            game.serverWinsPoint();
            game.serverWinsPoint();
            game.receiverWinsPoint();
            game.receiverWinsPoint();
            game.receiverWinsPoint();
        });
        Then("^the score should be (.*)$", (String score) -> {
            Assert.assertEquals(score, game.getCurrentScore());
        });
    }
}
