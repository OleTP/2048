package no.uib.inf101.sem2.twentyfourtyeight.tile;

import no.uib.inf101.sem2.grid.CellPosition;

public class Tile implements ITile {
    private int value;
    private CellPosition position;

    public Tile(int value, CellPosition position) {
        this.value = value;
        this.position = position;
    }

    public int getValue() {
        return value;
    }

    public CellPosition getPosition() {
        return position;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setPosition(CellPosition position) {
        this.position = position;
    }
   
    public Tile shiftedBy(int deltaRow, int deltaCol) {
        return new Tile(this.value, new CellPosition(this.position.row() + deltaRow, this.position.col() + deltaCol));
    }
    
    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + value;
        result = prime * result + position.hashCode();
        return result;
    }
}
