package com.company;

import java.awt.Point;

public class Gioco implements Runnable{

    private Campo campo;
    private Snake snake;

    public Gioco(Campo campo, Snake snake){
        this.campo = campo;
        this.snake = snake;
    }

    @Override
    public void run() {
        checkMossa();
        snake.mossa();

    }

    private boolean checkMossa(){
        Point testa = snake.corpo.get(0);

        switch (snake.getDirezione()){
            case LEFT:
                testa.x--;

        }

        return true;
    }


}
