package no.uib.inf101.sem2.twentyfourtyeight.view;

import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;

 /**
  * The ViewAbleGameModel interface defines the methods used for viewing the state of this
  * 2048 game model.
  */
public interface ViewableGameModel {
    
/**
 * Gets the size of the board
 * @return The GridDimension representing the size of the board
 */
GridDimension getDimension();


/**
 * Get iterable of the 2048 board.
 * Iterable of GridCell containing tiles for the whole board.
 * @return Iterable of the 2048 board
 */
Iterable<GridCell<Integer>> getTilesOnBoard();


/**
 * Moves the tiles on the board in a specific direction
 * @param dx moves the tiles on the x-axis direction (either -1, 0 or 1).
 * @param dy moves the tiles on the y-axis direction (either -1, 0 or 1).
 * @return True if any tiles were moved, false otherwise.
 */
boolean moveTiles(int dx, int dy);


/**
 * Checks if the game is over, meaning no more legal moves on the board.
 * @return True if the game is over, false otherwise.
 */
boolean isGameOver();


/**
 * Gets the state of the game, either ACTIVE or GAME OVER
 * @return The state of the game
 */
GameState getGameState();


/**
 * Gets the total score of the current game.
 * @return The score of the game.
 */
int getScore();

}
