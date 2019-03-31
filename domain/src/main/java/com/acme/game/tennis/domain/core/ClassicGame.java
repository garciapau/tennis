package com.acme.game.tennis.domain.core;

import com.acme.game.tennis.domain.Game;
import com.acme.game.tennis.domain.model.Player;
import com.acme.game.tennis.domain.model.Score;

public class ClassicGame implements Game {
    private Score score;

    private ClassicGame(Score score) {
        this.score = score;
    }

    @Override
    public Score currentScore() {
        return this.score;
    }

    @Override
    public void serverWinsPoint() {
        score.annotatePoint(Player.SERVER);
    }

    @Override
    public void receiverWinsPoint() {
        score.annotatePoint(Player.RECEIVER);
    }

    public static class Builder{
        private Score score;

        public static Builder newInstance(){
            return new Builder();
        }

        public Builder score(Score score) {
            this.score = score;
            return this;
        }

        public Game build() {
            return new ClassicGame(score);
        }
    }
}
