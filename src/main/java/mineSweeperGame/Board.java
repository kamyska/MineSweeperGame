package mineSweeperGame;


import java.util.List;

public class Board {

    private GameState gameState;
    private Cell[][] cells;
    private String[][] displayArray;
    private CellBoardGenerator cellBoardGenerator;
    private Difficulty difficulty;

    private int counterOfMarkedBombs = 0;
    private int counterOfMarkedCells = 0;

    public Board(Difficulty difficulty) {
        cellBoardGenerator = new CellBoardGenerator();
        this.difficulty = difficulty;
        this.gameState = GameState.INPROGRESS;
        this.cells = cellBoardGenerator.generateCells(difficulty.getCol(), difficulty.getRow(), difficulty.getNumberOfBombs());
        this.displayArray = cellBoardGenerator.generateCoveredDisplayArray(difficulty.getCol(), difficulty.getRow());
    }

    public void makeAMove(MoveType move, int row, int col) {

        switch (move) {

            case CLICK:

                if (cells[row][col].getIsABomb()) {
                    displayArray[row][col] = "X";
                    System.out.println("BOMBA!!! Przegrałeś!");
                    gameState = gameState.LOST;
                    for (int i = 0; i < displayArray.length; i++) {
                        for (int j = 0; j < displayArray[0].length; j++) {
                            if (cells[i][j].getIsABomb()) {
                                displayArray[i][j] = "X";
                            } else {
                                displayArray[i][j] = String.valueOf((cells[i][j]).getBombsAround());
                            }

                        }
                    }


                } else {
                    displayArray[row][col] = (cells[row][col].getBombsAround()).toString();
                    if (cells[row][col].getBombsAround() == 0) {
                        for (int i = 0; i < cells.length; i++) {
                            for (int j = 0; j < cells[0].length; j++) {
                                if (cells[i][j].getIsABomb()==false && cells[i][j].getBombsAround() == 0 ) {
                                    displayArray[i][j] = String.valueOf(cells[i][j].getBombsAround());
                                }
                            }
                        }
                    }
                }

                break;
            case MARKASBOMB:

                if (counterOfMarkedCells == difficulty.getNumberOfBombs()) {
                    System.out.println("Za dużo zaznaczonych bomb! Odznacz pole, aby zaznaczyć inne.");
                    break;
                }
                displayArray[row][col] = "\u2020";
                counterOfMarkedCells++;
                if (cells[row][col].getIsABomb()) {
                    counterOfMarkedBombs++;
                }

                if (counterOfMarkedBombs == difficulty.getNumberOfBombs()) {
                    this.gameState = gameState.WON;
                }
                displayArray[row][col] = "\u2020";
                break;

            case UNMARKED:
                if (displayArray[row][col].equals("\u2020")) {
                    displayArray[row][col] = "\u25A0";
                    counterOfMarkedCells--;
                }
                break;


        }


    }

    private void showSurroundingCellsWithZero(int row, int col) {


        List<Integer[]> listOfSurroundingCells = cellBoardGenerator.findSurroundingCells(row, col);
        for (Integer[] arrayOfCoordinates :
                listOfSurroundingCells) {
            if (cells[arrayOfCoordinates[0]][arrayOfCoordinates[1]].getBombsAround() == 0) {
                displayArray[arrayOfCoordinates[0]][arrayOfCoordinates[1]] = cells[arrayOfCoordinates[0]][arrayOfCoordinates[1]].getBombsAround().toString();
            }

        }


    }



    public String[][] getDisplayArray() {
        return displayArray;
    }

    public GameState getGameState() {
        return gameState;
    }


}
