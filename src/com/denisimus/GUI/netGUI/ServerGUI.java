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

public class ServerGUI extends JFrame {

    JFrame Mainframe = new JFrame("ServerXO");

    JLabel player1Label = new JLabel("Player1");
    JLabel portLabel = new JLabel("Hosts port:");

    JButton startServerButton = new JButton("Start server");
    JLabel socketAddressJLabel = new JLabel("ServerGUI not start");

    JTextField enterTheNameOfPlayer1TextField = new JTextField("enterTheNameOfPlayer1");
    JTextField portField = new JTextField("1111");

    private static final Logger LOG = Logger.getLogger(ServerGUI.class.getName());

    public ServerGUI() {


        Mainframe.setSize(600, 400);
        Mainframe.setLayout(new GridBagLayout());

        Mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Mainframe.setLocationRelativeTo(null);
        Mainframe.setResizable(false);

        Mainframe.add(player1Label, new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));

        Mainframe.add(enterTheNameOfPlayer1TextField, new GridBagConstraints(1, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));

        Mainframe.add(portLabel, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));

        Mainframe.add(portField, new GridBagConstraints(1, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));

        Mainframe.add(startServerButton, new GridBagConstraints(0, 3, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        Mainframe.add(socketAddressJLabel, new GridBagConstraints(1, 3, 2, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));

        Mainframe.setVisible(true);
        Mainframe.pack();

        startServerButton.addActionListener((ActionEvent e) -> {
            // Mainframe.setVisible(false);
            try {
                startServer();
            } catch (Exception e1) {
                LOG.info("Exception: " + e1);

                e1.printStackTrace();
            }


        });

        portField.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ENTER:

                        try {
                            startServer();
                        } catch (Exception e1) {
                            LOG.info("Exception: " + e1);

                            e1.printStackTrace();
                        }


                        break;

                }

            }

        });


    }


//    private void startServer() throws Exception {
////        ServerGUI serverGUI = new ServerGUI();
//
//
//        Integer portInt = new Integer(portField.getText());
//        try (ServerSocket serverSocket = new ServerSocket(portInt, 0, InetAddress.getLocalHost())) {
//            String address = serverSocket.getInetAddress().getHostAddress();
//
//
//            socketAddressJLabel.setText("ServerGUI start, host address: " + address);
//
//
//            LOG.info("ServerGUI start, local socket address: " + serverSocket.getLocalSocketAddress());
//            System.out.println(enterTheNameOfPlayer1TextField.getText());
//
//
//            startServerButton.setText("Exit");
//            startServerButton.addActionListener((ActionEvent e) -> {
//                System.exit(0);
//            });
//
//
//            Mainframe.pack();
//
//
//            while (true) {
//
//                //TODO
//                try (Socket socket = serverSocket.accept()) {
//
//
//                    serverClient(socket);
//
//                }
//            }
//
//        }
//    }


    private void startServer() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                LOG.info("Tread startServer run");


                Integer portInt = new Integer(portField.getText());
                try {
                    try (ServerSocket serverSocket = new ServerSocket(portInt, 0, InetAddress.getLocalHost())) {
                        String IP = serverSocket.getInetAddress().getHostAddress();
                        int port = serverSocket.getLocalPort();


                        socketAddressJLabel.setText("ServerGUI start, host IP: " + IP + " , port: " + port);


                        LOG.info("ServerGUI start, local socket address: " + serverSocket.getLocalSocketAddress());
                        System.out.println(enterTheNameOfPlayer1TextField.getText());


                        startServerButton.setText("Exit");
                        startServerButton.addActionListener((ActionEvent e) -> {
                            System.exit(0);
                        });


                        Mainframe.pack();


                        while (true) {

                            //TODO
                            try (Socket socket = serverSocket.accept()) {


                                serverClient(socket);

                            }
                        }

                    }
                } catch (IOException e) {
                    LOG.info("IOException: " + e);
                    e.printStackTrace();
                }
            }
        }).start();
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
