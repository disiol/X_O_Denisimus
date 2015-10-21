package com.denisimus.GUI.netGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * Author: Olenyk Denis (deoniisii@gmail.com)
 * on 14.10.15.
 */

public class Server extends JFrame {

    JFrame Mainframe = new JFrame("ServerXO");

    JLabel player1Label = new JLabel("Player1");
    JLabel portLabel = new JLabel("Hosts port:");

    JButton OKButton = new JButton("Start server");
    JLabel socketAddressJLabel = new JLabel("Server not start");

    JTextField enterTheNameOfPlayer1avTextField = new JTextField("enterTheNameOfPlayer1avTextField");
    JTextField portField = new JTextField("1111");

    private static final Logger LOG = Logger.getLogger(Server.class.getName());

    public Server() {


        Mainframe.setSize(600, 400);
        Mainframe.setLayout(new GridBagLayout());

        Mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Mainframe.setLocationRelativeTo(null);
        Mainframe.setResizable(false);

        Mainframe.add(player1Label, new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));

        Mainframe.add(enterTheNameOfPlayer1avTextField, new GridBagConstraints(1, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));

        Mainframe.add(portLabel, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));

        Mainframe.add(portField, new GridBagConstraints(1, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));

        Mainframe.add(OKButton, new GridBagConstraints(0, 3, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        Mainframe.add(socketAddressJLabel, new GridBagConstraints(1, 3, 2, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));

        Mainframe.setVisible(true);
        Mainframe.pack();

        OKButton.addActionListener((ActionEvent e) -> {
            StartServer startServer = new StartServer();


        });

        enterTheNameOfPlayer1avTextField.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ENTER:
                        try {
                            Mainframe.setVisible(false);
                            //startServer();
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        break;

                }

            }

        });


    }


    private void startServer2() throws Exception {


        Integer portInt = new Integer(portField.getText());
        try (ServerSocket serverSocket = new ServerSocket(portInt, 0, InetAddress.getLocalHost())) {
            String address = serverSocket.getInetAddress().getHostAddress();


//TODO
            System.out.println(enterTheNameOfPlayer1avTextField.getText());


            socketAddressJLabel.setText("Server start, host address: " + address);


            LOG.info("Server start, local socket address: " + serverSocket.getLocalSocketAddress());

            while (true) {
                try (Socket socket = serverSocket.accept()) {


                    serverClient(socket);

                }
            }

        }
    }


    private static void serverClient(Socket socket) throws IOException {
        LOG.info("Serving client " + socket.getInetAddress());

//TODO
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();

        while (true) {
            int regest = inputStream.read();
            if (regest == -1) {
                break;
            }
            LOG.info("Regest " + regest);
            outputStream.write(regest * regest);
            outputStream.flush();
        }


    }


}
