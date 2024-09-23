package no.uib.inf101.sem2.twentyfourtyeight.tile;

import no.uib.inf101.sem2.grid.CellPosition;

/**
 * The ITile interface defines the methods for working with tiles in the 2048 game.
 * It specifies the methods for accessing and modifying tile properties.
 */
public interface ITile {
    
    /**
     * Gets the value of the tile.
     * @return The value of the tile.
     */
    int getValue();

    /**
     * Gets the position of the tile on the game board.
     * @return The CellPosition representing the tile's position.
     */
    CellPosition getPosition();

    /**
     * Sets the value of the tile.
     * @param value The new value for the tile.
     */
    void setValue(int value);

    /**
     * Sets the position of the tile on the game board.
     * @param position The new CellPosition representing the tile's position.
     */
    void setPosition(CellPosition position);

    /**
     * Creates a new tile by shifting the current tile's position by the specified
     * row and column deltas.
     * @param deltaRow The change in row position.
     * @param deltaCol The change in column position.
     * @return A new Tile object with the updated position.
     */
    Tile shiftedBy(int deltaRow, int deltaCol);

    /**
     * Generates a hash code for the tile based on its value and position.
     * @return The generated hash code for the tile.
     */
    int hashCode();
}
