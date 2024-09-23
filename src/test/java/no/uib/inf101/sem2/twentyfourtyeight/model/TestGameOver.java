package no.uib.inf101.sem2.twentyfourtyeight.model;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.twentyfourtyeight.tile.FixedTileFactory;
import no.uib.inf101.sem2.twentyfourtyeight.tile.Tile;
import no.uib.inf101.sem2.twentyfourtyeight.tile.TileFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestGameOver {
    private GameModel model;
    private GameBoard board;
    private TileFactory factory;

    @BeforeEach
    public void setUp() {
        board = new GameBoard(4, 4);
        factory = new FixedTileFactory(2);
        model = new GameModel(board, factory);
    }

    @Test
    public void testIsGameOverFalse() {
        assertFalse(model.isGameOver());
    }

    @Test
    public void testIsGameOverTrue() {
        // Fill the board with tiles in a way that no valid moves are possible
        for (int row = 0; row < board.rows(); row++) {
            for (int col = 0; col < board.cols(); col++) {
                int value = (row + col) % 2 == 0 ? 2 : 4;
                board.set(new CellPosition(row, col), value);
            }
        }

        assertTrue(model.isGameOver());
    }
}
