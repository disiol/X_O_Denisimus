package com.denisimus.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Author: Olenyk Denis (deoniisii@gmail.com) on 17.08.15.
 */
public class MainGUI extends JFrame {


    private final JButton playerSVPlayerButton = new JButton("Player SV Player");
    private final JButton playerSVPCButton = new JButton("Player SV PC");
    private final JButton gameOnANetworkButton = new JButton("Game On ANetwork");
    private final JButton tableOfRecordsButton = new JButton("Table Of Records");
    private final JButton exitButton = new JButton("exit");
    JFrame main = new JFrame("X_O_denisimus");

    public MainGUI() {
        main.setSize(300, 300);
        main.setDefaultCloseOperation(EXIT_ON_CLOSE);
        main.setLocationRelativeTo(null);

        main.setLayout(new GridLayout(0, 1));
        main.add(playerSVPlayerButton);
        main.add(playerSVPCButton);
        main.add(gameOnANetworkButton);
        main.add(tableOfRecordsButton);
        main.add(exitButton);
        main.setVisible(true);

        playerSVPlayerButton.addActionListener((ActionEvent e) -> {
            main.setVisible(false);

            PlayersNames playersNames = new PlayersNames();
            playersNames.frame.setVisible(true);



        });

        playerSVPCButton.addActionListener((ActionEvent e) -> {
            //TODO
        });
        gameOnANetworkButton.addActionListener((ActionEvent e) -> {
            //TODO

        });
        tableOfRecordsButton.addActionListener((ActionEvent e) -> {
            //TODO

        });
        exitButton.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
    }

}


