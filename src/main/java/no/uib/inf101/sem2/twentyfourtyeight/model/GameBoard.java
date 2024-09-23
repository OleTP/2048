package no.uib.inf101.sem2.twentyfourtyeight.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.Grid;
import no.uib.inf101.sem2.twentyfourtyeight.tile.Tile;

public class GameBoard extends Grid<Integer>{

    
    
    public GameBoard(int row, int col) {
        super(row, col, 0);
        
    }

    public CellPosition getRandomEmptyPosition() {
        List<CellPosition> emptyPositions = new ArrayList<>();

        for (int row = 0; row < this.rows(); row++) {
            for (int col = 0; col < this.cols(); col++) {
                CellPosition position = new CellPosition(row, col);
                if (get(position) == 0) {
                    emptyPositions.add(position);
                }
            }
        }
        
        if (emptyPositions.isEmpty()) {
            return null;
        } else {
            Random random = new Random();
            return emptyPositions.get(random.nextInt(emptyPositions.size()));
        }
    }

    public void addTile(Tile tile) {
        set(tile.getPosition(), tile.getValue());
    }

    
    public String prettyString() {
        String boardToString = "";
        for (int row = 0; row < this.rows(); row++) {
            for (int col = 0; col < this.cols(); col++) {
                CellPosition position = new CellPosition(row, col);
                Integer value = get(position);
                boardToString += value;
            }
            boardToString += "\n";
        }
        return boardToString.strip();
    }
   
}