/**
 * BACKTRAKING
 *
 * 1) NReinas:
 *  Dadas n reinas en un tablero de nxn calcular todas las posibles soluciones.
 *
 * 2) NReinas v2:
 *  Dadas n reinas en un tablero de nxn. Y dada la posicion de la primera reina
 *  calcula una posible solucion las posibles soluciones.
 */
package NReinas;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author jorge
 */
public class NReines {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Cambiamos la interfaz a la del sistema con la siguente línea:
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(NReines.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        VentanaIntro vi = new VentanaIntro();
        vi.setVisible(true);
        
    }

}
