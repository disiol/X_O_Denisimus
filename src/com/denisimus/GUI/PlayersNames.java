package com.denisimus.GUI;

import com.denisimus.GUI.viewGUI.XoGuiPlayerSvPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Author: Olenyk Denis (deoniisii@gmail.com) on 22.08.15.
 */
public class PlayersNames extends JFrame {

    private final JButton OKButton = new JButton("OK");
    private final JLabel emptyLabel = new JLabel("");
    JFrame frame = new JFrame("Enter the players names");
    private JFormattedTextField enterTheNameOfPlayer1avTextField = new JFormattedTextField("Enter the name of player1");
    private JFormattedTextField enterTheNameOfPlayer2TextField = new JFormattedTextField("Enter the name of player2");

    public PlayersNames() {
        frame.setLayout(new GridLayout(0, 1));
        frame.add(enterTheNameOfPlayer1avTextField);
        frame.add(enterTheNameOfPlayer2TextField);
        frame.add(OKButton);
        frame.add(emptyLabel);
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        OKButton.addActionListener((ActionEvent e) -> {

            visiblePlayerSvPlayer();

        });

        enterTheNameOfPlayer1avTextField.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ENTER:
                        visiblePlayerSvPlayer();
                        break;

                }

            }

        });


        enterTheNameOfPlayer2TextField.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ENTER:
                        visiblePlayerSvPlayer();
                        break;

                }

            }

        });


    }

    private void visiblePlayerSvPlayer() {

        String player1 = enterTheNameOfPlayer1avTextField.getText();
        String player2 = enterTheNameOfPlayer2TextField.getText();

        if (player1.equals("") && player2.equals("") || player1.equals("")
                || player2.equals("")) {
            frame.setVisible(true);
            emptyLabel.setText("Names cannot be empty");
        } else {

            frame.setVisible(false);
            XoGuiPlayerSvPlayer xoGuiPlayerSvPlayer = new XoGuiPlayerSvPlayer();
            xoGuiPlayerSvPlayer.setGuiXO(player1, player2);

        }

    }
}
