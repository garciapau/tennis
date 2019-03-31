package com.acme.game.tennis.domain;

import com.acme.game.tennis.domain.model.Player;

import java.util.Optional;

public interface ScoreBoard {
    String getScoreboard();

    void annotatePoint(Player player);

    Optional<Player> getWinner();

    void resetScore();
}
