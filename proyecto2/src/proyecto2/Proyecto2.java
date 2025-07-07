/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto2;
import Interfaces.BienvenidaFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Clase principal y punto de entrada para la aplicación de análisis de ADN.
 * <p>
 * Esta clase contiene el método {@code main}, que se encarga de configurar
 * el entorno gráfico de Swing y lanzar la ventana de bienvenida de la aplicación.
 * 
 * @author Luis
 */
public class Proyecto2 {

    /**
     * El método principal que ejecuta la aplicación.
     * <p>
     * Sus responsabilidades son:
     * <ol>
     *   <li>Establecer el "Look and Feel" nativo del sistema operativo para que
     *       la interfaz gráfica se integre visualmente con el entorno del usuario
     *       (Windows, macOS, Linux).</li>
     *   <li>Instanciar y mostrar la ventana de bienvenida ({@link BienvenidaFrame}).</li>
     *   <li>Asegurar que toda la inicialización de la GUI se ejecute en el
     *       <i>Event Dispatch Thread (EDT)</i> de Swing, que es la práctica
     *       recomendada para evitar problemas de concurrencia.</li>
     * </ol>
     * 
     * @param args los argumentos de la línea de comandos (no se utilizan en esta aplicación).
     */
    public static void main(String[] args) {
        // Inicia la GUI en el Event Dispatch Thread (EDT) para seguridad en hilos.
        SwingUtilities.invokeLater(() -> {
            try {
                // Configura el Look and Feel para que coincida con el del sistema operativo.
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                // Si ocurre un error, se imprime en la consola pero la aplicación continúa.
                System.err.println("No se pudo establecer el Look and Feel del sistema.");
                e.printStackTrace();
            }
            
            // Crea la instancia de la ventana de bienvenida y la hace visible.
            new BienvenidaFrame().setVisible(true);
        });
    }
}