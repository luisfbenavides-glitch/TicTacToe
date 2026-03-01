package game;
import java.io.Serializable;
import game.Cell;

public class GameBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private Cell[][] board = new Cell[3][3];
    private String winner = null;

    public GameBean() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = new Cell();
            }
        }
    }

    // Este es el comando que el Servlet necesita encontrar
    public boolean makeMove(int row, int col, String player) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3) {
            if (board[row][col].getState() == null && winner == null) {
                board[row][col].setState(player);
                checkWinner();
                return true;
            }
        }
        return false;
    }

    private void checkWinner() {
        for (int i = 0; i < 3; i++) {
            if (checkLine(board[i][0], board[i][1], board[i][2])) winner = board[i][0].getState();
            if (checkLine(board[0][i], board[1][i], board[2][i])) winner = board[0][i].getState();
        }
        if (checkLine(board[0][0], board[1][1], board[2][2])) winner = board[0][0].getState();
        if (checkLine(board[0][2], board[1][1], board[2][0])) winner = board[0][2].getState();
    }

    private boolean checkLine(Cell c1, Cell c2, Cell c3) {
        return (c1.getState() != null && c1.getState().equals(c2.getState()) && c1.getState().equals(c3.getState()));
    }

    public Cell[][] getBoard() { return board; }
    public String getWinner() { return winner; }
}