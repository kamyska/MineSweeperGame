package mineSweeperGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CellBoardGenerator {

    private Cell[][] cells;
    String[][] displayArray;


    public Cell[][] generateCells(int row, int col, int numberOfBombs) {
        cells = new Cell[row][col];


        generateCellsWithBomb(row, col, numberOfBombs);

        generateCellsWithoutBomb(row, col);

        generateCellsWithNumberOfBombsAround(row, col);


        return cells;
    }

    private void generateCellsWithoutBomb(int row, int col) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {

                if (cells[i][j] == null) {
                    Cell cell = new Cell();
                    cells[i][j] = cell;
                    cell.setIsABomb(false);
                    cell.setBombsAround(0);

                }

            }

        }
    }

    private void generateCellsWithBomb(int row, int col, int numberOfBombs) {
        for (int i = 0; i < numberOfBombs; i++) {
            Random randomCol = new Random();
            Random randomRow = new Random();
            int randomColInt = randomCol.nextInt(col);
            int randomRowInt = randomRow.nextInt(row);
            Cell cellWithBomb = new Cell();
            if (cells[randomRowInt][randomColInt] == null) {
                cells[randomRowInt][randomColInt] = cellWithBomb;
                cellWithBomb.setIsABomb(true);
            } else {
                i--;
            }


        }
    }

    public void generateCellsWithNumberOfBombsAround(int row, int col) {


        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (cells[i][j].getIsABomb()) {
                    List<Integer[]> listOfSurrounding = findSurroundingCells(i, j);
                    for (Integer[] arrayOfCoordinates : listOfSurrounding) {

                        {
                            if (arrayOfCoordinates[0] < row && arrayOfCoordinates[1] < col)
                                cells[arrayOfCoordinates[0]][arrayOfCoordinates[1]].setBombsAround(1);
                        }
                    }
                }
            }
        }
    }


    public List<Integer[]> findSurroundingCells(int row, int col) {

        List<Integer[]> list = new ArrayList<>();
        list.add(new Integer[]{row + 1, col + 1});
        list.add(new Integer[]{row, col + 1});
        list.add(new Integer[]{row + 1, col});
        if (col != 0) {
            list.add(new Integer[]{row + 1, col - 1});
            list.add(new Integer[]{row, col - 1});
        }
        if (row != 0) {
            list.add(new Integer[]{row - 1, col});
            list.add(new Integer[]{row - 1, col + 1});
            if (col != 0)
                list.add(new Integer[]{row - 1, col - 1});
        }

        return list;
    }

    public String[][] generateCoveredDisplayArray(int row, int col) {
        displayArray = new String[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                displayArray[i][j] = "[]";

            }

        }

        return displayArray;
    }

}
