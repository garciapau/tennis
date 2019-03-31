package com.acme.game.tennis.domain.core;

import com.acme.game.tennis.domain.ScoreBoard;
import com.acme.game.tennis.domain.model.Player;

import java.util.HashMap;
import java.util.Map;

public class ClassicScoreBoard implements ScoreBoard {
    private Integer server;
    private Integer receiver;
    private static Map<Integer, String> pointsTranslator = new HashMap<>();

    private ClassicScoreBoard(Integer server, Integer receiver) {
        populateTranslators();
        this.server = server;
        this.receiver = receiver;
    }

    private void populateTranslators() {
        pointsTranslator.put(0, "0");
        pointsTranslator.put(1, "15");
        pointsTranslator.put(2, "30");
        pointsTranslator.put(3, "40");
        pointsTranslator.put(4, "A");
    }

    public Integer getServer() {
        return server;
    }

    public Integer getReceiver() {
        return receiver;
    }

    @Override
    public String getScoreboard() {
        String calculatedScore = "";
        if (getServer()<4 && getReceiver()<4)
            calculatedScore = String.format("%s:%s", translatePoint(getServer()), translatePoint(getReceiver()));
        else {
            if (getServer() == getReceiver()) calculatedScore = String.format("%s:%s", translatePoint(3), translatePoint(3));
            if (getServer() > getReceiver()) calculatedScore = String.format("%s:%s", translatePoint(4), translatePoint(3));
            if (getServer() < getReceiver()) calculatedScore = String.format("%s:%s", translatePoint(3), translatePoint(4));
        }
        return calculatedScore;
    }

    private String translatePoint(Integer point) {
        return pointsTranslator.get(point);
    }

    @Override
    public void annotatePoint(Player player) {
        if (player == Player.SERVER) server = calculatePoints(server);
        else receiver = calculatePoints(receiver);
    }

    private int calculatePoints(Integer currentPoints) {
        return currentPoints+1;
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

        public Builder server(Integer points) {
            this.server = points;
            return this;
        }

        public Builder receiver(Integer points) {
            this.receiver = points;
            return this;
        }

        public Builder server(String points) {
            this.server = literalsTranslator.get(points);
            return this;
        }

        public Builder receiver(String points) {
            this.receiver = literalsTranslator.get(points);
            return this;
        }

        public ScoreBoard build() {
            return new ClassicScoreBoard(this.server, this.receiver);
        }
    }
}
