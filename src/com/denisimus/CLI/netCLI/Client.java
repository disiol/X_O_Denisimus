package com.denisimus.CLI.netCLI;

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
    private boolean game;
    String nameOfProgram = new String("ClientXO");

    private static String player1Name;
    private static String player2Name;


    private int movePlayer1;
    private int movePlayer2;


    private static final Logger LOG = Logger.getLogger(Client.class.getName());
    private String hostIP;
    private int portNamber;

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

                    OutputStream outputStream = socket.getOutputStream();
                    DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                    LOG.fine("OutputStream: " + outputStream);
                    dataOutputStream.writeUTF(player2Name);
                    LOG.fine("OutputStream: " + outputStream);
                    outputStream.flush();

                    InputStream inputStream = socket.getInputStream();
                    LOG.fine("inputStream: " + inputStream.toString());
                    DataInputStream player1NameDataInputStream = new DataInputStream(inputStream);
                    LOG.fine("player1InputStream: " + player1NameDataInputStream.toString());


                    player1Name = player1NameDataInputStream.readUTF();

                    game = xoNet.setXONet(player1Name, player2Name, "Client XO");


                    while (game) {
                        movePlayer1 = inputStream.read();
                        System.out.println(movePlayer1);
                        LOG.info("movePlayer1 " + movePlayer1);

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

