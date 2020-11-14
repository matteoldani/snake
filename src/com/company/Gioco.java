package com.company;

import java.awt.Point;

public class Gioco implements Runnable{

    private Campo campo;
    private Snake snake;
    private Thread thread;
    private Display display;

    public Gioco(Campo campo, Snake snake, Display display){
        this.campo = campo;
        this.snake = snake;
        this.display = display;

        /**
         * la prima volta che istanzio il gioco inserisco lo snake e creo un cibo
         */
        aggiornaCampo();
        campo.creaCibo();
    }

    public void Start(){
        thread = new Thread();
        thread.run();
    }

    @Override
    public void run() {
       doMossa();
       aggiornaCampo();
       display.drawCampo(display.getGraphics(), campo);

        try {
            thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
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

                if(testa.x < 0){
                    testa.x = campo.getHeight() - 1;
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

                if(testa.x >= campo.getHeight()){
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

            default:
                break;
        }


    }

    private void gameOver() {
        thread.interrupt();
    }


}
