package com.acme.game.tennis.domain;

import com.acme.game.tennis.domain.model.Score;

public interface Game {
    Score currentScore();

    void serverWinsPoint();

    void receiverWinsPoint();
}
