package com.denisimus.CLI.netCLI;

import com.denisimus.CLI.modelCLI.Game;
import com.denisimus.CLI.netCLI.netViewCLI.XONet;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Author: Olenyk Denis (deoniisii@gmail.com)
 * on 14.10.15.
 */

public class Server {


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
    private boolean serverCanGo = true;
    private boolean serverDataReady = true;
    private boolean lucForWiner;


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

        LOG.info("Thread serverClient start ");
        LOG.info("Serving client " + socket.getInetAddress());
        XONet xoNet = new XONet();
        ConsoleViewNet consoleViewNet = new ConsoleViewNet();


//TODO


        try {
            fromServerToClient = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            LOG.info("IOException fromServerToClient " + e);
            e.printStackTrace();
        }
        try {
            toServerFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            LOG.info("IOException toServerFromClient " + e);
        }

        String player2Name = null;
        try {
            player2Name = toServerFromClient.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            LOG.info("IOException player2Name " + e);

        }
//        if (player2Name == null) {
//            break;
//        }
        LOG.info("player2Name " + player2Name);
        fromServerToClient.println(player1Name);
        fromServerToClient.flush();
        game = xoNet.playersNamesAndFigure(player1Name, player2Name, "Client XO");
        consoleViewNet.show(game);


        String inputLine;
        Point outputLine;
        while (true) {
            try {

                while (true) {


                    if (serverDataReady) {

                        System.out.println("Server: starting transmission...");

                        if (serverCanGo) {
                            move = consoleViewNet.move(game.getFiled(), game.getPlayers(), player1);
                            consoleViewNet.show(game);
                            System.out.println("Waiting for move of client");
                            lucForWiner = consoleViewNet.lucForWiner(game, game.getPlayers());
                            serverCanGo = false;
                        }

//                    outputLine = outputLine.concat(" ");
//
//                    outputLine = outputLine.concat(String.valueOf(horStep));
                        fromServerToClient.println(move);
                        LOG.info("Server set to client: " + move);


                        serverDataReady = false;


                        break;
                    } else {

                        try {
                            Thread.sleep(5);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }


                while ((inputLine = toServerFromClient.readLine()) != null) {


                    LOG.info("Client say: " + inputLine);

                    char[] point = inputLine.toCharArray();
                    for (char i : point){

                        System.out.print(i);
                    }

                    System.out.println(point.length);

                    consoleViewNet.moveAzerPlayer(game.getFiled(), new Point(), game.getPlayers(), player2);


                    consoleViewNet.show(game);


                    serverCanGo = true;
                    serverDataReady = true;

                    break;
                }


            } catch (IOException e) {
                e.printStackTrace();
                LOG.info("inputLine: " + e);
            }


        }


        //game = xoNet.setXONet(player1Name, player2Name, "Server XO");


//        if (player1Name != null && player2Name != null){
//
//            OutputStream outputStream = socket.getOutputStream();
//
//        }


//        while (serverMove) {
//            System.out.println();
//            move = consoleViewNet.move(game.getFiled(), game.getPlayers(), player1);
//            fromServerToClient.println(move);
//            consoleViewNet.show(game);
//            System.out.println();
//            consoleViewNet.lucForWiner(game, game.getPlayers());
//            serverMove = false;
//            clientMove = true;
//            fromServerToClient.println(clientMove);
//
//
//
//            break;
//        }

//        outputStream.close();
//        dataInputStream.close();
//        socket.close();


    }


//


}
