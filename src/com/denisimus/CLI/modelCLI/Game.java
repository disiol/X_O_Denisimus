package com.denisimus.CLI.modelCLI;

/**
 * Author: Olenyk Denis (deoniisii@gmail.com)
 * on 31.07.15.
 */

public class Game {

    private final Player[] players;

    private final Filed filed;

    private final String name;


    public Game(final Player[] players,
                final Filed filed,
                final String name) {

        this.players = players;
        this.filed = filed;
        this.name = name;
    }


    public Player[] getPlayers() {
        return players;
    }

    public Filed getFiled() {
        return filed;
    }

    public String getName() {
        return name;
    }
}
