package no.uib.inf101.sem2.twentyfourtyeight.tile;

import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.grid.CellPosition;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestRandomTileFactory {
    
    @Test
    public void testRandomTileFactory() {
        RandomTileFactory factory = new RandomTileFactory();
        Tile tile = factory.getNext(new CellPosition(0, 0));
        assertTrue(tile.getValue() == 2 || tile.getValue() == 4);
    }

}