package com.denisimus.GUI.netGUI;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * Author: Olenyk Denis (deoniisii@gmail.com)
 * on 21.10.15.
 */

public class StartServer extends Thread {

    private static final Logger LOG = Logger.getLogger(Server.class.getName());


    // Конструктор
    StartServer() {
        // Создаём новый второй поток
        super("Start Server");
        LOG.info("The second thread is created: " + this);
        start(); // Запускаем поток
    }

    // Точка входа второго потока
    public void run() {
        try {
            startServer2();
        } catch (InterruptedException e) {
            LOG.info("The second thread is interrupted");
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOG.info("The second thread is complete");
    }


    private void startServer2() throws Exception {
        Server server = new Server();


        Integer portInt = new Integer(server.portField.getText());
        try (ServerSocket serverSocket = new ServerSocket(portInt, 0, InetAddress.getLocalHost())) {
            String address = serverSocket.getInetAddress().getHostAddress();


//TODO
            System.out.println(server.enterTheNameOfPlayer1avTextField.getText());


            server.socketAddressJLabel.setText("Server start, host address: " + address);


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

    private void visiblePlayerSvPlayer() {
        //TODO
//        String player1 = enterTheNameOfPlayer1avTextField.getText();
//        String player2 = enterTheNameOfPlayer2TextField.getText();
//
//        if (player1.equals("") && player2.equals("") || player1.equals("")
//                || player2.equals("")) {
//            Mainframe.setVisible(true);
//            emptyLabel.setText("Names cannot be empty");
//        } else {
//
//            Mainframe.setVisible(false);
//            XoGuiPlayerSvPlayer xoGuiPlayerSvPlayer = new XoGuiPlayerSvPlayer();
//            xoGuiPlayerSvPlayer.setGuiXO(player1, player2);
//
//        }

    }

}
