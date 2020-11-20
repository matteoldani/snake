package com.company;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame{


    private static final int WIDTH = 525;
    private static final int HEIGHT = 670;
    private static final String TITLE = "SNAKE!";


    private static Snake snake;
    private static Campo campo;
    private static Gioco gioco;


    public Main(){

        snake = new Snake();
        campo = new Campo();
        gioco = new Gioco(campo, snake);

        add(gioco);
        addKeyListener(snake);
        addKeyListener(gioco);

        setSize(WIDTH, HEIGHT);
        setTitle(TITLE);

        setBackground(new Color(0, 0,0));
        setResizable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setVisible(true);
        setFocusable(true);

        gioco.Start();
    }


    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame m = new Main();
            }
        });


    }
}
