package com.mycompany.gameoflife;

/**
 *
 * @author victor
 */
public class DataMatrix {
    
    private boolean[][] cells;
    
    public DataMatrix(int numRows, int numCols) {
        cells = new boolean[numRows][numCols];
        for (int row = 0; row < numRows; row ++) {
            for (int col = 0; col < numCols; col ++) {
                cells[row][col] = false;
            }
        }
    }
    
    public void kill(int row, int col) {
        cells[row][col] = false;
    }
    
    public void born(int row, int col) {
        cells[row][col] = true;
    }
    
    public int getNumRows() {
        return cells.length;
    }
    
    public int getNumCols() {
        return cells[0].length;
    }
    
    public DataMatrix copy() {
        int rows = getNumRows();
        int cols = getNumCols();
        DataMatrix newDatamatrix = new DataMatrix(rows, cols);
        for (int row = 0; row < rows; row ++) {
            for (int col = 0; col < cols; col ++) {
                newDatamatrix.cells[row][col] = cells[row][col];
            }
        }
        return newDatamatrix;
    }
    
    public boolean isAlive(int row, int col) {
        return cells[row][col];
    }
    
}
