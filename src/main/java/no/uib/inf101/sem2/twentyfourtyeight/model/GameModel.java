package no.uib.inf101.sem2.twentyfourtyeight.model;


import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.twentyfourtyeight.controller.ControllableGameModel;
import no.uib.inf101.sem2.twentyfourtyeight.tile.Tile;
import no.uib.inf101.sem2.twentyfourtyeight.tile.TileFactory;
import no.uib.inf101.sem2.twentyfourtyeight.view.GameState;
import no.uib.inf101.sem2.twentyfourtyeight.view.ViewableGameModel;

public class GameModel implements ViewableGameModel, ControllableGameModel {
    GameBoard board;
    TileFactory factory;
    Tile tile;
    GameState gameState;
    private int score;

    public GameModel(GameBoard board, TileFactory factory){
        this.board = board;
        this.factory = factory;
        this.gameState = GameState.ACTIVE_GAME;
        score = 0;
        
        for (int i = 0; i < 2; i++){
            addRandomTile();
        }
    }
    
    private void addRandomTile(){
        board.addTile(factory.getNext(board.getRandomEmptyPosition()));
    }
    
    
    @Override
    public GridDimension getDimension() {
        return this.board;
    }
    
    @Override
    public Iterable<GridCell<Integer>> getTilesOnBoard() {
        return this.board;
    }
    
    
    private boolean canMoveTile(Tile tile, int dx, int dy) {
        // Calculates the next position by having current position + dy on row, and + dx on col
        CellPosition nextPosition = new CellPosition(tile.getPosition().row() + dy, tile.getPosition().col() + dx);
        
        // Return false if next position is not on the board
        if (!board.positionIsOnGrid(nextPosition)) {
            return false;
        }
        
        
        // Variable nextValue which will be the value in the nextPosition
        int nextValue = this.board.get(nextPosition);
        
        // If the value of the nextPosition is 0 or the same as the value of the Tile that is to move, return true.
        if (nextValue == 0) {
            return true;
        } else if (nextValue == tile.getValue()) {
            return true;
        }
        // If the value of nextPosition is anything else than 0 or same value as Tile that is to move, return false
        return false;
    }
    
    private void applyMoveTile(Tile tile, int dx, int dy) {
        // Get current position of the tile
        CellPosition currentPosition = tile.getPosition();
        // Calculate next position 
        CellPosition nextPosition = new CellPosition(tile.getPosition().row() + dy, tile.getPosition().col() + dx);
        // Get the value of the tile
        int currentValue = tile.getValue();
        // Get the value of the nextPosition
        int nextValue = this.board.get(nextPosition);
        
        // If the value in the nextPosition is 0:
        if (nextValue == 0) {
            // The tile can move and makes a copy of itself into the new position
            Tile movedTile = tile.shiftedBy(dy, dx);
            // Sets the new tile in the position with the corresponding value
            this.board.set(movedTile.getPosition(), movedTile.getValue());
            // Sets the previous position of the tile to 0
            this.board.set(currentPosition, 0);

            // If value in the nextPosition is the same
        } else if (nextValue == currentValue) {
            // The tile makes a copy of itself into the new position
            Tile mergedTile = tile.shiftedBy(dy, dx);
            // Since value of nextPosition is the same, the tiles merge (their value is multiplicated with 2)
            mergedTile.setValue(currentValue * 2);
            // Sets the new til in the position with its new value
            this.board.set(mergedTile.getPosition(), mergedTile.getValue());
            // Sets the previous position of the Tile to 0
            this.board.set(currentPosition, 0);
            
            // Updates the score with the sum of merged Tiles
            updateScore(currentValue * 2);
        }
    }
    
