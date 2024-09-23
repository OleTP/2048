package no.uib.inf101.sem2.twentyfourtyeight.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.twentyfourtyeight.tile.RandomTileFactory;

public class TestGameScore {
    
    @Test
    public void testUpdatedGameScore(){
        GameBoard board = new GameBoard(4, 4);
        board.set(new CellPosition(0, 0), 2);
        board.set(new CellPosition(0, 1), 2);
        
        RandomTileFactory factory = new RandomTileFactory();
        GameModel model = new GameModel(board, factory);
        
        model.moveRight();
        assertEquals(4, model.getScore());
    }
}

