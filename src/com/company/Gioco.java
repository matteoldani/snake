package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Gioco extends JPanel implements Runnable, KeyListener {

    private Campo campo;
    private Snake snake;
    private Thread thread;


    private Image testaDown;
    private Image testaUp;
    private Image testaLeft;
    private Image testaRight;
    private Image corpo;
    private Image cibo;
    private Image nero;
    private Image scrittaSnake;
    private Image scrittaEnter;
    private Image scrittaGameOver;
    private Image score;

    private boolean inGioco = true;
    private Stato statoGioco = Stato.MENU;

    public Gioco(Campo campo, Snake snake){
        this.campo = campo;
        this.snake = snake;


        setBackground(Color.black);
        setVisible(true);
        setFocusable(true);
        requestFocus();


        /**
         * carico le immagini che andrò a disegnare
         */
        testaDown = loadImage("img/snakeDown.png");
        testaUp = loadImage("img/snakeUp.png");
        testaLeft = loadImage("img/snakeLeft.png");
        testaRight = loadImage("img/snakeRight.png");
        corpo = loadImage("img/corpo.png");
        cibo = loadImage("img/hamburger.png");
        nero = loadImage("img/palette.png");
        scrittaEnter = loadImage("img/scrittaEnter.png");
        scrittaSnake = loadImage("img/scrittaSnake.png");
        scrittaGameOver = loadImage("img/scrittaGameOver.png");
        score = loadImage("img/score.png");

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
    }

    @Override
    public void run() {

        while(inGioco){
            doMossa();
            aggiornaCampo();
            snake.resetMossaEffettuata();
            repaint();

            try {
                if(snake.getLunghezza()>=87){
                    thread.sleep(75);
                }else{
                    thread.sleep(200 - snake.getLunghezza()*3);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * In base allo stato del gioco richiama la funzione che disegna lo stato corretto
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(statoGioco == Stato.GIOCO){
            drawGioco(g);
        }else{
            if(statoGioco == Stato.MENU){
                drawMenu(g);
            }else{
                drawGameOver(g);
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
        statoGioco = Stato.GAMEOVER;
        repaint();

    }

    private void drawMenu(Graphics g){
        g.drawImage(scrittaSnake, 87,150, this);
        g.drawImage(scrittaEnter, 137,300,this);
    }

    private void drawGameOver(Graphics g){
        String punteggio = Integer.toString(snake.getLunghezza()-4);
        JLabel punti = new JLabel(punteggio);
        g.drawImage(scrittaGameOver, 37,150, this);
        g.drawImage(score, 137, 300, this);
        punti.setForeground(Color.green);
        punti.setVisible(true);
        punti.setPreferredSize(new Dimension(50, 50));
        add(punti);

        g.drawImage(scrittaEnter, 137,450,this);
    }

    private void drawGioco(Graphics g){
        for(int i = 0; i<campo.getWidth(); i++){
            for (int j = 0; j<campo.getHeight(); j++){

                if(campo.getElemento(i, j) == Elementi.TESTA ){
                    if(snake.getDirezione() == Direzioni.DOWN){
                        g.drawImage(testaDown, i*15, (j*15), this);
                    }
                    if(snake.getDirezione() == Direzioni.UP){
                        g.drawImage(testaUp, i*15, (j*15), this);
                    }
                    if(snake.getDirezione() == Direzioni.LEFT){
                        g.drawImage(testaLeft, i*15, (j*15), this);
                    }
                    if(snake.getDirezione() == Direzioni.RIGHT || snake.getDirezione() == Direzioni.STOP){
                        g.drawImage(testaRight, i*15, (j*15), this);
                    }


                }
                if(campo.getElemento(i, j) == Elementi.CORPO ){
                    g.drawImage(corpo, i*15, (j*15), this);
                }
                if(campo.getElemento(i, j) == Elementi.CIBO ){
                    g.drawImage(cibo, i*15, (j*15), this);
                }
                if(campo.getElemento(i, j) == Elementi.VUOTO ){
                    g.drawImage(nero, i*15, (j*15), this);
                }

            }
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if(keyCode == KeyEvent.VK_ENTER){
            if(statoGioco == Stato.MENU){
                statoGioco = Stato.GIOCO;
                thread.start();
            }else{
                if(statoGioco == Stato.GAMEOVER){
                    statoGioco = Stato.GIOCO;
                    rePlay();
                }
            }

        }

    }

    private void rePlay() {
       snake.resetSnake();
       statoGioco = Stato.GIOCO;
       Start();
       inGioco = true;
       thread.start();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
