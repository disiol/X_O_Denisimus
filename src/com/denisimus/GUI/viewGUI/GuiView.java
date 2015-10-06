package com.denisimus.GUI.viewGUI;

import com.denisimus.GUI.controlerGUI.CurrentMoveControllerGUI;
import com.denisimus.GUI.controlerGUI.MoveControllerGUI;
import com.denisimus.GUI.controlerGUI.WinnerControllerGUI;
import com.denisimus.GUI.modelGUI.FiledGUI;
import com.denisimus.GUI.modelGUI.GameGUI;
import com.denisimus.CLI.modelCLI.Figure;
import com.denisimus.CLI.modelCLI.Player;
import com.denisimus.CLI.modelCLI.exeptions.AlreadyOccupantException;
import com.denisimus.CLI.modelCLI.exeptions.InvalidPointException;

import java.awt.*;


/**
 * Author: Olenyk Denis (deoniisii@gmail.com) on 07.08.15.
 */
public class GuiView {

    private final CurrentMoveControllerGUI currentMoveControllerGUI = new CurrentMoveControllerGUI();

    private final WinnerControllerGUI winnerControllerGUI = new WinnerControllerGUI();

    private final MoveControllerGUI moveControllerGUI = new MoveControllerGUI();


    public void show(final GameGUI gameGUI) {
        System.out.printf("Game name: %s\n", gameGUI.getName());
        final FiledGUI filedGUI = gameGUI.getFiled();


    }

    public boolean move(final GameGUI gameGUI, Player[] players, int point) throws InvalidPointException {
        final FiledGUI filedGUI = gameGUI.getFiled();
        final Figure winner = winnerControllerGUI.getWinner(filedGUI);
        if (winner != null) {

            System.out.println();
            System.out.printf("Winner is player: %s, Figure: %s\n", playerName(players, winner));

            return false;
        }
        final Figure currentFigure = currentMoveControllerGUI.currentMove(filedGUI);
        if (currentFigure == null) {
            if (winner == null) {
                System.out.println("No winner and no moves left");
                return false;
            }

        }
        System.out.printf("Player move: %s, figure: %s\nPlease enter move point \n", playerName(players, currentFigure), currentFigure);
        //final Point point = askPoint();
        try {
            moveControllerGUI.applyFigure(filedGUI, point, currentFigure);
        } catch (InvalidPointException | AlreadyOccupantException e) {
            System.out.println("Point is invalid");
        }
       return true;
    }

    protected String playerName(Player[] players, Figure input) {
        final Player[] player = new GameGUI(players, null, null).getPlayers();

        if (input == Figure.X) {
            return player[0].getName();

        }
        if (input == Figure.O) {
            return player[1].getName();
        }

        return null;
    }
//    private Point askPoint() {
//        return new Point(askCoordinate("X") - 1, askCoordinate("Y") - 1);
//    }

//    private int askCoordinate(final String coordinateName) {
//
//        System.out.printf("Please input %s: ", coordinateName);
//        final Scanner in = new Scanner(System.in);
//
//        try {
//            return in.nextInt();
//        } catch (final InputMismatchException e) {
//            System.out.println("O_O olololo!!!!");
//            return askCoordinate(coordinateName);
//        }
//
//    }
//    private void printLine(final FiledGUI filed,
//                           final int x) {
//
//
//        for (int y = 0; y < filed.getSize(); y++) {
//            if (y != 0) {
//                System.out.print("|");
//            }
//            System.out.print(" ");
//
//            final Figure figure;
//            try {
//                figure = filed.getFigure(new Point(y, x));
//            } catch (final InvalidPointException e) {
//                e.printStackTrace();
//                throw new RuntimeException(e);
//            }
//            System.out.print(figure != null ? figure : " ");
//            System.out.print(" ");
//
//
//        }
//        System.out.println();
//
//
//    }
//
//    private void printSeparator() {
//        System.out.println(" " + "~~~~~~~~~");
//    }
}

