package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Gioco extends JPanel implements Runnable, KeyListener {

    private Campo campo;
    private Snake snake;
    private Thread thread;


    private Image testa;
    private Image corpo;
    private Image cibo;
    private Image nero;

    private boolean inGioco = true;

    public Gioco(Campo campo, Snake snake){
        this.campo = campo;
        this.snake = snake;


        setBackground(Color.BLACK);
        setVisible(true);
        setFocusable(true);
        requestFocus();


        /**
         * carico le immagini che andrò a disegnare
         */
        testa = loadImage("img/snake.png");
        corpo = loadImage("img/corpo.png");
        cibo = loadImage("img/hamburger.png");
        nero = loadImage("img/palette.png");

        /**
         * la prima volta che istanzio il gioco inserisco lo snake e creo un cibo
         */
        aggiornaCampo();
        campo.creaCibo();


    }

    private Image loadImage(String path){
        return new ImageIcon(path).getImage();
    }


    public void Start(){
        thread = new Thread(this);
        thread.start();

    }

    @Override
    public void run() {

        while(inGioco){
            doMossa();
            aggiornaCampo();
            repaint();

            try {
                thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int i = 0; i<campo.getWidth(); i++){
            for (int j = 0; j<campo.getHeight(); j++){

                if(campo.getElemento(i, j) == Elementi.TESTA ){
                    g.drawImage(testa, i*15, (j*15)+15, this);
                }
                if(campo.getElemento(i, j) == Elementi.CORPO ){
                    g.drawImage(corpo, i*15, (j*15)+15, this);
                }
                if(campo.getElemento(i, j) == Elementi.CIBO ){
                    g.drawImage(cibo, i*15, (j*15)+15, this);
                }
                if(campo.getElemento(i, j) == Elementi.VUOTO ){
                    g.drawImage(nero, i*15, (j*15)+15, this);
                }

            }
        }
    }

    /**
     * aggiorna la posiziione dello snake all'interno del campo
     */
    private void aggiornaCampo() {
        Point p;
        campo.resetCampo();

        for (int i=0; i<snake.getLunghezza(); i++) {
            p = snake.getElementById(i);
            if(i==0){
                campo.setElemento(p.x, p.y, Elementi.TESTA);
            }else{
                campo.setElemento(p.x, p.y, Elementi.CORPO);
            }
        }

        campo.setElemento(campo.getCiboX(), campo.getCiboY(), Elementi.CIBO);
    }

    /**
     * controlla ed esegue che la mossa fatta dal giocatore sia applicabile, in quel caso muove lo snake
     * e lo accresce se si è mosso dentro una casella con del cibo
     *
     */
    private void doMossa(){
        Point testa = snake.getElementById(0);

        switch (snake.getDirezione()){
            case LEFT:
                testa.x--;
                /**
                 * controllo che non sia andato fuori dal campo
                 */
                if(testa.x < 0){
                    testa.x = campo.getWidth() - 1;
                }
                /**
                 * controllo in cosa dovrebbe andare a finire
                 */
                if(campo.getElemento(testa.x, testa.y).equals(Elementi.VUOTO)){
                    snake.mossa(testa.x, testa.y);
                }else {
                    if(campo.getElemento(testa.x, testa.y).equals(Elementi.CIBO) ){
                        snake.cresci(testa.x, testa.y);
                        campo.aggiornaCibo(testa.x, testa.y);

                    }else{
                      gameOver();  
                    }
            }
                break;

            case RIGHT:
                testa.x++;

                if(testa.x >= campo.getWidth()) {
                    testa.x = 0;
                }

                if(campo.getElemento(testa.x, testa.y).equals(Elementi.VUOTO)){
                    snake.mossa(testa.x, testa.y);
                }else {
                    if(campo.getElemento(testa.x, testa.y).equals(Elementi.CIBO) ){
                        snake.cresci(testa.x, testa.y);
                        campo.aggiornaCibo(testa.x, testa.y);

                    }else{
                        gameOver();
                    }
                }
                break;

            case UP:
                testa.y--;

                if(testa.y <  0){
                    testa.y = campo.getHeight() - 1;
                }

                if(campo.getElemento(testa.x, testa.y).equals(Elementi.VUOTO)){
                    snake.mossa(testa.x, testa.y);
                }else {
                    if(campo.getElemento(testa.x, testa.y).equals(Elementi.CIBO) ){
                        snake.cresci(testa.x, testa.y);
                        campo.aggiornaCibo(testa.x, testa.y);

                    }else{
                        gameOver();
                    }
                }
                break;
            case DOWN:
                testa.y++;

                if(testa.y >= campo.getHeight()){
                    testa.y = 0;
                }

                if(campo.getElemento(testa.x, testa.y).equals(Elementi.VUOTO)){
                    snake.mossa(testa.x, testa.y);
                }else {
                    if(campo.getElemento(testa.x, testa.y).equals(Elementi.CIBO) ){
                        snake.cresci(testa.x, testa.y);
                        campo.aggiornaCibo(testa.x, testa.y);

                    }else{
                        gameOver();
                    }
                }
                break;

            default:
                break;
        }


    }

    private void gameOver() {
        inGioco = false;
        thread.interrupt();
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("dsioprv");
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
