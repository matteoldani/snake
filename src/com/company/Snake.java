package com.company;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Snake implements KeyListener{

    private int lunghezza = 4;
    private Direzioni direzione = Direzioni.STOP;
    public ArrayList<Point> corpo = new ArrayList<Point>();

    public void mossa(){

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode){
            case KeyEvent.VK_LEFT:
                direzione = Direzioni.LEFT;
                break;
            case KeyEvent.VK_RIGHT:
                direzione = Direzioni.RIGHT;
                break;
            case KeyEvent.VK_UP:
                direzione = Direzioni.UP;
                break;
            case KeyEvent.VK_DOWN:
                direzione = Direzioni.DOWN;
                break;
            default:
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public Direzioni getDirezione() {
        return direzione;
    }
}
