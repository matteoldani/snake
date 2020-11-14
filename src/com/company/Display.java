package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class Display extends JPanel {



    public Display(String title, int width, int height){

        setBackground(Color.BLACK);
        setVisible(true);
        this.getGraphics();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    /**
     * utile ma da modificare
     * @param g

    private void doDrawing(Graphics g) {

        if (inGame) {

            g.drawImage(apple, apple_x, apple_y, this);

            for (int z = 0; z < dots; z++) {
                if (z == 0) {
                    g.drawImage(head, x[z], y[z], this);
                } else {
                    g.drawImage(ball, x[z], y[z], this);
                }
            }

            Toolkit.getDefaultToolkit().sync();

        } else {

            gameOver(g);
        }
    }
     */
    public void drawCampo(Graphics g, Campo campo){

    }



}
