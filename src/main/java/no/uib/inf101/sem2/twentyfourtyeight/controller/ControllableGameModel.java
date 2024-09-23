package no.uib.inf101.sem2.twentyfourtyeight.controller;

import no.uib.inf101.sem2.twentyfourtyeight.view.GameState;

/**
 * The ControllableGameModel interface defines the methods for controlling the game model
 * in the 2048 game.
 */
public interface ControllableGameModel {
    
    /**
     * Moves all the tiles up on the game board.
     * Merges tiles with the same value, if they are adjacent in the direction of the move.
     */
    void moveUp();

    /**
     * Moves all the tiles down on the game board.
     * Merges tiles with the same value, if they are adjacent in the direction of the move.
     */
    void moveDown();

    /**
     * Moves all the tiles to the left on the game board.
     * Merges tiles with the same value, if they are adjacent in the direction of the move.
     */
    void moveLeft();

    /**
     * Moves all the tiles to the right on the game board.
     * Merges tiles with the same value, if they are adjacent in the direction of the move.
     */
    void moveRight();

    /**
     * Gets the current game state.
     * 
     * @return The current GameState (e.g., ACTIVE_GAME, GAME_OVER, etc.)
     */
    GameState getGameState();
}
