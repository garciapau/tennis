package com.acme.game.tennis.domain.core;

import com.acme.game.tennis.domain.Game;
import com.acme.game.tennis.domain.model.Player;
import com.acme.game.tennis.domain.ScoreBoard;

import java.util.Optional;

public class ClassicGame implements Game {
    private ScoreBoard scoreBoard;

    private ClassicGame(ScoreBoard scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    @Override
    public String getCurrentScore() {
        return this.scoreBoard.getScoreboard();
    }

    @Override
    public void serverWinsPoint() {
        scoreBoard.annotatePoint(Player.SERVER);
    }

    @Override
    public void receiverWinsPoint() {
        scoreBoard.annotatePoint(Player.RECEIVER);
    }

    @Override
    public Optional<Player> getWinner() {
        return scoreBoard.getWinner();
    }

    public static class Builder{
        private ScoreBoard scoreBoard;

        public static Builder newInstance(){
            return new Builder();
        }

        public Builder score(ScoreBoard scoreBoard) {
            this.scoreBoard = scoreBoard;
            return this;
        }

        public Game build() {
            if (scoreBoard == null) {
                this.scoreBoard = ClassicScoreBoard.Builder.newInstance().build();
            }
            return new ClassicGame(scoreBoard);
        }
    }
}
