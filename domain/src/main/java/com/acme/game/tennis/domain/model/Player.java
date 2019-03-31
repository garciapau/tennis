package com.acme.game.tennis.domain.model;

public enum Player {
    SERVER("server"),
    RECEIVER("receiver");

    private String name;

    Player(String name) {
        this.name = name;

    }
    public String getName() {
        return name;
    }
}
