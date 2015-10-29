package com.denisimus.CLI.netCLI;

import com.denisimus.CLI.netCLI.netViewCLI.XONet;

import javax.swing.*;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Author: Olenyk Denis (deoniisii@gmail.com)
 * on 14.10.15.
 */

public class Server extends JFrame {


    String programTitle = new String("ServerXO");

    String player1Label = new String("Player1");
    String portLabel = new String("Hosts port:");


    String socketAddressJLabel = new String("Server start");

    String enterTheNameOfPlayer1 = new String("enterTheNameOfPlayer1");
    int portField = 1111;

    private static final Logger LOG = Logger.getLogger(Server.class.getName());


    private static String player1Name;
    private int serverSet;
    private int clientSet;
    private boolean game;


    public Server() throws IOException {
        System.out.println(programTitle);
        Scanner scanner = new Scanner(System.in);
        System.out.println(enterTheNameOfPlayer1);
        enterTheNameOfPlayer1 = scanner.nextLine();
        System.out.println(portLabel);
        portField = scanner.nextInt();
        try {
            startServer();
        } catch (IOException e) {
            e.printStackTrace();
            LOG.info("e.printStackTrace: " + e);
        }

    }

    synchronized private void startServer() throws IOException {
        new Thread(() -> {
            LOG.info("Tread startServer run");


            try {
                try (ServerSocket serverSocket = new ServerSocket(portField, 0, InetAddress.getLocalHost())) {
                    String IP = serverSocket.getInetAddress().getHostAddress();
                    int port = serverSocket.getLocalPort();


                    socketAddressJLabel = "ServerGUI start, host IP: " + IP + " , port: " + port +
                            " , waiting of connection with the client ";

                    System.out.println(socketAddressJLabel);


                    LOG.info("ServerGUI start, local socket address: " + serverSocket.getLocalSocketAddress());

                    player1Name = enterTheNameOfPlayer1;


                    while (true) {

                        try (Socket socket = serverSocket.accept()) {


                            serverClient(socket);

                        }
                    }

                }
            } catch (IOException e) {
                LOG.info("IOException: " + e);
                e.printStackTrace();
            }
        }).start();
    }


    synchronized private void serverClient(Socket socket) throws IOException {
        LOG.info("Serving client " + socket.getInetAddress());
        XONet xoNet = new XONet();

//TODO


        InputStream inputStream = socket.getInputStream();
        DataInputStream dataInputStream = new DataInputStream(inputStream);

        OutputStream outputStream = socket.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

        String player2Name = dataInputStream.readUTF();
//        if (player2Name == null) {
//            break;
//        }
        LOG.info("player2Name " + player2Name);
        dataOutputStream.writeUTF(player1Name);
        dataOutputStream.flush();
        game = xoNet.setXONet(player1Name, player2Name, "Client XO");



        //game = xoNet.setXONet(player1Name, player2Name, "Server XO");


//        if (player1Name != null && player2Name != null){
//
//            OutputStream outputStream = socket.getOutputStream();
//
//        }


        while (game) {

            game = xoNet.setXONet(player1Name, player2Name, "Server XO");
            // lookForWinner = lookForWinner(gameXO, players, clientSet);
            LOG.info("clientSet: " + clientSet);

            serverSet = 12;
            dataOutputStream.write(serverSet);
            outputStream.flush();
            LOG.info("serverSet: " + serverSet);

        }

        outputStream.close();
        dataInputStream.close();
        socket.close();


    }


//


}
