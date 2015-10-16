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

    private final JButton OKButton = new JButton("OK");
    private final JLabel emptyLabel = new JLabel("");
    JFrame frame = new JFrame("Server");
    private JFormattedTextField enterTheNameOfPlayer1avTextField = new JFormattedTextField("Enter the name of player1");
    private JLabel socketAddressJLabel = new JLabel("");


    private static final Logger LOG = Logger.getLogger(Server.class.getName());

    public Server() {


        frame.setLayout(new GridLayout(0, 1));
        frame.add(enterTheNameOfPlayer1avTextField);
        frame.add(socketAddressJLabel);
        frame.add(OKButton);
        frame.add(emptyLabel);
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);


        OKButton.addActionListener((ActionEvent e) -> {
            //TODO
            try {
                startServer();
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        });

        enterTheNameOfPlayer1avTextField.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ENTER:
                        try {
                            startServer();
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        break;

                }

            }

        });


    }


    private void startServer() throws Exception {

        frame.setLayout(new GridLayout(0, 1));
        frame.add(enterTheNameOfPlayer1avTextField);
        frame.add(socketAddressJLabel);
        frame.add(OKButton);
        frame.add(emptyLabel);
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);


        try (ServerSocket serverSocket = new ServerSocket(1111, 0, InetAddress.getLocalHost())) {
            String address = serverSocket.getInetAddress().toString();
            InetAddress outputStream = serverSocket.getInetAddress();
            socketAddressJLabel.setText("Server start socket address: " + outputStream.toString());
            System.out.println(enterTheNameOfPlayer1avTextField.getText());
            System.out.println("Server start socket address: " + address);

            while (true) {
                try (Socket socket = serverSocket.accept()) {
                    serverClient(socket);

                }
            }

        }
    }


    private static void serverClient(Socket socket) throws IOException {
        LOG.info("Serving client " + socket.getInetAddress());

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

    private void visiblePlayerSvPlayer() {
        //TODO
//        String player1 = enterTheNameOfPlayer1avTextField.getText();
//        String player2 = enterTheNameOfPlayer2TextField.getText();
//
//        if (player1.equals("") && player2.equals("") || player1.equals("")
//                || player2.equals("")) {
//            frame.setVisible(true);
//            emptyLabel.setText("Names cannot be empty");
//        } else {
//
//            frame.setVisible(false);
//            XoGuiPlayerSvPlayer xoGuiPlayerSvPlayer = new XoGuiPlayerSvPlayer();
//            xoGuiPlayerSvPlayer.setGuiXO(player1, player2);
//
//        }

    }

}
