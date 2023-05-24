package com.mycompany.gameoflife;

/**
 *
 * @author victor
 */
public class ViewModelMatrix {
    
    private DataMatrix dataMatrix;
    
    public ViewModelMatrix(int numRows, int numCols) {
        dataMatrix = new DataMatrix(numRows, numCols);
    }
    
    public void fillRandom(float ratioAlive) {
        int numRows = dataMatrix.getNumRows();
        int numCols = dataMatrix.getNumCols();
        int numCells = numRows * numCols;
        int numCellsToFill = (int) (numCells * ratioAlive);
        System.out.println(numCellsToFill);
        int counter = 0;
        while (counter < numCellsToFill) {
            int randRow = (int) (Math.random() * numRows);
            int randCol = (int) (Math.random() * numCols);
            if (!dataMatrix.isAlive(randRow, randCol)) {
                dataMatrix.born(randRow, randCol);
                counter ++;
            }
        }    
    }
    
    public void calculateNextGen() {
        DataMatrix previousDataMatrix = dataMatrix.copy();
        int numRows = dataMatrix.getNumRows();
        int numCols = dataMatrix.getNumCols();
        for (int row = 0; row < numRows; row ++) {
            for (int col = 0; col < numCols; col ++) {
                // Apply rules
                int numNeighbors = getNumNeighbors(previousDataMatrix, row, col);
                if (numNeighbors < 2) {
                    dataMatrix.kill(row, col);
                } else if (numNeighbors >= 2 && numNeighbors <= 3 
                        && previousDataMatrix.isAlive(row, col)) {
                    dataMatrix.born(row, col);
                } else if (numNeighbors > 3) {
                    dataMatrix.kill(row, col);
                } else if (numNeighbors == 3) {
                    dataMatrix.born(row, col);
                }
            }
        }
    }
    
    public int getNumNeighbors(DataMatrix previous, int row, int col) {
        int counter = 0;
        int numRows = previous.getNumRows();
        int numCols = previous.getNumCols();
        for (int i = -1; i <=1; i++) {
            for (int j = -1; j <=1; j++) {
                if (!(i == 0 && j == 0)) {
                    int checkRow = row + i;
                    int checkCol = col + j;
                    if (checkRow >= 0 && checkRow < numRows &&
                            checkCol >= 0 && checkCol < numCols) {
                        // Valid position
                        if (previous.isAlive(checkRow, checkCol)) {
                            counter ++;
                        }
                    } 
                }
                
            }
        }
        return counter;
    }
    
    public boolean isAlive(int row, int col) {
        return dataMatrix.isAlive(row, col);
    }
    
}
