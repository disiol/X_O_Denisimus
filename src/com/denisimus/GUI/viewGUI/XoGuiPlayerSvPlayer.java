package com.denisimus.GUI.viewGUI;

import com.denisimus.CLI.modelCLI.Figure;
import com.denisimus.CLI.modelCLI.Player;
import com.denisimus.GUI.controlerGUI.CurrentMoveControllerGUI;
import com.denisimus.GUI.controlerGUI.MoveControllerGUI;
import com.denisimus.GUI.controlerGUI.WinnerControllerGUI;
import com.denisimus.GUI.modelGUI.FiledGUI;
import com.denisimus.GUI.modelGUI.GameGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Author: Olenyk Denis (deoniisii@gmail.com) on 07.08.15.
 */
public class XoGuiPlayerSvPlayer extends JFrame implements ActionListener {
    final Player[] players = new Player[2];
    final GameGUI gameXO = new GameGUI(players, new FiledGUI(9), "XO");
    final FiledGUI filed = gameXO.getFiled();
    private final CurrentMoveControllerGUI currentMoveControllerGUI = new CurrentMoveControllerGUI();
    private final WinnerControllerGUI winnerControllerGUI = new WinnerControllerGUI();
    private final MoveControllerGUI moveControllerGUI = new MoveControllerGUI();
    JButton squares[];
    JButton newGameButton;
    JLabel score = new JLabel("Puth the new game button");
    JLabel plaeyr1Name = new JLabel("plaeyr1Name");
    JLabel plaeyr2Name = new JLabel("plaeyr2Name");
    int plaeyr1Win = 0;
    int plaeyr2Win = 0;
    Figure winnerFigure = null;

    //private Figure figure;


    public void setGuiXO(String plaeyr1, String plaeyr2) {


        final String name1 = plaeyr1;
        final String name2 = plaeyr2;

        //написать волидатор

        players[0] = new Player(name1, Figure.X);
        players[1] = new Player(name2, Figure.O);

        final GameGUI gameXO = new GameGUI(players, new FiledGUI(9), "XO");


        JFrame frame = new JFrame("Game name: " + gameXO.getName());
        final Player[] plaeyrs = gameXO.getPlayers();

        // Менеджер расположения апплета, шрифт и цвет
        frame.setLayout(new BorderLayout());
        frame.setBackground(Color.WHITE);
        frame.setSize(500, 500);

        // Шрифт
        Font font = new Font("Monospased", Font.BOLD, 20);
        frame.setFont(font);

        // Кнопка “New Game” и слушатель действия
        plaeyr1Name = new JLabel(plaeyrs[0].getName() + " : " + plaeyr1Win);
        plaeyr2Name = new JLabel(plaeyrs[1].getName() + " : " + plaeyr2Win);
        newGameButton = new JButton("New game");
        newGameButton.addActionListener(this);

        Panel topPanel = new Panel();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(newGameButton, "North");
        topPanel.add(plaeyr1Name, "West");
        topPanel.add(plaeyr2Name, "East");
        frame.add(topPanel, "North");
        Panel centerPanel = new Panel();
        centerPanel.setLayout(new GridLayout(3, 3));
        frame.add(centerPanel, "Center");
        score = new JLabel("Push the new game button");
        frame.add(score, "South");

        // Массив для хранения ссылок на 9 кнопок
        squares = new JButton[filed.getSize()];

        // Кнопки
        for (int i = 0; i < filed.getSize(); i++) {
            squares[i] = new JButton();
            squares[i].addActionListener(this);
            squares[i].setBackground(Color.green);
            centerPanel.add(squares[i]);
            squares[i].setEnabled(false);
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {


        JButton theButton = (JButton) e.getSource();
        // Кнопка New Game
        if (theButton.equals(newGameButton)) {
            for (int i = 0; i < filed.getSize(); i++) {
                squares[i].setEnabled(true);
                squares[i].setText("");
                squares[i].setBackground(Color.green);
                score.setText("move player : " + players[0].getName() + " figure: X");
            }

            //  Figure figure = Figure.X;
            //   score.setText("move player : " + playerName(players, figure));
            newGameButton.setEnabled(true);
            return;
        }

//        String winner = true;
        // Одна из клеток
        metka:

        for (int i = 0; i < filed.getSize(); i++) {

            if (theButton == squares[i]) {
                boolean lookForWinner = lookForWinner(gameXO, players, i);


                if (lookForWinner == true) {
                    //final Figure currentFigure = currentMoveControllerGUI.currentMove(filed);

                    squares[i].setFont(new java.awt.Font("TimesRoman", 0, 36));
                    // winner = lookForWinner;


                    // Figure figure = ;

                } else {
                    continue metka;
                }

                if (lookForWinner == false) {
                    endTheGame();

                }
                break;
            }


        }

//
//        Figure currentFigure = currentMoveControllerGUI.currentMove(filed);
//        final Figure figure = new CurrentMoveControllerGUI().currentMove(filed);
//
//
//        if (figure.equals("X")) {
//            ++plaeyr1Win;
//            //labWin.setText("ВЫ: " + plaeyr1Win);
//            score.setText("Winner is player: " + playerName(players, figure).toString() + " figure: " + figure.toString());
//
//        } else if (figure.equals("O")) {
//            ++plaeyr2Win;
//            score.setText("Winner is player: " + playerName(players, figure).toString() + " figure: " + figure.toString());
//            // labLoose.setText("ПК: " + loose);
//
//        } else if (currentFigure == null) {
//            if (winnerFigure == null) {
//                score.setText("No winner and no moves left");
//
//            }
//
//        }


    }

    void endTheGame() {
        newGameButton.setEnabled(true);
        for (int i = 0; i < filed.getSize(); i++) {
            squares[i].setEnabled(false);
        }
    }


    public boolean lookForWinner(final GameGUI game, Player[] players, int i) {
        final FiledGUI filed = game.getFiled();
        final Figure winner = winnerControllerGUI.getWinner(filed);
        if (winner != null) {

            System.out.println();
            score.setText("Winner is player: " + playerName(players, winner) + " Figure: " + winner);


            return false;
        }
        final Figure currentFigure = currentMoveControllerGUI.currentMove(filed);
        if (currentFigure == null) {
            if (winner == null) {
                score.setText("No winner and no moves left");
                return false;
            }

        }

        final int point = i;

        moveControllerGUI.applyFigure(filed, point, currentFigure);
        squares[point].setText(filed.getFigure(point).toString());
        Figure figure = null;

        if (currentFigure.toString().equals("X")) {
            figure = Figure.O;
        }
        if (currentFigure.toString().equals("O")) {
            figure = Figure.X;
        }

        score.setText("Player move: " + playerName(players, figure) + " figure: " + figure);


        return true;
    }


    /**
     * @param players
     * @param input
     * @return
     */
    private String playerName(Player[] players, Figure input) {
        final Player[] player = new GameGUI(players, null, null).getPlayers();

        if (input == Figure.X) {
            return player[0].getName().toString();

        }
        if (input == Figure.O) {
            return player[1].getName().toString();
        }

        return null;

    }
}

