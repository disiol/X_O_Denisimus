package com.denisimus.CLI.viewCLI;

import com.denisimus.CLI.controlerCLI.CurrentMoveController;
import com.denisimus.CLI.controlerCLI.MoveController;
import com.denisimus.CLI.controlerCLI.WinnerController;
import com.denisimus.CLI.modelCLI.Figure;
import com.denisimus.CLI.modelCLI.Filed;
import com.denisimus.CLI.modelCLI.Game;
import com.denisimus.CLI.modelCLI.Player;
import com.denisimus.CLI.modelCLI.exeptions.AlreadyOccupantException;
import com.denisimus.CLI.modelCLI.exeptions.InvalidPointException;

import java.awt.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Author: Olenyk Denis (deoniisii@gmail.com)
 * on 07.08.15.
 */

public class ConsoleView {

    private final CurrentMoveController currentMoveController = new CurrentMoveController();

    private final WinnerController winnerController = new WinnerController();

    private final MoveController moveController = new MoveController();

    public void show(final Game game) {
        System.out.printf("Game name: %s\n", game.getName());
        final Filed filed = game.getFiled();
        for (int x = 0; x < filed.getSize(); x++) {
            if (x != 0) {
                printSeparator();
            }
            printLine(filed, x);
        }

    }

    public boolean move(final Game game, Player[] players) {
        final Filed filed = game.getFiled();
        final Figure winner = winnerController.getWinner(filed);
        if (winner != null) {

            System.out.println();
            System.out.printf("Winner is player: %s, Figure: %s\n", playerName(players, winner), winner);


            return false;
        }
        final Figure currentFigure = currentMoveController.currentMove(filed);
        if (currentFigure == null) {
            if (winner == null) {
                System.out.println("No winner and no moves left");
                return false;
            }

        }
        System.out.printf("Player move: %s, figure: %s\nPlease enter move point \n", playerName(players, currentFigure), currentFigure);
        final Point point = askPoint();
        try {
            moveController.applyFigure(filed, point, currentFigure);
        } catch (final InvalidPointException | AlreadyOccupantException e) {
            System.out.println("Point is invalid");
        }
        return true;
    }


    private String playerName(Player[] players, Figure input) {
        final Player[] player = new Game(players, null, null).getPlayers();

        if (input == Figure.X) {
            return player[0].getName();
        }
        if (input == Figure.O) {
            return player[1].getName();
        }


        return null;
    }

    private Point askPoint() {
        return new Point(askCoordinate("X") - 1, askCoordinate("Y") - 1);
    }

    private int askCoordinate(final String coordinateName) {

        System.out.printf("Please input %s: ", coordinateName);
        final Scanner in = new Scanner(System.in);

        try {
            return in.nextInt();
        } catch (final InputMismatchException e) {
            System.out.println("O_O olololo!!!!");
            return askCoordinate(coordinateName);
        }


    }

    private void printLine(final Filed filed,
                           final int x) {


        for (int y = 0; y < filed.getSize(); y++) {
            if (y != 0) {
                System.out.print("|");
            }
            System.out.print(" ");

            final Figure figure;
            try {
                figure = filed.getFigure(new Point(y, x));
            } catch (final InvalidPointException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            System.out.print(figure != null ? figure : " ");
            System.out.print(" ");


        }
        System.out.println();


    }

    private void printSeparator() {
        System.out.println(" " + "~~~~~~~~~");
    }


}
