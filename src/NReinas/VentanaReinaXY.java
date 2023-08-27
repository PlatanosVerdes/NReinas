/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NReinas;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import static NReinas.VentanaIntro.sizeTablero;

/**
 *
 * @author jorge
 */
public class VentanaReinaXY extends JFrame {

    //Tamaño Pantalla
    private static final int SIZE = 50; //Tamaño de los botones
    private static final String WHITE = "white";
    private static final String BLACK = "black";

    private int nMAX;
    private int xReina;
    private int yReina;

    private int[][] solucion;

    private Tablero tablero;

    //Componentes:
    private JButton[][] casillas;
    private ImageIcon imagen;

    // Barra de Menu
    private JMenuBar barraMenu;
    private JMenu menu;
    private JMenuItem inicio;
    private JMenuItem nTablero;
    private JMenu vSolucion;
    private JMenuItem queen;
    private JMenuItem changePos;

    public VentanaReinaXY(int n, int x, int y) {
        super("NReinas XY");
        nMAX = n;
        xReina = x;
        yReina = y;
        tablero = new Tablero(nMAX, xReina, yReina);
        solucion = tablero.getTablero();
        boolean possible = tablero.solveXY(solucion, 0);

        while (!possible) {
            JOptionPane.showMessageDialog(null, "No existen soluciones!");
            this.nMAX = sizeTablero();
            possible = createIT(nMAX);
        }
        solucion = tablero.getTablero();
        initComponents();
    }

    private void initComponents() {
        this.setSize(SIZE * nMAX, SIZE * nMAX);
        this.setLocationRelativeTo(null);

        this.setLayout(new GridLayout(nMAX, nMAX));
        //Cierre con la X de la ventana
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Ensanchable
        this.setResizable(true);
        imagen = new ImageIcon("Images/queen.png");

        //Componentes:
        //Barra
        barraMenu = new JMenuBar();
        menu = new JMenu("Menú");
        inicio = new JMenuItem("Menú Principal");
        nTablero = new JMenuItem("Cambiar tamaño");
        vSolucion = new JMenu("Solución");
        queen = new JMenuItem("Reina ( " + xReina + ", " + yReina + " ) - "
                + "N: " + this.nMAX);
        changePos = new JMenuItem("Cambiar posición");

        //Tablero
        casillas = new JButton[nMAX][nMAX];
        inItTablero(casillas);

        //Marcamos la casilla de la reina que ha elegido el usuario
        casillas[xReina][yReina].setBackground(Color.RED);

        //Acciones Barra
        inicio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                cerrarVentana();
                VentanaIntro vi = new VentanaIntro();
                vi.setVisible(true);
            }
        });

        nTablero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                cerrarVentana();
                VentanaIntro.makeItVentXY();
            }
        });

        changePos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                cerrarVentana();

                //Posición en el eje x
                String toSelect[] = new String[nMAX];
                for (int i = 0; i < nMAX; i++) {
                    String s = "";
                    s += i;
                    toSelect[i] = s;
                }

                Object seleccion = JOptionPane.showInputDialog(
                        null,
                        "Seleccione la posición de las columnas",
                        "Soluciones",
                        JOptionPane.QUESTION_MESSAGE,
                        null, // null para icono defecto
                        //Creamos un array de Strings para que el usuario pueda elegir
                        toSelect,
                        "Soluciones");
                int xQ = Integer.parseInt(seleccion.toString());

                //Posición en el eje y
                seleccion = JOptionPane.showInputDialog(
                        null,
                        "Seleccione ",
                        "Soluciones  la posición de las filas",
                        JOptionPane.QUESTION_MESSAGE,
                        null, // null para icono defecto
                        //Creamos un array de Strings para que el usuario pueda elegir
                        toSelect,
                        "Soluciones");
                int yQ = Integer.parseInt(seleccion.toString());

                VentanaReinaXY vQueenXY = new VentanaReinaXY(nMAX, xQ, yQ);
                vQueenXY.setVisible(true);
            }
        });

        // Añadimos componentes
        menu.add(inicio);
        barraMenu.add(menu);

        vSolucion.add(queen);
        vSolucion.add(nTablero);
        vSolucion.add(changePos);
        barraMenu.add(vSolucion);

        this.setJMenuBar(barraMenu);
    }

    /**
     * Metodo que pide al usuario los datos para crear el tablero
     *
     * @param nSize
     * @return
     */
    private boolean createIT(int nSize) {
        try {
            //Tamaño tablero
            //int nSize = sizeTablero();
            if (nSize < 0) {
                throw new NumberFormatException();
            }

            //Posición en el eje x
            String toSelect[] = new String[nSize];
            for (int i = 0; i < nSize; i++) {
                String s = "";
                s += i;
                toSelect[i] = s;
            }

            Object seleccion = JOptionPane.showInputDialog(
                    null,
                    "Seleccione la posición de las columnas",
                    "Soluciones",
                    JOptionPane.QUESTION_MESSAGE,
                    null, // null para icono defecto
                    //Creamos un array de Strings para que el usuario pueda elegir
                    toSelect,
                    "Soluciones");
            xReina = Integer.parseInt(seleccion.toString());

            //Posición en el eje y
            seleccion = JOptionPane.showInputDialog(
                    null,
                    "Seleccione ",
                    "Soluciones  la posición de las filas",
                    JOptionPane.QUESTION_MESSAGE,
                    null, // null para icono defecto
                    //Creamos un array de Strings para que el usuario pueda elegir
                    toSelect,
                    "Soluciones");
            yReina = Integer.parseInt(seleccion.toString());
            tablero = new Tablero(nMAX, xReina, yReina);
            solucion = tablero.getTablero();
            boolean posible = tablero.solveXY(solucion, 0);
            return posible;
        } catch (NumberFormatException e) {
        }
        return false;
    }

    /**
     * Metodo que inicializa el tablero a 0
     *
     * @param casillas
     */
    private void inItTablero(JButton casillas[][]) {
        //Recorrido del tablero 
        for (int i = 0; i < nMAX; i++) {
            for (int j = 0; j < nMAX; j++) {
                casillas[i][j] = new JButton();
                //Blanco y negro (par e impar)
                if ((i + j) % 2 == 0) {
                    setBox(BLACK, i, j);
                    if (solucion[i][j] == 1) {
                        setQueen(i, j);
                    }

                } else {
                    setBox(WHITE, i, j);
                    if (solucion[i][j] == 1) {
                        setQueen(i, j);
                    }
                }
            }
        }

        //ADD-COMPONENT
        for (int i = 0; i < nMAX; i++) {
            for (int j = 0; j < nMAX; j++) {
                this.add(casillas[i][j]);
            }
        }
    }

    /**
     * Metodo que construye un boton con el imagen de la reina
     *
     * @param x
     * @param y
     */
    private void setQueen(int x, int y) {
        imagen = new ImageIcon("Images/queen.png");
        casillas[x][y].setIcon(new ImageIcon(imagen.getImage().getScaledInstance(SIZE - 20, SIZE - 20, Image.SCALE_DEFAULT)));
        casillas[x][y].setOpaque(true);
    }

    /**
     * Metodo que construye un boton con el imagen de color blanco o negro,
     * segun parametro
     *
     * @param x
     * @param y
     */
    private void setBox(String color, int x, int y) {

        if (color.equals("white")) {
            casillas[x][y].setBackground(Color.WHITE);
        } else {
            casillas[x][y].setBackground(Color.BLACK);
        }

        imagen = new ImageIcon("Images/" + color + ".jpg");
        casillas[x][y].setIcon(imagen);
    }

    private void cerrarVentana() {
        this.dispose();
    }

}
