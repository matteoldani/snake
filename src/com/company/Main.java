package com.company;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame{

    private static Display display;
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;
    private static final String TITLE = "SNAKE!";

    public Main(){

        display = new Display(TITLE, WIDTH, HEIGHT);
        add(display);

        setSize(WIDTH, HEIGHT);
        setTitle(TITLE);

        setBackground(new Color(0,0,0));
        setResizable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setVisible(true);
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
