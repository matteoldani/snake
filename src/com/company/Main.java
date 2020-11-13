package com.company;

import javax.swing.*;

public class Main {

    private static Display display;
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;
    private static final String TITLE = "SNAKE!";


    public static void main(String[] args) {
        display = new Display(TITLE, WIDTH, HEIGHT);

    }
}
