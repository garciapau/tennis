package com.acme.game.tennis.domain.core;

import com.acme.game.tennis.domain.exception.GameFinishedException;
import com.acme.game.tennis.domain.model.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CustomDirectScoreBoard extends ClassicScoreBoard {

    private CustomDirectScoreBoard(Integer server, Integer receiver) {
        super(server, receiver);
    }

    @Override
    public void annotatePoint(Player player) throws GameFinishedException {
        if (super.getServerScore()<4 && super.getReceiverScore()<4) {
            super.annotatePoint(player);
        }
    }

    @Override
    public String getScoreboard() {
        return String.format("%s:%s", translatePoint(getServerScore()), translatePoint(getReceiverScore()));
    }

    @Override
    public Optional<Player> getWinner() {
        if (super.getServerScore()>3) return Optional.of(Player.SERVER);
        if (super.getReceiverScore()>3) return Optional.of(Player.RECEIVER);
        return Optional.empty();
    }

    public static class Builder{
        private Integer server;
        private Integer receiver;
        private static Map<String, Integer> literalsTranslator = new HashMap<>();

        private Builder(Integer server, Integer receiver) {
            this.server = server;
            this.receiver = receiver;
            literalsTranslator.put("0", 0);
            literalsTranslator.put("15", 1);
            literalsTranslator.put("30", 2);
            literalsTranslator.put("40", 3);
            literalsTranslator.put("A", 4);
        }

        public static Builder newInstance(){
            return new Builder(0, 0);
        }

        public Builder server(String points) {
            this.server = literalsTranslator.get(points);
            return this;
        }

        public Builder receiver(String points) {
            this.receiver = literalsTranslator.get(points);
            return this;
        }

        public CustomDirectScoreBoard build() {
            return new CustomDirectScoreBoard(this.server, this.receiver);
        }
    }
}
