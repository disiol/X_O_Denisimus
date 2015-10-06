package com.denisimus.CLI.modelCLI;

/**
 * Author: Olenyk Denis (deoniisii@gmail.com)
 * on 30.07.15.
 */

public class Player {

    private final String name;

    private final Figure figure;


    public Player(String name, Figure figure) {
        this.name = name;
        this.figure = figure;
    }


    public String getName() {
        return name;
    }

    public Figure getFigure() {
        return figure;
    }
}