    public boolean moveTiles(int dx, int dy) {
        // Flag to indicate if any tiles have been moved during proccess
        boolean moved = false;
        // Flag to control the while-loop, whether to keep moving Tiles or not
        boolean keepMoving = true;
        
        // Continue loop if there still are Tiles to move
        while(keepMoving){
            // Assume no more tiles are to be moved, setting keepMoving to false. If any Tile move in the iteration, it is set back to true
            keepMoving = false;

            // Calculating start and end of row and col indicies based on the move direction
            // If move is down, start from the last row
            int startRow = (dy == 1) ? this.board.rows() - 1 : 0;
            // If move is right, start from the last col
            int startCol = (dx == 1) ? this.board.cols() - 1 : 0;
            // If move is down, end at -1, outside the grid (above)
            int endRow = (dy == 1) ? -1 : this.board.rows();
            // If move is right, end at -1, outside the grid(to the left)
            int endCol = (dx == 1) ? -1 : this.board.cols();

            // Calculate the step of row and col iteration based on the move direction
            // Set to iterate through the row against the movement direction to ensure the tile closest to direction moves is being operated first
            int rowStep = (dy == 0) ? 1 : -dy;
            // Set to iterate through the col against movement direction to ensure the tile closest ti direction move is operated first
            int colStep = (dx == 0) ? 1 : -dx;
            
            // Iterates through the board in specified direction
            for (int row = startRow; row != endRow; row += rowStep) {
                for (int col = startCol; col != endCol; col += colStep) {
                    // Creates a CellPosition object of representing the current row and col
                    CellPosition currentPosition = new CellPosition(row, col);
                    // Gets the currentValue of the tile in the currentPosition
                    int currentValue = this.board.get(currentPosition);
                    
                    // If the value of the Tile is any other then 0, it is to be moved
                    if (currentValue != 0) {
                        // Create a tile object with the currentValue and currentPosition
                        Tile currentTile = new Tile(currentValue, currentPosition);
                        
                        // Check if the currentTile can be moved in the given direction (dx, dy)
                        if (canMoveTile(currentTile, dx, dy)) {
                            // Move the currentTile in the given direction (dx, dy)
                            applyMoveTile(currentTile, dx, dy);
                            // Set the moved flag to true to indicate at least one tile has been moved
                            moved = true;
                            // Set keep moving to true to indicate that there might be more tiles to move
                            keepMoving = true;
                        }
                    }
                }
            }
        }
        // If any Tile was moved, add a random Tile
        if (moved) {
            addRandomTile();
            // If the game is over, update the gameState
            if (isGameOver()){
                gameState = GameState.GAME_OVER;
            }
        }
        // Return moved flag to indicate if any tile was moved during the process
        return moved;
    }
    
    
    public boolean isGameOver() {
        // Iterating through all rows of the board
        for (int row = 0; row < board.rows(); row++) {
            //Iterating through all cols of the board
            for (int col = 0; col < board.cols(); col++) {
                // Create a CellPosition object for the currentPosition of the Tile
                CellPosition currentPosition = new CellPosition(row, col);
                // Gets the currentValue of the Tile in this position
                int currentValue = this.board.get(currentPosition);
                // If the currentValue of the tile in the currentPosition is 0, the game is not over (empty cell)
                if (currentValue == 0) {
                    return false; 
                }
                // Iterate through all the neighbouring cells in the possible directions (up, down, left or right)
                for (int dx = -1; dx <= 1; dx++) {
                    for (int dy = -1; dy <= 1; dy++) {
                        // Makes sure to only check the four directions and not diagonal
                        if ((dx == 0) != (dy == 0)) { 
                            // Calculate row and col indencies of neighbouring cells
                            int newRow = row + dy;
                            int newCol = col + dx;
                            // Check if neighbouring cells is within grid boundaries
                            if (board.positionIsOnGrid(new CellPosition(newRow, newCol))) {
                                // If the neighbouring tile has the same value, the game is not over
                                if (currentValue == this.board.get(new CellPosition(newRow, newCol))) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }
        // If the loop completes without returning false, then there are no more possible moves
        return true;
    }
    

    @Override
    public void moveUp() {
        moveTiles(0, -1);
    }
    
    @Override
    public void moveDown() {
        moveTiles(0, 1);
    }
    
    @Override
    public void moveLeft() {
        moveTiles(-1, 0);
    }
    
    @Override
    public void moveRight() {
        moveTiles(1, 0);
    }
    
    @Override
    public GameState getGameState() {
        return gameState;
    }
        
   
    private void updateScore(int value){
        score += value;
    }
    

    public int getScore(){
        return score;
    }
    
    
}

