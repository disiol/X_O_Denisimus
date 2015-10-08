package com.denisimus.GUI.viewGUI;

import com.denisimus.CLI.modelCLI.Figure;
import com.denisimus.CLI.modelCLI.Filed;
import com.denisimus.CLI.modelCLI.Player;
import com.denisimus.CLI.modelCLI.exeptions.AlreadyOccupantException;
import com.denisimus.CLI.modelCLI.exeptions.InvalidPointException;
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

    JButton squares[];
    JButton newGameButton;
    JLabel score = new JLabel("Puth the new game button");
    JLabel plaeyr1Name = new JLabel("plaeyr1Name");
    JLabel plaeyr2Name = new JLabel("plaeyr2Name");
    int plaeyr1Win = 0;
    int plaeyr2Win = 0;
    int emptySquaresLeft = 9;

    public void setGuiXO(String plaeyr1, String plaeyr2) {


        final String name1 = plaeyr1;
        final String name2 = plaeyr2;

        //написать волидатор

        players[0] = new Player(name1, Figure.X);
        players[1] = new Player(name2, Figure.O);

        final GameGUI gameXO = new GameGUI(players, new FiledGUI(9), "XO");


//        System.out.println();
//        System.out.printf("%s Figure: %s\n", players[0].getName(), players[0].getFigure());
//        System.out.printf("%s Figure: %s \n", players[1].getName(), players[1].getFigure());
//        System.out.println();
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
        newGameButton = new JButton("new game");
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
            boolean b = false;
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
        GuiView guiView = new GuiView();
        newGameButton.setEnabled(false);
        JButton theButton = (JButton) e.getSource();
        // Кнопка New Game
        if (theButton == newGameButton) {
            for (int i = 0; i <filed.getSize() ; i++) {
                squares[i].setEnabled(true);
                squares[i].setText("");
                squares[i].setBackground(Color.green);

            }

            score.setText("Player move: " + players[0].getName() + " figure: " + players[0].getFigure());
            newGameButton.setEnabled(true);

        }

        // Одна из клеток
        for (int i = 0; i < filed.getSize(); i++) {

            if (theButton == squares[i]) {


                try {
                    if (guiView.move(gameXO, players, i) == true) {
                        squares[i].setText(String.valueOf(filed.getFigure(i)));

                        score.setText("Player move: " + guiView.playerName(players, filed.getFigure(i)) + " figure: "
                                + guiView);
                        squares[i].setFont(new Font("TimesRoman", 0, 36));


                        //winner = lookForWinner();
                    }
                } catch (InvalidPointException ei) {
                    ei.printStackTrace();
                }


            }


        }

    }
}

