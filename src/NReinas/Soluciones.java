package NReinas;

public class Soluciones {

    private int[][] board;

    public Soluciones(int max) {
        board = new int[max][max];
        for (int i = 0; i < max; i++) {
            for (int j = 0; j < max; j++) {
                board[i][j] = 0;
            }
        }
    }

    /**
     * Método que rellena un tablero a 0
     *
     * @param b
     * @param max
     */
    public void fill(int[][] b, int max) {
        for (int i = 0; i < max; i++) {
            for (int j = 0; j < max; j++) {
                board[i][j] = b[i][j];
            }
        }
    }

    /**
     * Metodo que printea por pantalla el contenido de un tablero
     *
     * @param max
     */
    public void print(int max) {
        for (int i = 0; i < max; i++) {
            for (int j = 0; j < max; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public int[][] getBoard() {
        return board;
    }

}
