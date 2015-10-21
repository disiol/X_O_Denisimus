package com.denisimus.GUI.netGUI;

import java.awt.event.ActionEvent;
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

public class StartServerThreadGUI extends Thread {

    private static final Logger LOG = Logger.getLogger(ServerGUI.class.getName());


    // Конструктор
    StartServerThreadGUI() {
        // Создаём новый второй поток
        super("Start ServerGUI");
        LOG.info("The second thread is created: " + this);
        start(); // Запускаем поток
    }

    // Точка входа второго потока
    public void run() {
        try {
            startServer2();
        } catch (InterruptedException e) {
            LOG.info("InterruptedException: " + e);
            LOG.info("The second thread is interrupted");
        } catch (Exception e) {
            LOG.info("Exception: " + e);

            e.printStackTrace();
        }
        LOG.info("The second thread is complete");
    }


    private void startServer2() throws Exception {
        ServerGUI serverGUI = new ServerGUI();


        Integer portInt = new Integer(serverGUI.portField.getText());
        try (ServerSocket serverSocket = new ServerSocket(portInt, 0, InetAddress.getLocalHost())) {
            String address = serverSocket.getInetAddress().getHostAddress();


            System.out.println(serverGUI.enterTheNameOfPlayer1TextField.getText());


            serverGUI.socketAddressJLabel.setText("ServerGUI start, host address: " + address);


            LOG.info("ServerGUI start, local socket address: " + serverSocket.getLocalSocketAddress());


            serverGUI.startServerButton.setText("Exit");
            serverGUI.startServerButton.addActionListener((ActionEvent e) -> {
                System.exit(0);
            });


            serverGUI.Mainframe.pack();


            while (true) {

                //TODO
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
