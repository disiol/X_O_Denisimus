package com.denisimus.CLI.netCLI;

import com.denisimus.CLI.modelCLI.Game;
import com.denisimus.CLI.netCLI.netViewCLI.XONet;

import javax.swing.*;
import java.awt.*;
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
    private static int player1 = 0;
    private static int player2 = 1;
    private int serverSet;
    private int clientSet;
    private Game game;
    private Point move;
    private PrintWriter fromServerToClient;
    private BufferedReader toServerFromClient;


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
        ConsoleViewNet consoleViewNet = new ConsoleViewNet();


//TODO


        fromServerToClient = new PrintWriter(socket.getOutputStream(), true);
        toServerFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String player2Name = toServerFromClient.readLine();
//        if (player2Name == null) {
//            break;
//        }
        LOG.info("player2Name " + player2Name);
        fromServerToClient.println(player1Name);
        fromServerToClient.flush();
        game = xoNet.playersNamesAndFigure(player1Name, player2Name, "Client XO");
        consoleViewNet.show(game);


        //game = xoNet.setXONet(player1Name, player2Name, "Server XO");


//        if (player1Name != null && player2Name != null){
//
//            OutputStream outputStream = socket.getOutputStream();
//
//        }


        consoleViewNet.show(game);
        while (consoleViewNet.lucForWiner(game, game.getPlayers())) {
            System.out.println();
            move = consoleViewNet.move(game.getFiled(), game.getPlayers(), player1);

            consoleViewNet.show(game);
            System.out.println();
            break;
        }

//        outputStream.close();
//        dataInputStream.close();
//        socket.close();


    }


//


}
