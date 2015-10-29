package com.denisimus.CLI.netCLI;

import javax.swing.*;
import java.io.IOException;
import java.util.Scanner;


/**
 * Author: Olenyk Denis (deoniisii@gmail.com)
 * on 12.10.15.
 */

public class ServerOrClient {

    char choice, ignore;

    public ServerOrClient() throws IOException {

        //меню
        do {
            System.out.println("Cheese please ho ara you server or client:");
            System.out.println("1. Server");
            System.out.println("2. Client");
            System.out.println("q - exit");


            //програма получает данные о выборе пользовотеля
            choice = (char) System.in.read();

            if (choice > '2') {
                if (choice != 'q') {
                    System.out.println("Selection not found.\n");
                }
            }
            if (choice == 'q') {
                System.exit(0);
            }

            do {
                ignore = (char) System.in.read();

            } while (ignore != '\n');


        }while (choice < '1' | choice > '2') ;
            System.out.println('\n');


            switch (choice) {
                case '1':
                    Server server = new Server();

                    break;
                case '2':
                    Client client = new Client();

                    break;


            }
        }

    }

