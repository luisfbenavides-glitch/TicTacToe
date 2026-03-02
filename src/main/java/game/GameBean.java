package game;
import java.io.Serializable;
import game.Cell;

public class GameBean implements Serializable {
    private static final long serialVersionUID = 1L;
    // CAMBIO 1: Tamaño de la matriz a 4x4
    private Cell[][] board = new Cell[4][4]; 
    private String winner = null;

    public GameBean() {
        // CAMBIO 2: Ciclos hasta 4
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j] = new Cell();
            }
        }
    }

    public boolean makeMove(int row, int col, String player) {
        // CAMBIO 3: Validación de límites hasta 4
        if (row >= 0 && row < 4 && col >= 0 && col < 4) {
            if (board[row][col].getState() == null && winner == null) {
                board[row][col].setState(player);
                checkWinner();
                return true;
            }
        }
        return false;
    }

    private void checkWinner() {
        // CAMBIO 4: Revisar filas y columnas de 4 espacios
        for (int i = 0; i < 4; i++) {
            // Filas
            if (checkLine(board[i][0], board[i][1], board[i][2], board[i][3])) winner = board[i][0].getState();
            // Columnas
            if (checkLine(board[0][i], board[1][i], board[2][i], board[3][i])) winner = board[0][i].getState();
        }
        // CAMBIO 5: Diagonales de 4 espacios
        if (checkLine(board[0][0], board[1][1], board[2][2], board[3][3])) winner = board[0][0].getState();
        if (checkLine(board[0][3], board[1][2], board[2][1], board[3][0])) winner = board[0][3].getState();
    }

    // CAMBIO 6: Método ahora recibe 4 celdas para validar
    private boolean checkLine(Cell c1, Cell c2, Cell c3, Cell c4) {
        return (c1.getState() != null && 
                c1.getState().equals(c2.getState()) && 
                c1.getState().equals(c3.getState()) && 
                c1.getState().equals(c4.getState()));
    }

    public Cell[][] getBoard() { return board; }
    public String getWinner() { return winner; }
}
