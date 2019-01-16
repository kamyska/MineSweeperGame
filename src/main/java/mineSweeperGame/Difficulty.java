package mineSweeperGame;

public enum Difficulty {

    EASY(8,8,10),
    MEDIUM(16,16,40),
    HARD(16,32,99);

    private int col;
    private int row;
    private int numberOfBombs;

    Difficulty(int col, int row, int numberOfBombs) {
        this.col = col;
        this.row = row;
        this.numberOfBombs = numberOfBombs;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public int getNumberOfBombs() {
        return numberOfBombs;
    }


}
