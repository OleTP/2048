package no.uib.inf101.sem2.twentyfourtyeight.tile;

import no.uib.inf101.sem2.grid.CellPosition;

public class FixedTileFactory implements TileFactory {
    private final int fixedValue;

    public FixedTileFactory(int fixedValue) {
        this.fixedValue = fixedValue;
    }

    @Override
    public Tile getNext(CellPosition position) {
        return new Tile(fixedValue, position);
    }
}

