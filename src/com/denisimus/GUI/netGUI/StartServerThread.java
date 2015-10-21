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

public class StartServerThread extends Thread {

    private static final Logger LOG = Logger.getLogger(Server.class.getName());


    // Конструктор
    StartServerThread() {
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
            LOG.info("InterruptedException: " + e);
            LOG.info("The second thread is interrupted");
        } catch (Exception e) {
            LOG.info("Exception: " + e);

            e.printStackTrace();
        }
        LOG.info("The second thread is complete");
    }


    private void startServer2() throws Exception {
        Server server = new Server();


        Integer portInt = new Integer(server.portField.getText());
        try (ServerSocket serverSocket = new ServerSocket(portInt, 0, InetAddress.getLocalHost())) {
            String address = serverSocket.getInetAddress().getHostAddress();


            System.out.println(server.enterTheNameOfPlayer1avTextField.getText());


            server.socketAddressJLabel.setText("Server start, host address: " + address);


            LOG.info("Server start, local socket address: " + serverSocket.getLocalSocketAddress());


            server.startServerButton.setText("Exit");
            server.startServerButton.addActionListener((ActionEvent e) -> {
                System.exit(0);
            });


            server.Mainframe.pack();


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
