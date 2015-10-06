package com.denisimus.CLI.viewCLI;

import com.denisimus.CLI.modelCLI.Figure;
import com.denisimus.CLI.modelCLI.Filed;
import com.denisimus.CLI.modelCLI.Game;
import com.denisimus.CLI.modelCLI.Player;

/**
 * Author: Olenyk Denis (deoniisii@gmail.com)
 * on 07.08.15.
 */

public class XOCLI {


    public void setXOCLI(String plaeyr1, String plaeyr2) {
        final String name1 = plaeyr1;
        final String name2 = plaeyr2;


        //написать волидатор
        final Player[] players = new Player[2];
        players[0] = new Player(name1, Figure.X);
        players[1] = new Player(name2, Figure.O);

        final Game gameXO = new Game(players, new Filed(3), "XO");
        System.out.println();
        System.out.printf("%s Figure: %s\n", players[0].getName(), players[0].getFigure());
        System.out.printf("%s Figure: %s \n", players[1].getName(), players[1].getFigure());
        System.out.println();


        final ConsoleView consoleView = new ConsoleView();
        consoleView.show(gameXO);
        while (consoleView.move(gameXO, players)) {
            System.out.println();
            consoleView.show(gameXO);
            System.out.println();
        }

    }
}
