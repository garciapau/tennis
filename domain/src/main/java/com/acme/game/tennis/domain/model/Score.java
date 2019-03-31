package com.acme.game.tennis.domain.model;

public class Score {
    private Integer server;
    private Integer receiver;

    private Score(Integer server, Integer receiver) {
        this.server = server;
        this.receiver = receiver;
    }

    public Integer getServer() {
        return server;
    }

    public Integer getReceiver() {
        return receiver;
    }

    public void annotatePoint(String player) {
        if ("server".equals(player)) server = server + 15;
        else receiver = receiver + 15;
    }

    public static class Builder{
        private Integer server;
        private Integer receiver;

        private Builder(Integer server, Integer receiver) {
            this.server = server;
            this.receiver = receiver;
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

        public Score build() {
            return new Score(this.server, this.receiver);
        }
    }
}
