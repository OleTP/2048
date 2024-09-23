package no.uib.inf101.sem2.twentyfourtyeight.tile;

import no.uib.inf101.sem2.grid.CellPosition;

public interface TileFactory {
    Tile getNext(CellPosition position);
}
