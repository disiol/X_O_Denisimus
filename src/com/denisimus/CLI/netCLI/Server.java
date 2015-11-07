package com.denisimus.CLI.netCLI;

import com.denisimus.CLI.modelCLI.Game;
import com.denisimus.CLI.netCLI.netViewCLI.XONet;

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
    XONet xoNet = new XONet();
    ConsoleViewNet consoleViewNet = new ConsoleViewNet();


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


                            try {
                                serverClient(socket);
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }

                        }
                    }

                }
            } catch (IOException e) {
                LOG.info("IOException: " + e);
                e.printStackTrace();
            }
        }).start();
    }


    synchronized private void serverClient(Socket socket) throws IOException, ClassNotFoundException {

        LOG.info("Thread serverClient start ");
        LOG.info("Serving client " + socket.getInetAddress());


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
        String outputLine = null;
        while (true) {
            try {

                while (true) {


                    if (serverDataReady) {

                        System.out.println("Server: starting transmission...");

                        FileOutputStream moveFile = new FileOutputStream("move.out");
                        ObjectOutputStream moveSerial = new ObjectOutputStream(moveFile);


                        if (serverCanGo) {

                            //TODO

                            move = consoleViewNet.move(game.getFiled(), game.getPlayers(), player1);


                            lucForWiner = consoleViewNet.lucForWiner(game, game.getPlayers());

                            serverCanGo = false;


                        }

                        LOG.info("Server set to client: " + move);
                        moveSerial.writeObject(move);
                        fromServerToClient.println(moveFile);
                        moveSerial.flush();
                        moveSerial.close();

                        if (lucForWiner) {
                            consoleViewNet.show(game);
                            System.out.println("Waiting for move of client");
                            serverCanGo = false;
                        } else if (!lucForWiner) {
                            consoleViewNet.show(game);
                            System.exit(-1);
                        }

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

                    FileInputStream fis = new FileInputStream("move.out");
                    ObjectInputStream oin = new ObjectInputStream(fis);
                    Point point = (Point) oin.readObject();


                    consoleViewNet.moveAzerPlayer(game.getFiled(), point, game.getPlayers(), player2);


                    consoleViewNet.show(game);

                    if (lucForWiner) {
                        serverCanGo = true;
                        serverDataReady = true;
                    } else if (!lucForWiner) {
                        consoleViewNet.show(game);
                        System.exit(-1);
                    }

                    break;
                }


            } catch (IOException e) {
                e.printStackTrace();
                LOG.info("inputLine: " + e);
            }


        }


    }


}
