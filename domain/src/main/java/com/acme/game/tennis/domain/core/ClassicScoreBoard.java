package com.acme.game.tennis.domain.core;

import com.acme.game.tennis.domain.ScoreBoard;
import com.acme.game.tennis.domain.exception.GameFinishedException;
import com.acme.game.tennis.domain.model.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ClassicScoreBoard implements ScoreBoard {
    private Integer[] players = new Integer[Player.values().length];
    private static Map<Integer, String> pointsTranslator = new HashMap<>();

    ClassicScoreBoard(Integer server, Integer receiver) {
        populateTranslators();
        this.players[Player.SERVER.ordinal()] = server;
        this.players[Player.RECEIVER.ordinal()] = receiver;
    }

    @Override
    public void annotatePoint(Player player) throws GameFinishedException {
        if (getWinner().isPresent()) throw new GameFinishedException();
        players[player.ordinal()] = players[player.ordinal()] + 1;
    }

    @Override
    public String getScoreboard() {
        String calculatedScore = "";
        if (lowerPoints())
            calculatedScore = String.format("%s:%s", translatePoint(getServerScore()), translatePoint(getReceiverScore()));
        else {
            if (deuce()) calculatedScore = String.format("%s:%s", translatePoint(3), translatePoint(3));
            if (advantageServer()) calculatedScore = String.format("%s:%s", translatePoint(4), translatePoint(3));
            if (advantageReceiver()) calculatedScore = String.format("%s:%s", translatePoint(3), translatePoint(4));
        }
        return calculatedScore;
    }

    @Override
    public Optional<Player> getWinner() {
        if (playerHasWon(Player.SERVER, Player.RECEIVER)) return Optional.of(Player.SERVER);
        if (playerHasWon(Player.RECEIVER, Player.SERVER)) return Optional.of(Player.RECEIVER);
        return Optional.empty();
    }

    @Override
    public void resetScore() {
        players[Player.SERVER.ordinal()] = 0;
        players[Player.RECEIVER.ordinal()] = 0;
    }

    private void populateTranslators() {
        pointsTranslator.put(0, "0");
        pointsTranslator.put(1, "15");
        pointsTranslator.put(2, "30");
        pointsTranslator.put(3, "40");
        pointsTranslator.put(4, "A");
    }

    Integer getServerScore() {
        return players[Player.SERVER.ordinal()];
    }

    Integer getReceiverScore() {
        return players[Player.RECEIVER.ordinal()];
    }

    private boolean deuce() {
        return getServerScore() == getReceiverScore();
    }

    private boolean advantageReceiver() {
        return getReceiverScore()>3 && (getServerScore() == getReceiverScore() - 1);
    }

    private boolean advantageServer() {
        return getServerScore()>3 && (getServerScore() == getReceiverScore() + 1);
    }

    private boolean lowerPoints() {
        return getServerScore()<4 || getReceiverScore()<4;
    }

    protected String translatePoint(Integer point) {
        return pointsTranslator.get(point);
    }

    private boolean playerHasWon(Player winner, Player looser) {
        return (players[winner.ordinal()] > 3 && players[winner.ordinal()] > players[looser.ordinal()] + 1);
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

        public ScoreBoard build() {
            return new ClassicScoreBoard(this.server, this.receiver);
        }
    }
}
