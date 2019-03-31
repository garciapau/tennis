package com.acme.game.tennis.domain;

import com.acme.game.tennis.domain.model.Player;

public interface ScoreBoard {
    String getScoreboard();

    void annotatePoint(Player player);
}
