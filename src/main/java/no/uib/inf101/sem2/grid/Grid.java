package no.uib.inf101.sem2.grid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Grid<E> implements IGrid<E> {
    int row;
    int col;
    List<List<E>> board;
    
    public Grid(int row, int col) {
        this(row, col, null);
    }
    public Grid(int row, int col, E value) {
        this.row = row;
        this.col = col;
        this.board = new ArrayList<>(row);
        for (int i = 0; i < row; i++) {
            List<E> rows = new ArrayList<>(col);
            for (int j = 0; j < col; j++) {
                rows.add(value);
            }
            this.board.add(rows);
        }
    }

    @Override
    public int rows() {
        return this.row;
    }

    @Override
    public int cols() {
        return this.col;
    }

    @Override
    public Iterator<GridCell<E>> iterator() {
        ArrayList<GridCell<E>> cellIterator = new ArrayList<>();
        for (int i = 0; i < this.row; i++){
            for (int j = 0; j < this.col; j++){
                cellIterator.add(new GridCell<>(new CellPosition(i, j), board.get(i).get(j)));
            }
        }
        return cellIterator.iterator();
    }


    @Override
    public void set(CellPosition pos, E value) {
        if (!positionIsOnGrid(pos)) {
            throw new IndexOutOfBoundsException("Position out of bounds: (" + pos.row() + ", " + pos.col() + ")");
        }   
        board.get(pos.row()).set(pos.col(), value);
    }

    @Override
    public E get(CellPosition pos) {
        if (!positionIsOnGrid(pos)) {
            throw new IndexOutOfBoundsException("Position out of bounds: (" + pos.row() + ", " + pos.col() + ")");
        }
        return board.get(pos.row()).get(pos.col());
    }
    

    @Override
    public boolean positionIsOnGrid(CellPosition pos) {
        if (pos.row() >= 0 && pos.row() < this.row) {
            return pos.col() >= 0 && pos.col() < this.col;
        }
        return false;
    }
}
