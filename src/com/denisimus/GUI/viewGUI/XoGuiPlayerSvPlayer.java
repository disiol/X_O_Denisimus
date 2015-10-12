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
 *
 * @version 1.0
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
    JLabel player1Name = new JLabel("plaeyr1 Name");
    JLabel player2Name = new JLabel("plaeyr 2Name");
    JLabel noWinnerJLabel = new JLabel(" No winner: ");
    int player1Win = 0;
    int player2Win = 0;
    int noWinner = 0;

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
        player1Name = new JLabel(plaeyrs[0].getName() + " : " + player1Win);
        player2Name = new JLabel(plaeyrs[1].getName() + " : " + player2Win);
        newGameButton = new JButton("New game");
        newGameButton.addActionListener(this);

        Panel topPanel = new Panel();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(newGameButton, "North");
        topPanel.add(player1Name, "West");
        topPanel.add(player2Name, "East");
        //topPanel.add(noWinnerJLabel, "Center");
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
                moveControllerGUI.applyFigure(filed, i, null);

                score.setText("move player : " + players[0].getName() + " figure: X");
            }

            //  Figure figure = Figure.X;
            //   score.setText("move player : " + playerName(players, figure));
            newGameButton.setEnabled(true);
            return;
        }

        // Одна из клеток
        metka:

        for (int i = 0; i < filed.getSize(); i++) {

            if (theButton == squares[i]) {


                boolean lookForWinner = true;
                if (lookForWinner == true) {
                    final Figure currentFigure = currentMoveControllerGUI.currentMove(filed);

                    squares[i].setFont(new java.awt.Font("TimesRoman", 0, 36));
                    moveControllerGUI.applyFigure(filed, i, currentFigure);
                    squares[i].setText(filed.getFigure(i).toString());


                    lookForWinner = lookForWinner(gameXO, players, i);


                } else {
                    continue metka;
                }

                if (lookForWinner == false) {
                    endTheGame();

                } else
                    break;
            }


        }


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
        final int[] winnerCoordinates = winnerControllerGUI.getWinnerCoordinates();
        if (winner != null) {


            highlightWinner(winnerCoordinates);

            score.setText("Winner is player: " + playerName(players, winner) + " Figure: " + winner);
            winerPlayer(winner, players);

            return false;
        }
        final Figure currentFigure = currentMoveControllerGUI.currentMove(filed);
        if (currentFigure == null) {
            if (winner == null) {
                score.setText("No winner and no moves left");
                return false;
            }

        }


        score.setText("Player move: " + playerName(players, currentFigure) + " figure: " + currentFigure);


        return true;
    }


    void highlightWinner(int highlightWinner[]) {

        squares[highlightWinner[0]].setBackground(Color.RED);
        squares[highlightWinner[1]].setBackground(Color.RED);
        squares[highlightWinner[2]].setBackground(Color.RED);
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

    private void winerPlayer(Figure winnerFigure, Player[] players) {
        final Player[] player = new GameGUI(players, null, null).getPlayers();

        if (winnerFigure == Figure.X) {
            player1Win++;
            player[0].getName().toString();
            player1Name.setText(player[0].getName().toString() + " : " + player1Win);


        }
        if (winnerFigure == Figure.O) {
            player2Win++;
            player2Name.setText(player[1].getName().toString() + " : " + player2Win);
        }
        if (winnerFigure == null) ;


    }
}

