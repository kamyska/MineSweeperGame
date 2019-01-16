
        package mineSweeperGame;

import java.util.Random;

public class Cell {
    private boolean isABomb;
    private Integer bombsAround;
    Random random = new Random();

    public boolean getIsABomb() {
        return this.isABomb;
    }

    public Cell() {
    }

    public void setIsABomb(boolean isABomb) {
        this.isABomb = isABomb;
    }

    public Integer getBombsAround() {
        return bombsAround;
    }

    public void setBombsAround(Integer bombsAround) {

       Integer acutalNumberOfBombs = this.getBombsAround();
if (acutalNumberOfBombs ==null){
    acutalNumberOfBombs = 0;
}
       this.bombsAround = bombsAround+acutalNumberOfBombs;
    }

    public Cell(boolean isABomb, Integer bombsAround) {
        this.isABomb = isABomb;
        this.bombsAround = bombsAround;
    }


}
