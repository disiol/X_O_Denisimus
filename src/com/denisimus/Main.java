package com.denisimus;

import com.denisimus.CLI.netCLI.ServerOrClient;
import com.denisimus.CLI.viewCLI.XOCLI;
import com.denisimus.GUI.MainGUI;

import java.io.IOException;
import java.util.Scanner;

/**
 * Author: Olenyk Denis (deoniisii@gmail.com)
 * on 10.08.15.
 * Главное меню
 * <p>
 * --CLI -консольный режим
 *
 * @version 1.1
 */

public class Main {
    public static void main(String args[]) throws IOException {

        char choice, ignore;

        XOCLI xocli = new XOCLI();


        if (args.length == 0) {
            MainGUI mainGUI = new MainGUI();


        }else if (args[0].equals("--CLI") == true) {
            //меню
            do {
                System.out.println("select please the game mode:");
                System.out.println("1. Player SV  player");
                System.out.println("2. Player SV  PC");
                System.out.println("3. Game on a network");
                System.out.println("4. Table of records");
                System.out.println("q - exit");


                //програма получает данные о выборе пользовотеля
                choice = (char) System.in.read();

                if (choice > '4') {
                    if (choice != 'q') {
                        System.out.println("Selection not found.\n");
                    }
                }
                if (choice == 'q') {
                    break;
                }

                do {
                    ignore = (char) System.in.read();

                } while (ignore != '\n');
            } while (choice < '1' | choice > '4');
            System.out.println('\n');


            switch (choice) {
                case '1':
                    System.out.println("Enter a name of the first player ");
                    Scanner scanner = new Scanner(System.in);
                    String name1 = scanner.nextLine();
                    System.out.println("Enter a name of the second player ");
                    String name2 = scanner.nextLine();
                    xocli.setXOCLI(name1, name2);

                    break;
                case '2':

                    break;
                case '3':
                    ServerOrClient serverOrClient = new ServerOrClient();

                    break;
                case '4':
                    break;


            }


        }


    }


}

