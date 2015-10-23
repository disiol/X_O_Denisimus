package com.denisimus.GUI.netGUI;

import com.denisimus.CLI.modelCLI.Figure;
import com.denisimus.CLI.modelCLI.Player;
import com.denisimus.CLI.modelCLI.exeptions.AlreadyOccupantException;
import com.denisimus.CLI.modelCLI.exeptions.InvalidPointException;
import com.denisimus.GUI.controlerGUI.CurrentMoveControllerGUI;
import com.denisimus.GUI.controlerGUI.MoveControllerGUI;
import com.denisimus.GUI.controlerGUI.WinnerControllerGUI;
import com.denisimus.GUI.modelGUI.FiledGUI;
import com.denisimus.GUI.modelGUI.GameGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * Author: Olenyk Denis (deoniisii@gmail.com)
 * on 14.10.15.
 */

public class ServerGUI extends JFrame implements ActionListener {





    JFrame Mainframe = new JFrame("ServerXO");

    JLabel player1Label = new JLabel("Player1");
    JLabel portLabel = new JLabel("Hosts port:");

    JButton startServerButton = new JButton("Start server");
    JLabel socketAddressJLabel = new JLabel("ServerGUI not start");

    JTextField enterTheNameOfPlayer1TextField = new JTextField("enterTheNameOfPlayer1");
    JTextField portField = new JTextField("1111");

    private static final Logger LOG = Logger.getLogger(ServerGUI.class.getName());


    //fieldGuiXO
    private static String player1Name;
    private static String player2Name;
    private static Player[] players = new Player[2];
    private static JButton squares[];
    private static JButton newGameButton;
    private static JLabel score = new JLabel("Push the new game button");
    private static JLabel player1NameLabel = new JLabel("player1 Name");
    private static JLabel player2NameLabel = new JLabel("player2 Name");
    final GameGUI gameXO = new GameGUI(players, new FiledGUI(9), "XO");
    final FiledGUI filed = gameXO.getFiled();
    private final CurrentMoveControllerGUI currentMoveControllerGUI = new CurrentMoveControllerGUI();
    private final WinnerControllerGUI winnerControllerGUI = new WinnerControllerGUI();
    private final MoveControllerGUI moveControllerGUI = new MoveControllerGUI();

    static int noWinner = 0;
    static JLabel noWinnerJLabel = new JLabel("   No winner: " + noWinner);

    static int player1Win = 0;
    static int player2Win = 0;



    public ServerGUI() {


        Mainframe.setSize(600, 400);
        Mainframe.setLayout(new GridBagLayout());

        Mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Mainframe.setLocationRelativeTo(null);
        Mainframe.setResizable(false);

        Mainframe.add(player1Label, new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));

        Mainframe.add(enterTheNameOfPlayer1TextField, new GridBagConstraints(1, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));

