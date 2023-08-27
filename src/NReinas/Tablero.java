package NReinas;

import java.util.ArrayList;

public class Tablero {

    private int[][] board;
    private static int MAX;
    //private boolean Possible;
    private ArrayList<Soluciones> soluciones;
    private int numSol;
    private int[] Qx;
    private int[] Qy;
    private int Userx;
    private int Usery;

    /**
     * Constructor para todas las posibles soluciones
     *
     * @param size
     */
    public Tablero(int size) {
        System.out.println("////////////////////");
        System.out.println("//// SIMULACIÓN ////");
        System.out.println("////////////////////\n");
        //Possible = false;
        numSol = 0;
        soluciones = new ArrayList<Soluciones>();
        MAX = size;
        MAX = size;
        board = new int[MAX][MAX];
        inIt();
        // Printeamos el tablero
        printSol();

        solveNQ();
    }

    /**
     * Constructor dada la primera reina
     *
     * @param size
     * @param x
     * @param y
     */
    public Tablero(int size, int x, int y) {
        MAX = size;
        Userx = x;
        Usery = y;
        board = new int[MAX][MAX];
        inIt();
        board[Userx][Usery] = 1;
    }

    /**
     * Rellena el tablero de ceros
     */
    private void inIt() {
        for (int i = 0; i < MAX; i++) {
            for (int j = 0; j < MAX; j++) {
                board[i][j] = 0;
            }
        }
    }

    /**
     * Método que retona si es seguro colocar una reina.
     *
     * @param board
     * @param row
     * @param col
     * @return
     */
    public boolean isSafe(int board[][], int row, int col) {
        int i, j;

        // CHECK ROW ON THE LEFT
        for (i = 0; i < col; i++) {
            if (board[row][i] == 1) {
                return false;
            }
        }
        // CHECK ROW ON THE RIGHT
        for (i = 0; i < MAX; i++) {
            if (board[row][i] == 1) {
                return false;
            }
        }
        // LEFT UP DIAGONAL
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        // LEFT DOWN DIAGONAL
        for (i = row, j = col; i < MAX && j >= 0; i++, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        // RIGHT UP DIAGONAL
        for (i = row, j = col; i >= 0 && j < MAX; i--, j++) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        // RIGHT DOWN DIAGONAL
        for (i = row, j = col; i < MAX && j < MAX; i++, j++) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        return true;
    }

    public boolean solution(int board[][], int col) {
        boolean posible = false;
        /* base case: If all queens are placed  
        then return true */
        if (col == MAX) {
            numSol++;
            System.out.println("SOLUCIÓN nº" + numSol + " !");
            //Print();
            Soluciones temp = new Soluciones(MAX);
            temp.fill(board, MAX);
            //temp.print(MAX);
            soluciones.add(temp);
            soluciones.get(numSol - 1).print(MAX);
            //Possible = true;
            return true;
        }

        /* Consider this column and try placing  
        this queen in all rows one by one */
        //Possible = false;  
        for (int i = 0; i < MAX; i++) {
            /* Check if queen can be placed on  
            board[i][col] */
            if (isSafe(board, i, col)) {
                /* Place this queen in board[i][col] */
                board[i][col] = 1;

                // Make result true if any placement  
                // is possible  
                posible = solution(board, col + 1) || posible;

                /* If placing queen in board[i][col]  
                doesn't lead to a solution, then  
                remove queen from board[i][col] */
                board[i][col] = 0; // BACKTRACK  
            }
        }

        /* If queen can not be place in any row in  
            this column col then return false */
        return posible;
    }

    /**
     * Backtracking para la solcion del problema. Devuelve verdadero si todas
     * las reinas han sido colocadas.
     *
     * @param board
     * @param col
     * @return
     */
    boolean solveXY(int board[][], int col) {
        //Para printear información
        int counter = 0;
        System.out.println("Paso nº" + counter);
        printSol();
        counter++;
        System.out.println("\n");
        //
        if (col == Usery) {
            col++;
        }
        if (col >= MAX) {
            return true;
        }
        for (int i = 0; i < MAX; i++) {
            if (isSafe(board, i, col)) {
                // Colocamos la reina si es seguro
                board[i][col] = 1;
                if (solveXY(board, col + 1) == true) {
                    return true;
                }
                board[i][col] = 0; //BACKTRACK 
                System.out.println("\nBack!");
            }
        }
        return false;
    }

    public boolean solveNQ() {
        System.out.println("Número de soluciones: " + numSol);
        return solution(board, 0);
    }

    public void printSol() {
        for (int i = 0; i < MAX; i++) {
            for (int j = 0; j < MAX; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println("");
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    /////////////////////////////// SET & GET ///////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    public int[][] getTableroN(int idx) {
        return soluciones.get(idx).getBoard();
    }

    public int[][] getTablero() {
        return board;
    }

    public int getQx(int i) {
        return Qx[i];
    }

    public int getNumSols() {
        return numSol;
    }

    public int getQy(int i) {
        return Qy[i];
    }

    public static int getMAX() {
        return MAX;
    }

}
