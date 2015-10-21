package com.denisimus.GUI.netGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Author: Olenyk Denis (deoniisii@gmail.com)
 * on 10.08.15.
 */

public class ClientGUI extends JFrame {
    JFrame Mainframe = new JFrame("ClientXO");

    JLabel player1Label = new JLabel("Player2");
    JLabel portLabel = new JLabel("Hosts port:");
    JLabel ipHostLabel = new JLabel("IP host: ");
    JTextField ipHostTextField = new JTextField();

    JButton startButton = new JButton("Start");
    JLabel socketAddressJLabel = new JLabel("ServerGUI not start");

    JTextField enterTheNameOfPlayer2TextField = new JTextField("enterTheNameOfPlayer2");
    JTextField portField = new JTextField("1111");

    // private static final Logger LOG = Logger.getLogger(ServerGUI.class.getName());

    public ClientGUI() {


        Mainframe.setSize(600, 400);
        Mainframe.setLayout(new GridBagLayout());

        Mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Mainframe.setLocationRelativeTo(null);
        Mainframe.setResizable(false);

        Mainframe.add(player1Label, new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));

        Mainframe.add(enterTheNameOfPlayer2TextField, new GridBagConstraints(1, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));

        Mainframe.add(portLabel, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));

        Mainframe.add(portField, new GridBagConstraints(1, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        Mainframe.add(ipHostLabel, new GridBagConstraints(0, 3, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));

        Mainframe.add(ipHostTextField, new GridBagConstraints(1, 3, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));

        Mainframe.add(startButton, new GridBagConstraints(0, 4, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        Mainframe.add(socketAddressJLabel, new GridBagConstraints(1, 4, 2, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));

        Mainframe.setVisible(true);
        Mainframe.pack();

        startButton.addActionListener((ActionEvent e) -> {
            Mainframe.setVisible(false);
            StartServerThreadGUI startServerThreadGUI = new StartServerThreadGUI();


        });

        startButton.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ENTER:

                        Mainframe.setVisible(false);
                        StartServerThreadGUI startServerThreadGUI = new StartServerThreadGUI();

                        break;

                }

            }

        });


    }


    private void Client() throws Exception {
        ClientGUI clientGUI = new ClientGUI();


        System.out.println("IP host");

        Integer portInt = new Integer(clientGUI.portField.getText());
        String ip = clientGUI.ipHostTextField.getText();

        try (Socket socket = new Socket(ip, portInt)) {


            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(5);
            outputStream.flush();
            InputStream inputStream = socket.getInputStream();
            int response = inputStream.read();

            System.out.println(response);
        }

    }
}
