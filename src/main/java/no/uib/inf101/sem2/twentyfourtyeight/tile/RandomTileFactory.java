package no.uib.inf101.sem2.twentyfourtyeight.tile;

import java.util.Random;

import no.uib.inf101.sem2.grid.CellPosition;

public class RandomTileFactory implements TileFactory {

    @Override
    public Tile getNext(CellPosition position) {
       Random random = new Random();
       int value = random.nextInt(10) < 9 ? 2 : 4;
       Tile newTile = new Tile(value, position);
       return newTile;

}

}