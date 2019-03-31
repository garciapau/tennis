package com.acme.game.tennis.domain.core;

import com.acme.game.tennis.domain.model.Player;

import java.util.Optional;

public class CustomScoreBoard extends BasicScoreBoard {

    @Override
    public void annotatePoint(Player player) {
        if (super.getServerScore()<4 && super.getReceiverScore()<4) {
            super.annotatePoint(player);
        }
    }

    @Override
    public String getScoreboard() {
        String calculatedScore = "";
        calculatedScore = String.format("%s:%s", translatePoint(getServerScore()), translatePoint(getReceiverScore()));
        return calculatedScore;
    }

    @Override
    public Optional<Player> getWinner() {
        if (super.getServerScore()>3) return Optional.of(Player.SERVER);
        if (super.getReceiverScore()>3) return Optional.of(Player.RECEIVER);
        return Optional.empty();
    }


}
