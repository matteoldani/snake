package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class Display extends JFrame {

    private JPanel pannello;


    public Display(String title, int width, int height){

        setSize(width, height);
        setTitle(title);

        pannello = new JPanel();
        pannello.setBackground(Color.BLACK);
        add(pannello);

        /*
         *  questo vederemo più avanti se ha senso
         *  add(new JPanel()).setBackground(Color.BLACK);;
         */

        setBackground(new Color(0,0,0));
        setResizable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setVisible(true);
    }



}
