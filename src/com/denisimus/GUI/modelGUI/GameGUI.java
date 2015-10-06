package com.denisimus.GUI.modelGUI;

import com.denisimus.CLI.modelCLI.Player;

/**
 * Author: Olenyk Denis (deoniisii@gmail.com)
 * on 31.07.15.
 */

public class GameGUI {

    private final Player[] players;

    private final FiledGUI filed;

    private final String name;


    public GameGUI(final Player[] players,
                   final FiledGUI filed,
                   final String name) {

        this.players = players;
        this.filed = filed;
        this.name = name;
    }


    public Player[] getPlayers() {
        return players;
    }

    public FiledGUI getFiled() {
        return filed;
    }

    public String getName() {
        return name;
    }
}
