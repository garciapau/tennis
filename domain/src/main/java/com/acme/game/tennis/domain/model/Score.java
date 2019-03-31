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

    public static class Builder{
        private Integer server;
        private Integer receiver;

        public static Builder newInstance(){
            return new Builder();
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
