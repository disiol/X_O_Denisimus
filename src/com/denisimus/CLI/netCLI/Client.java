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

//    private void status() {
//        heder();
//        System.out.println("Port: " + portNamber);
//        System.out.println("Status server:" + statusServer);
//    }

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


                    while (true) {
                        try {

                            String str;


                            while ((str = toClientFromServer.readLine()) != null) {

                                LOG.info("toClientFromServer:" + str);
                                System.out.println("Client called from server...");


                                FileInputStream fis = new FileInputStream("move.out");
                                ObjectInputStream oin = new ObjectInputStream(fis);
                                Point point = (Point) oin.readObject();



                                //  System.out.println(str.length());


                                // ставим в полученные координаты символ крестика
                                consoleViewNet.moveAzerPlayer(game.getFiled(),point, game.getPlayers(), player1);

                                // перерисовываем доску
                                consoleViewNet.show(game);

                                // теперь мы ( клиент ) можем ходить
                                clientCanGo = true;
                                clientDataReady = true;

                                break;
                            }

                            while (true) {


                                if (clientDataReady) {

                                    FileOutputStream moveFile = new FileOutputStream("move.out");
                                    ObjectOutputStream moveSerial = new ObjectOutputStream(moveFile);


                                    if (clientCanGo) {
                                        move = consoleViewNet.move(game.getFiled(), game.getPlayers(), player2);

                                        consoleViewNet.show(game);
                                        System.out.println("Waiting for move of server");
                                        lucForWiner = consoleViewNet.lucForWiner(game, game.getPlayers());
                                        clientCanGo = false;


                                    }



                                    System.out.println("Client: ready to send data!");

//
//                                    outputLine = outputLine.concat(String.valueOf(move.x));
//                                    outputLine = outputLine.concat(" ");
//                                    outputLine = outputLine.concat(String.valueOf(move.y));
                                    LOG.info("Server set to client: " + move);

                                    moveSerial.writeObject(move);
                                    fromClientToServer.println(moveFile);
                                    moveSerial.flush();
                                    moveSerial.close();


                                    LOG.info("Client set to server: " + move);

                                    clientDataReady = false;


                                    System.out.println("Client: data transported!");
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


//                    clientMove = Boolean.parseBoolean(toClientFromServer.readLine());
//                    while (clientMove) {
//                        System.out.println();
//                        String str;
//
//
//                        while ((str = toClientFromServer.readLine()) != null) {
//
//
//                            System.out.println("Client called from server...");
//
//
//                            String[] words = str.split(" ");
//
//
//                            int x = Integer.parseInt(words[0]);
//                            int y = Integer.parseInt(words[1]);
//
//                            consoleViewNet.moveAzerPlayer(game.getFiled(),new Point(x,y), game.getPlayers(),movePlayer1);
//                            consoleViewNet.show(game);
//                            System.out.println();
//                            clientMove = false;
//                            serverMove = true;
//                            break;
//                        }

//                        break;
//                    }

                    //TODO
                }
            } catch (IOException e) {
                e.printStackTrace();
                LOG.info("e.printStackTrace:" + e);
            }


        }).start();
    }

}

