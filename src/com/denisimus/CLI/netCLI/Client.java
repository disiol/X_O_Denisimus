package com.denisimus.CLI.netCLI;

import com.denisimus.CLI.modelCLI.Game;
import com.denisimus.CLI.netCLI.netViewCLI.XONet;

import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Author: Olenyk Denis (deoniisii@gmail.com)
 * on 10.08.15.
 */

public class Client {
    private Game game;
    String nameOfProgram = new String("ClientXO");

    private static String player1Name;
    private static String player2Name;


    private int movePlayer1;
    private int movePlayer2;

    private static int player1 = 0;
    private static int player2 = 1;

    private static final Logger LOG = Logger.getLogger(Client.class.getName());
    private String hostIP;
    private int portNamber;
    private PrintWriter fromClientToServer;
    private BufferedReader toClientFromServer;
    private boolean serverMove;
    private boolean clientMove = false;
    private boolean clientDataReady;
    private boolean clientCanGo;
    private Point move;
    private boolean lucForWiner;

    public Client() {
        heder();

        System.out.println("Enter a name of player");
        Scanner scanner = new Scanner(System.in);
        player2Name = scanner.nextLine();
        System.out.println("Enter IP of host");
        hostIP = scanner.nextLine();
        System.out.println("Enter number of port");
        portNamber = scanner.nextInt();
        //status();
        statClient();
    }

    private void heder() {
        System.out.println(nameOfProgram);

    }



    private void statClient() {
        new Thread(() -> {
            LOG.info("Tread Client run");

            try {
                try (Socket socket = new Socket(hostIP, portNamber)) {
                    LOG.info("Client start: " + socket.getLocalSocketAddress());

                    XONet xoNet = new XONet();
                    ConsoleViewNet consoleViewNet = new ConsoleViewNet();

                    fromClientToServer = new PrintWriter(socket.getOutputStream(), true);
                    toClientFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    fromClientToServer.println(player2Name);
                    LOG.fine("OutputStream: " + fromClientToServer);
                    fromClientToServer.flush();


                    player1Name = toClientFromServer.readLine();

                    game = xoNet.playersNamesAndFigure(player1Name, player2Name, "Client XO");
                    consoleViewNet.show(game);
                    System.out.println("Waiting for move of server");


                    while (true) {
                        try {

                            String str;


                            while ((str = toClientFromServer.readLine()) != null) {

                                LOG.info("toClientFromServer:" + str);
                                System.out.println("Client called from server...");


                                FileInputStream fis = new FileInputStream("move.out");
                                ObjectInputStream oin = new ObjectInputStream(fis);
                                Point point = (Point) oin.readObject();

//TODO
                                consoleViewNet.moveAzerPlayer(game.getFiled(), point, game.getPlayers(), player1);

                                lucForWiner = consoleViewNet.lucForWiner(game, game.getPlayers());


                                if (lucForWiner) {
                                    consoleViewNet.show(game);
                                    clientCanGo = true;
                                    clientDataReady = true;
                                } else if (!lucForWiner) {
                                    consoleViewNet.show(game);
                                    System.exit(-1);
                                }

                                break;
                            }

                            while (true) {


                                if (clientDataReady) {

                                    FileOutputStream moveFile = new FileOutputStream("move.out");
                                    ObjectOutputStream moveSerial = new ObjectOutputStream(moveFile);


                                    if (clientCanGo) {
                                        move = consoleViewNet.move(game.getFiled(), game.getPlayers(), player2);
                                        lucForWiner = consoleViewNet.lucForWiner(game, game.getPlayers());
                                        consoleViewNet.show(game);

                                        clientCanGo = false;


                                    }


                                    System.out.println("Client: ready to send data!");

                                    LOG.info("Server set to client: " + move);

                                    moveSerial.writeObject(move);
                                    fromClientToServer.println(moveFile);
                                    moveSerial.flush();
                                    moveSerial.close();


                                    LOG.info("Client set to server: " + move);

                                    clientDataReady = false;

                                    System.out.println("Client: data transported!");

                                    if (!lucForWiner) {
                                        consoleViewNet.show(game);
                                        System.exit(-1);
                                    } else if (lucForWiner) {
                                        System.out.println("Waiting for move of server");
                                    }

                                    break;

                                } else {
                                    // иначе - ждем, потом - все снова
                                    try {
                                        Thread.sleep(5);
                                    } catch (InterruptedException ex) {
                                        LOG.info("InterruptedException:" + ex);
                                    }
                                }
                            }


                        } /////////
                        catch (IOException ex) {
                            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                            System.exit(-1);
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }


                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
                LOG.info("e.printStackTrace:" + e);
            }


        }).start();
    }

}

