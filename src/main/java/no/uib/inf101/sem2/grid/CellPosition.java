package no.uib.inf101.sem2.grid;

public record CellPosition(int row, int col){  
    
    @Override
    public boolean equals(Object obj){
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof CellPosition)) return false;
        CellPosition other = ((CellPosition) obj);
        return this.row() == other.row() && this.col() == other.col();
    }

}
