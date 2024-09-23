package no.uib.inf101.sem2.twentyfourtyeight.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import no.uib.inf101.sem2.twentyfourtyeight.view.GameState;
import no.uib.inf101.sem2.twentyfourtyeight.view.GameView;

public class GameController implements KeyListener{
    private ControllableGameModel cgm;
    private GameView view;

    public GameController(ControllableGameModel cgm, GameView view ){
        this.cgm = cgm;
        this.view = view;

        this.view.addKeyListener(this);
        //this.view.setFocusable(true);
    }


        @Override
        public void keyPressed(KeyEvent e) {
            if(this.cgm.getGameState() != GameState.GAME_OVER){
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                // Up arrow was pressed
                cgm.moveUp();
                updateView();
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                // Down arrow was pressed
                cgm.moveDown();
                updateView();
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                // Left arrow was pressed
                cgm.moveLeft();
                updateView();
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                // Right arrow was pressed
                cgm.moveRight();
                updateView();
            }
        }
    }

        private void updateView() {
            this.view.repaint();
        }

        @Override
        public void keyTyped(KeyEvent e) {
            
        }


        @Override
        public void keyReleased(KeyEvent e) {
            
        }
}