        Mainframe.add(portLabel, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));

        Mainframe.add(portField, new GridBagConstraints(1, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));

        Mainframe.add(startServerButton, new GridBagConstraints(0, 3, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        Mainframe.add(socketAddressJLabel, new GridBagConstraints(1, 3, 2, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));

        Mainframe.setVisible(true);
        Mainframe.pack();

        startServerButton.addActionListener((ActionEvent e) -> {

            try {
                startServer();
            } catch (Exception e1) {
                LOG.info("Exception: " + e1);

                e1.printStackTrace();
            }


        });

        portField.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ENTER:

                        try {
                            startServer();
                        } catch (Exception e1) {
                            LOG.info("Exception: " + e1);

                            e1.printStackTrace();
                        }


                        break;

                }

            }

        });


    }


    private void startServer() throws IOException {
        new Thread(() -> {
            LOG.info("Tread startServer run");


            Integer portInt = new Integer(portField.getText());
            try {
                try (ServerSocket serverSocket = new ServerSocket(portInt, 0, InetAddress.getLocalHost())) {
                    String IP = serverSocket.getInetAddress().getHostAddress();
                    int port = serverSocket.getLocalPort();


                    socketAddressJLabel.setText("ServerGUI start, host IP: " + IP + " , port: " + port);


                    LOG.info("ServerGUI start, local socket address: " + serverSocket.getLocalSocketAddress());

                    player1Name = enterTheNameOfPlayer1TextField.getText();


                    startServerButton.setText("Exit");
                    startServerButton.addActionListener((ActionEvent e) -> System.exit(0));


                    Mainframe.pack();


                    while (true) {

                        //TODO
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


    private void serverClient(Socket socket) throws IOException {
        LOG.info("Serving client " + socket.getInetAddress());

//TODO
        InputStream inputStream = socket.getInputStream();
        DataInputStream dataInputStream = new DataInputStream(inputStream);

        OutputStream outputStream = socket.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);


        while (true) {
            player2Name = dataInputStream.readUTF();
            if (player2Name == null) {
                break;
            }
            LOG.info("player2Name " + player2Name);
            dataOutputStream.writeUTF(player1Name);
            dataOutputStream.flush();
            fieldGuiXO(player1Name, player2Name);
        }


    }


    private void fieldGuiXO(String player1, String player2) {


        final String name1 = player1;
        final String name2 = player2;

        //написать волидатор

        players[0] = new Player(name1, Figure.X);
        players[1] = new Player(name2, Figure.O);

        final GameGUI gameXO = new GameGUI(players, new FiledGUI(9), "XO");


        JFrame frame = new JFrame("Game name: " + gameXO.getName());
        final Player[] plaeyrs = gameXO.getPlayers();

        // Менеджер расположения , шрифт и цвет
        frame.setLayout(new BorderLayout());
        frame.setBackground(Color.WHITE);
        frame.setSize(530, 550);
        frame.setResizable(false);

        // Шрифт
        Font font = new Font("Monospased", Font.BOLD, 20);
        frame.setFont(font);

        // Кнопка “New Game” и слушатель действия
        player1NameLabel = new JLabel(plaeyrs[0].getName() + " : " + player1Win);
        player2NameLabel = new JLabel(plaeyrs[1].getName() + " : " + player2Win);
        newGameButton = new JButton("New game");
        newGameButton.addActionListener((ActionListener) this);

        Panel topPanel = new Panel();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(newGameButton, "North");
        topPanel.add(player1NameLabel, "West");
        topPanel.add(player2NameLabel, "East");
        topPanel.add(noWinnerJLabel, "Center");
        frame.add(topPanel, "North");
        Panel centerPanel = new Panel();
        centerPanel.setLayout(new GridLayout(3, 3));
        frame.add(centerPanel, "Center");
        score = new JLabel("Push the new game button");
        frame.add(score, "South");

        // Массив для хранения ссылок на 9 кнопок
        squares = new JButton[filed.getSize()];

        // Кнопки
        for (int i = 0; i < filed.getSize(); i++) {
            squares[i] = new JButton();
            squares[i].addActionListener((ActionListener) this);
            squares[i].setBackground(Color.green);
            centerPanel.add(squares[i]);
            squares[i].setEnabled(false);
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {


        JButton theButton = (JButton) e.getSource();
        // Кнопка New Game
        if (theButton.equals(newGameButton)) {
            for (int i = 0; i < filed.getSize(); i++) {
                squares[i].setEnabled(true);
                squares[i].setText("");
                squares[i].setBackground(Color.green);
                filed.setFigure(i, null);
                score.setText("move player : " + players[0].getName() + " figure: X");
            }


            newGameButton.setEnabled(true);
            return;
        }

        // Одна из клеток
        metka:

        for (int i = 0; i < filed.getSize(); i++) {

            if (theButton == squares[i]) {


                boolean lookForWinner = true;
                if (lookForWinner == true) {
                    final Figure currentFigure = currentMoveControllerGUI.currentMove(filed);

                    squares[i].setFont(new java.awt.Font("TimesRoman", 0, 36));
                    try {
                        moveControllerGUI.applyFigure(filed, i, currentFigure);
                    } catch (InvalidPointException | AlreadyOccupantException e1) {
                        e1.printStackTrace();
                    }
                    squares[i].setText(filed.getFigure(i).toString());


                    lookForWinner = lookForWinner(gameXO, players, i);


                } else {
                    continue metka;
                }

                if (lookForWinner == false) {
                    endTheGame();

                } else
                    break;
            }


        }


    }

    private boolean lookForWinner(GameGUI gameXO, Player[] players, int i) {
        return false;
    }

    private void endTheGame() {
    }


}
