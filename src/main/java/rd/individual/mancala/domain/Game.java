package rd.individual.mancala.domain;

public class Game {
    private Board board;
    private int index;

    public Game(Board board) {
        this.board = board;

        index = findIndex(board);
        int stones = board.getPits()[index];
        board.getPits()[index] = 0;
        while (stones > 0) {
            index++;
            if (index == 13) {
                index = 0;
            }
            board.getPits()[index] += 1;
            stones--;
        }
    }

    public Board getBoard() {
        return board;
    }

    public int getIndex() {
        return index;
    }

    private int findIndex(Board board) {
        int index = Integer.valueOf(board.getCurrent().substring(board.getCurrent().indexOf("_") + 1));
        if (index == 6 || index == 13) {
            //house of the player, must not be in here
        }
        return --index;
    }
}
