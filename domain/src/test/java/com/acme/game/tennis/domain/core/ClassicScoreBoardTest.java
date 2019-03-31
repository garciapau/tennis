package com.acme.game.tennis.domain.core;

import com.acme.game.tennis.domain.ScoreBoard;
import com.acme.game.tennis.domain.exception.GameFinishedException;
import com.acme.game.tennis.domain.model.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

public class ClassicScoreBoardTest {
    @Before
    public void setUp() {
    }

    @Test
    public void testAnnotatePoint_whenGameStarts_shouldAddOne() throws GameFinishedException {
        ScoreBoard classicScoreBoard = ClassicScoreBoard.Builder.newInstance().server("0").receiver("0").build();
        classicScoreBoard.annotatePoint(Player.SERVER);
        String result = classicScoreBoard.getScoreboard();
        Assert.assertEquals("15:0", result);
    }

    @Test(expected = GameFinishedException.class)
    public void testAnnotatePoint_whenGameCompleted_shouldRaiseException() throws GameFinishedException {
        ScoreBoard classicScoreBoard = ClassicScoreBoard.Builder.newInstance().server("A").receiver("0").build();
        classicScoreBoard.annotatePoint(Player.SERVER);
        Assert.fail();
    }

    @Test
    public void testGetScoreboard_shouldConcatValues() {
        ScoreBoard classicScoreBoard = ClassicScoreBoard.Builder.newInstance().server("30").receiver("0").build();
        String result = classicScoreBoard.getScoreboard();
        Assert.assertEquals("30:0", result);
    }

    @Test
    public void testGetWinner_whenServerScoresFourPoints_shouldReturnServer() {
        ScoreBoard classicScoreBoard = ClassicScoreBoard.Builder.newInstance().server("A").receiver("0").build();
        Optional<Player> result = classicScoreBoard.getWinner();
        Assert.assertTrue(result.isPresent());
        Assert.assertEquals(true, result.map(player -> "server".equals(player.getName())).orElse(false));
    }

    @Test
    public void testGetWinner_whenServerScoresThreePoints_shouldReturnEmpty() {
        ScoreBoard classicScoreBoard = ClassicScoreBoard.Builder.newInstance().server("40").receiver("0").build();
        Optional<Player> result = classicScoreBoard.getWinner();
        Assert.assertFalse(result.isPresent());
    }

    @Test
    public void testResetScore_whenAnyValue_shouldReturnScoreBlank() {
        ScoreBoard classicScoreBoard = ClassicScoreBoard.Builder.newInstance().server("A").receiver("30").build();
        classicScoreBoard.resetScore();
        Assert.assertEquals(classicScoreBoard.getScoreboard(), "0:0");
    }
}