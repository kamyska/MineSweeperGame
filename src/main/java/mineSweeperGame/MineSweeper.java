package mineSweeperGame;

import java.util.Scanner;

public class MineSweeper {
    Board board;

    public void play() {


        System.out.println("Podaj poziom trudności: EASY(e), MEDIUM(m), HARD(h)");
        try {
            Scanner sc = new Scanner(System.in);
            char c = sc.next().charAt(0);
            switch (c) {

                case 'h':
                    board = new Board(Difficulty.HARD);


                    break;
                case 'e':
                    board = new Board(Difficulty.EASY);


                    break;
                case 'm':
                    board = new Board(Difficulty.MEDIUM);

                    break;
            }
        } catch (IndexOutOfBoundsException|NullPointerException|NumberFormatException ex) {
            System.out.println("Wybrałeś niepoprawny poziom.");
            play();
        }

        Display display = new Display();
        do {
            display.printDisplay(board.getDisplayArray());
            System.out.println();
            askForMove();


        } while (board.getGameState().equals(GameState.INPROGRESS));

        if (board.getGameState().equals(GameState.LOST)){
            display.printDisplay(board.getDisplayArray());
        }



    }

    private void askForMove() {

        System.out.println("Wykonaj ruch podając współrzędne oraz akcję (np. a3o - odkrywa pole A3): " +
                "\nZAZNACZ BOMBĘ (b), ODKRYJ POLE (o), ODZNACZ BOMBĘ (x)");
        int row = 0;
        int col = 0;
        char move = 0;
        try {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.next();
            char rowChar = input.charAt(0);
            row = (int) rowChar - 97;
            col = (Integer.parseInt(String.valueOf(input.charAt(1)))) - 1;
            move = input.charAt(2);


        switch (move) {

            case 'b':
               board.makeAMove(MoveType.MARKASBOMB, row, col);
                break;
            case 'o':
               board.makeAMove(MoveType.CLICK, row, col);
                break;
            case 'x':
                board.makeAMove(MoveType.UNMARKED, row, col);
            default:
                break;
        }
        } catch (IndexOutOfBoundsException|NumberFormatException ex) {
            askForMove();
        }
    }
}
