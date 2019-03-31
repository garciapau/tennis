package com.acme.game.tennis.domain;

public interface Game {
    String getCurrentScore();

    void serverWinsPoint();

    void receiverWinsPoint();
}
