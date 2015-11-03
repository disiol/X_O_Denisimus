package com.denisimus.CLI.netCLI;

import com.denisimus.CLI.modelCLI.Game;
import com.denisimus.CLI.netCLI.netViewCLI.XONet;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
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


    private static final Logger LOG = Logger.getLogger(Client.class.getName());
    private String hostIP;
    private int portNamber;
    private PrintWriter fromClientToServer;
    private BufferedReader toClientFromServer;

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

                    InputStream inputStream = socket.getInputStream();
                    LOG.fine("inputStream: " + inputStream.toString());
                    DataInputStream player1NameDataInputStream = new DataInputStream(inputStream);
                    LOG.fine("player1InputStream: " + player1NameDataInputStream.toString());


                    player1Name = toClientFromServer.readLine();

                    game = xoNet.playersNamesAndFigure(player1Name, player2Name, "Client XO");


                    consoleViewNet.show(game);
                    while (consoleViewNet.lucForWiner(game,game.getPlayers())) {
                        System.out.println();
                        consoleViewNet.show(game);
                        System.out.println();
                        break;
                    }

                    //TODO
                }
            } catch (IOException e) {
                e.printStackTrace();
                LOG.info("e.printStackTrace:" + e);
            }


        }).start();
    }

}

