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

/**
 *
 * @author jorge
 */
public class VentanaReinas extends JFrame {

    private static final int SIZE = 50; //Tamaño de los botones
    private static final String rutaImagenQueen = "Images/queen.png";
    private static final String WHITE = "white";
    private static final String BLACK = "black";

    private int nMAX;

    private Tablero tablero;

    private int nSoluciones;
    private int select;
    private int[][] solucion;

    //Componentes:
    private JButton[][] casillas;
    private ImageIcon imagen;

    // Barra de Menu
    private JMenuBar barraMenu;
    private JMenu menu;
    private JMenuItem inicio;
    private JMenuItem nTablero;
    private JMenu soluciones;
    private JMenuItem thisSol;
    private JMenuItem seleccSol;

    public VentanaReinas(int n) {
        super("N-Reinas Soluciones");
        nMAX = n;
        tablero = new Tablero(nMAX);

        imagen = new ImageIcon(rutaImagenQueen);

        nSoluciones = tablero.getNumSols();
        select = selecSol();
        solucion = tablero.getTableroN(select);

        initComponents();
    }

    public VentanaReinas(Tablero t) {
        super("N-Reinas Soluciones");
        nMAX = t.getMAX();
        tablero = t;

        nSoluciones = tablero.getNumSols();

        select = selecSol();
        solucion = tablero.getTableroN(select);

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
        soluciones = new JMenu("Soluciones");
        thisSol = new JMenuItem("Solución: " + select);
        seleccSol = new JMenuItem("Seleccionar solución");

        //Tablero
        casillas = new JButton[nMAX][nMAX];
        inItTablero(casillas);

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
                try {
                    int n = VentanaIntro.sizeTablero();
                    // Comprueba que sea un valor entero positivo
                    if (n < 0) {
                        throw new NumberFormatException();
                    }
                    cerrarVentana();
                    VentanaReinas vr = new VentanaReinas(n);
                    vr.setVisible(true);
                } catch (NumberFormatException e) {
                }

            }
        });

        seleccSol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                VentanaReinas vr = new VentanaReinas(tablero);
                vr.setSelect(select);
                vr.setSolucion(solucion);
                cerrarVentana();
                vr.setVisible(true);
            }
        });

        // Añadimos componentes
        menu.add(inicio);
        menu.add(nTablero);
        barraMenu.add(menu);

        soluciones.add(thisSol);
        soluciones.add(seleccSol);
        barraMenu.add(soluciones);

        this.setJMenuBar(barraMenu);
    }

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

        //ADD-COMPONENT//
        for (int i = 0; i < nMAX; i++) {
            for (int j = 0; j < nMAX; j++) {
                this.add(casillas[i][j]);
            }
        }

    }

    /**
     * Método que pone la imagen de la reina
     *
     * @param x
     * @param y
     */
    private void setQueen(int x, int y) {
        imagen = new ImageIcon(rutaImagenQueen);
        casillas[x][y].setIcon(new ImageIcon(imagen.getImage().getScaledInstance(SIZE - 20, SIZE - 20, Image.SCALE_DEFAULT)));
        casillas[x][y].setOpaque(true);
    }

    /**
     * Método que pone la imagen en blanco o en negro
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

    /**
     * Método para enseñar por pantalla la ventana para escoger la solución
     *
     * @return
     */
    private int selecSol() {
        String sSoluciones[] = new String[nSoluciones];
        for (int i = 0; i < nSoluciones; i++) {
            String s = "";
            s += i;
            sSoluciones[i] = s;
        }

        Object seleccion = JOptionPane.showInputDialog(
                null,
                "Seleccione solución",
                "Soluciones",
                JOptionPane.QUESTION_MESSAGE,
                null, // null para icono defecto
                //Creamos un array de Strings para que el usuario pueda elegir
                sSoluciones,
                "Soluciones");

        return Integer.parseInt(seleccion.toString());
    }

    private void cerrarVentana() {
        this.dispose();
    }

    ///// GETS & SETS /////
    public int getnMAX() {
        return nMAX;
    }

    public void setSelect(int select) {
        this.select = select;
    }

    public void setSolucion(int[][] solucion) {
        this.solucion = solucion;
    }

}
