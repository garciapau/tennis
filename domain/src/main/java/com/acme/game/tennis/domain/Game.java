package com.acme.game.tennis.domain;

import com.acme.game.tennis.domain.model.Player;

import java.util.Optional;

public interface Game {
    String getCurrentScore();

    void serverWinsPoint();

    void receiverWinsPoint();

    Optional<Player> getWinner();
}
