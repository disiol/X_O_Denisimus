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



    public ServerOrClientGUI() {
        jFrame.setLayout(new GridLayout(0, 1));
        jFrame.add(askLabel);
        jFrame.add(ServerRadioButton);
        jFrame.add(ClientRadioButton);
        jFrame.setSize(300, 300);
        jFrame.setLocationRelativeTo(null);
        jFrame.pack();
        jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);

        ServerRadioButton.addActionListener((ActionEvent e) -> {
            jFrame.setVisible(false);
            Server server = null;
            try {
                server = new Server();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            server.frame.setVisible(true);
        });

        ClientRadioButton.addActionListener((ActionEvent e) -> {
            jFrame.setVisible(false);
            System.out.println("Client");
            //TODO

        });





    }
}
