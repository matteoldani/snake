package com.company;

import java.util.Random;

public class Campo {

    private static final int WIDTH = 30;
    private static final int HEIGHT = 30;

    private Elementi campo[][];

    /**
     * innizializzo il campo a vuoto
     */

    public Campo(){
        campo = new Elementi[HEIGHT][WIDTH];
        for(int i=0; i<HEIGHT; i++){
            for (int j=0; j<WIDTH;j++){
                campo[i][j] = Elementi.VUOTO;
            }
        }
    }

    /**
     * crea e inserisce un nuovo cibo in posizione valida
     */
    public void creaCibo(){
        Random cas = new Random();
        int x;
        int y;
        Elementi el;

        do{

            x = cas.nextInt(WIDTH -1);
            y = cas.nextInt(HEIGHT -1);
            el = campo[y][x];

        }while (el != Elementi.VUOTO);

        campo[y][x] = Elementi.CIBO;
    }

    /**
     * elimina il vecchio cibo e ne crea uno nuovo
     */
    public void aggiornaCibo(int x, int y) {

        creaCibo();
        campo[y][x] = Elementi.VUOTO;

    }

    public void resetCampo(){
        for(int i=0; i<HEIGHT; i++){
            for (int j=0; j<WIDTH;j++){
                campo[i][j] = Elementi.VUOTO;
            }
        }
    }

    public Elementi getElemento(int x, int y){
        return campo[y][x];
    }

    public static int getWidth(){
        return WIDTH;
    }

    public static int getHeight(){
        return HEIGHT;
    }

    public void setElemento(int x, int y, Elementi el){
        campo[y][x] = el;
    }


}
