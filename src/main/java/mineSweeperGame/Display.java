package mineSweeperGame;

public class Display {

    private String[][] displayArray;


    public void printDisplay(String[][] displayArray) {
        int row = displayArray.length;
        int col = displayArray[0].length;

        for (int i = 0; i < row ; i++) {
           System.out.printf("%6s", i+1);
        }
        for (int i = 0; i < row; i++) {
            System.out.println();

            System.out.print((char)(i+97));
            for (int j = 0; j < col; j++) {

                System.out.printf("%6s",displayArray[i][j]);
            }

        }


    }

}
