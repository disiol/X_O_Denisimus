package com.denisimus.CLI.netCLI.netViewCLI;

import com.denisimus.CLI.modelCLI.Figure;
import com.denisimus.CLI.modelCLI.Filed;
import com.denisimus.CLI.modelCLI.Game;
import com.denisimus.CLI.modelCLI.Player;

/**
 * Author: Olenyk Denis (deoniisii@gmail.com)
 * on 10.08.15.
 * меню
 */

public class XONet {


    public Game playersNamesAndFigure(String plaeyr1, String plaeyr2, String gameName) {
        final String name1 = plaeyr1;
        final String name2 = plaeyr2;


        //написать волидатор
        final Player[] players = new Player[2];
        players[0] = new Player(name1, Figure.X);
        players[1] = new Player(name2, Figure.O);

        final Game gameXO = new Game(players, new Filed(3), gameName);
        System.out.println();
        System.out.printf("%s Figure: %s\n", players[0].getName(), players[0].getFigure());
        System.out.printf("%s Figure: %s \n", players[1].getName(), players[1].getFigure());
        System.out.println();

        return gameXO;


//
//            final ConsoleViewNet consoleViewNet = new ConsoleViewNet();
//            consoleViewNet.show(gameXO);
//            while (consoleViewNet.move(gameXO, players)) {
//                System.out.println();
//                consoleViewNet.show(gameXO);
//                System.out.println();
//            }

    }



}
