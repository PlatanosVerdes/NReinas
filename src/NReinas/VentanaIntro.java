/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NReinas;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author jorge
 */
public class VentanaIntro extends JFrame {

    //Tamaño Pantalla
    private final int alto_MAX = 200;
    private final int ancho_MAX = 300;

    //Variables
    VentanaReinaXY vReninaXY;
    VentanaReinas vReinas;

    //Componentes:
    private JPanel panelBotones;
    private JPanel panelTitulo1;
    private JPanel panelTitulo2;

    private JLabel titulo1;
    private JLabel titulo2;

    private JButton buttonYes;
    private JButton buttonNo;

    public VentanaIntro() {
        super("N-Reinas");
        initComponents();
    }

    private void initComponents() {
        //+35 por los botones de la ventana y +5 para las separaciones Horitzontales
        this.setSize(ancho_MAX, alto_MAX);
        this.setLocationRelativeTo(null);

        //Quitamos el Layout para ordenar los paneles
        this.getContentPane().setLayout(null);

        //Cierre con la X de la ventana
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //No ensanchable
        this.setResizable(false);

        ////Componentes////
        //Paneles
        this.panelBotones = new JPanel();
        //new GridLayout(filas,columnas,espacio entre columnas, espacio entre filas)
        panelBotones.setLayout(new GridLayout(1, 2, 20, 0));
        panelBotones.setVisible(true);

        this.panelTitulo1 = new JPanel();
        panelTitulo1.setVisible(true);
        this.panelTitulo2 = new JPanel();
        panelTitulo2.setVisible(true);

        //Labels
        this.titulo1 = new JLabel();
        this.titulo1.setText("BIENVENIDO");
        this.titulo1.setFont(new Font("Arial", Font.PLAIN, 18));
        this.titulo1.setVerticalAlignment(SwingConstants.CENTER);

        this.titulo2 = new JLabel();
        this.titulo2.setText("¿Deseas elegir la posición de la reina?");
        this.titulo2.setFont(new Font("Arial", Font.PLAIN, 12));
        this.titulo2.setVerticalAlignment(SwingConstants.CENTER);

        //Botones
        this.buttonYes = new JButton("Si");
        //this.buttonYes.setBackground(Color.BLACK);

        this.buttonNo = new JButton("No");
        //this.buttonNo.setBackground(Color.BLACK);

        buttonYes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                makeItVentXY();
                cerrarVentana();
            }
        });

        buttonNo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    int nSize = sizeTablero();
                    if (nSize < 0) {
                        throw new NumberFormatException();
                    }
                    cerrarVentana();
                    vReinas = new VentanaReinas(nSize);
                    vReinas.setVisible(true);
                } catch (NumberFormatException e) {
                }
            }
        });

        //.setBounds(x,y,ancho,alto)
        panelBotones.setBounds(27, this.alto_MAX - 100, this.ancho_MAX - 60, 30);
        panelTitulo1.setBounds(7, 5, this.ancho_MAX - 20, 22);
        panelTitulo2.setBounds(7, 20 + 5 + 22, this.ancho_MAX - 20, 20);

        //Añadir componentes
        panelBotones.add(buttonYes);
        panelBotones.add(buttonNo);

        panelTitulo1.add(titulo1);
        panelTitulo2.add(titulo2);

        //Añadimos paneles a la ventana
        this.getContentPane().add(panelBotones);
        this.getContentPane().add(panelTitulo1);
        this.getContentPane().add(panelTitulo2);
    }

    private void cerrarVentana() {
        this.dispose();
    }

    public static int sizeTablero() {
        try {
            int n = Integer.parseInt(JOptionPane.showInputDialog("Tamaño del tablero:"));
            // Comprueba que sea un valor entero positivo.
            if (n < 0) {
                throw new NumberFormatException();
            } else {
                while (n < 4) {
                    JOptionPane.showMessageDialog(null, "No hay soluciones");
                    n = sizeTablero();
                }
                return n;
            }
        } catch (NumberFormatException e) {
        }
        return -1;
    }

    public static void makeItVentXY() {
        //Tamaño tablero
        int nSize = sizeTablero();
        if (nSize < 0) {
            throw new NumberFormatException();
        }

        String toSelect[] = new String[nSize];
        for (int i = 0; i < nSize; i++) {
            String s = "";
            s += i;
            toSelect[i] = s;
        }
        
        //Posición en el eje x
        Object seleccion = JOptionPane.showInputDialog(
                null,
                "Selecciona columna:",
                "Seleccione la posición en las columnas",
                JOptionPane.QUESTION_MESSAGE,
                null, // null para icono defecto
                //Creamos un array de Strings para que el usuario pueda elegir
                toSelect,
                "Soluciones");
        int x = Integer.parseInt(seleccion.toString());

        //Posición en el eje y
        seleccion = JOptionPane.showInputDialog(
                null,
                "Selecciona fila:",
                "Seleccione la posición en las filas",
                JOptionPane.QUESTION_MESSAGE,
                null, // null para icono defecto
                //Creamos un array de Strings para que el usuario pueda elegir
                toSelect,
                "Soluciones");
        int y = Integer.parseInt(seleccion.toString());

        //Generar Tablero
        VentanaReinaXY vReninaXY = new VentanaReinaXY(nSize, x, y);
        vReninaXY.setVisible(true);

    }

}
