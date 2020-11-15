package com.company;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Snake implements KeyListener{

    private int lunghezza = 4;
    private Direzioni direzione = Direzioni.UP;
    private ArrayList<Point> corpo = new ArrayList<Point>();

    public Snake(){
        corpo.add(new Point(Campo.getWidth()/2, Campo.getHeight()/2));
        corpo.add(new Point(Campo.getWidth()/2 - 1, Campo.getHeight()/2));
        corpo.add(new Point(Campo.getWidth()/2 - 2, Campo.getHeight()/2));
        corpo.add(new Point(Campo.getWidth()/2 - 3, Campo.getHeight()/2));
    }

    /**
     * aggiorna la lista con il corpo
     */
    public void mossa(int newX, int newY){
        corpo.remove(lunghezza - 1);
        corpo.add(0, new Point(newX, newY));
    }


    /**
     * aggiunge un elemento in testa all'arraylistin poszione x, y
     */
    public void cresci(int x, int y) {
        corpo.add(0, new Point(x, y));
        lunghezza++;
    }


    /**
     * restituisce l'elemento della lista in  posizione id
     * @param id
     * @return
     */
    public Point getElementById(int id){
        Point p = new Point();
        p.x = corpo.get(id).x;
        p.y = corpo.get(id).y;
        return p;
    }

    public int getLunghezza(){
        return lunghezza;
    }

    public Direzioni getDirezione() {
        return direzione;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        System.out.println("ho cliccato");

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
}
