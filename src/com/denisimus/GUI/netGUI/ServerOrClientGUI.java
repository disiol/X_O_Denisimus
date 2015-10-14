package com.denisimus.GUI.netGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


/**
 * Author: Olenyk Denis (deoniisii@gmail.com)
 * on 12.10.15.
 */

public class ServerOrClientGUI extends JFrame {
    JFrame jFrame = new JFrame("Server or client");
    private final JRadioButton ServerRadioButton = new JRadioButton("Server");
    private final JRadioButton ClientRadioButton = new JRadioButton("Client");
    private final JButton OKButton = new JButton("OK");
    private final JLabel askLabel = new JLabel("Cheese please ho ara you server or client and pres");

//    public static void main(String[] args) {
//        ServerOrClientGUI serverOrClientGUI = new ServerOrClientGUI();
//        serverOrClientGUI.jFrame.setVisible(true);
//    }

    public ServerOrClientGUI() {
        jFrame.setLayout(new GridLayout(0, 1));
        jFrame.add(askLabel);
        jFrame.add(ServerRadioButton);
        jFrame.add(ClientRadioButton);
        // jFrame.add(OKButton);
        jFrame.setSize(300, 300);
        jFrame.setLocationRelativeTo(null);
        jFrame.pack();
        jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);

        ServerRadioButton.addActionListener((ActionEvent e) -> {
            jFrame.setVisible(false);
            System.out.println("Server");

        });

        ServerRadioButton.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ENTER:
                        //TODO
                        break;

                }

            }

        });

    }
}
