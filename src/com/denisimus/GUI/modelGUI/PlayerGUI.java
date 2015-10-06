package com.denisimus.GUI.modelGUI;

import com.denisimus.CLI.modelCLI.Figure;

/**
 * Author: Olenyk Denis (deoniisii@gmail.com)
 * on 30.07.15.
 */

public class PlayerGUI {

    private final String name;

    private final Figure figure;


    public PlayerGUI(String name, Figure figure) {
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
