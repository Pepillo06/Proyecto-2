package Interfaces;

import Interfaces.CargarFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

/**
 * Representa la ventana inicial de bienvenida del sistema de análisis de secuencias de ADN.
 * 
 * <p>Esta clase implementa la primera interfaz que observa el usuario al ejecutar la aplicación,
 * proporcionando una experiencia visual agradable con imagen de fondo y una navegación intuitiva.
 * La ventana está diseñada para generar confianza en el usuario y facilitar el acceso inicial
 * al sistema de análisis genético.</p>
 * 
 * <p><strong>Características principales:</strong></p>
 * <ul>
 *   <li>Interfaz de entrada atractiva con imagen de fondo científica</li>
 *   <li>Título descriptivo que clarifica el propósito de la aplicación</li>
 *   <li>Botón de acceso directo al módulo de carga de archivos</li>
 *   <li>Manejo elegante de errores en caso de que la imagen no esté disponible</li>
 * </ul>
 * 
 * <p>La ventana utiliza un {@link GridBagLayout} para centrar perfectamente los elementos
 * y mantener una apariencia profesional independientemente del tamaño de pantalla del usuario.
 * Al hacer clic en "Entrar", la aplicación navega automáticamente hacia {@link CargarFrame}
 * donde el usuario podrá seleccionar su archivo de secuencia de ADN.</p>
 * 
 * @author Luis
 */
public class BienvenidaFrame extends JFrame {

    /**
     * Imagen de fondo científica que proporciona contexto visual apropiado para la aplicación.
     * 
     * <p>Esta imagen se carga dinámicamente desde el directorio de recursos y se escala
     * automáticamente para ajustarse al tamaño de la ventana. En caso de que el archivo
     * de imagen no esté disponible, la aplicación continuará funcionando normalmente
     * con un fondo sólido, garantizando la robustez del sistema.</p>
     * 
     * @see BufferedImage
     * @see ImageIO#read(File)
     */
    private BufferedImage imagenFondo;

    /**
     * Inicializa y configura la ventana de bienvenida del sistema.
     * 
     * <p>Este constructor se encarga de crear una interfaz de usuario pulida y profesional
     * que sirve como punto de entrada al análisis de secuencias de ADN. Durante la
     * inicialización, se configura automáticamente:</p>
     * 
     * <ul>
     *   <li>Las dimensiones óptimas de la ventana (800x600 píxeles)</li>
     *   <li>La carga y configuración de la imagen de fondo científica</li>
     *   <li>El posicionamiento centrado de la ventana en la pantalla</li>
     *   <li>Los componentes visuales con tipografía y colores apropiados</li>
     *   <li>Los controladores de eventos para la navegación entre ventanas</li>
     * </ul>
     * 
     * <p>En caso de que la imagen de fondo no pueda ser cargada (por ejemplo, si el archivo
     * fue movido o eliminado), la aplicación continuará funcionando normalmente con un
     * fondo sólido, asegurando que el usuario siempre pueda acceder al sistema.</p>
     * 
     * <p><strong>Comportamiento del botón "Entrar":</strong><br>
     * Al activarse, cierra la ventana actual de forma limpia y abre {@link CargarFrame}
     * en el hilo de eventos de Swing, garantizando una transición suave y sin problemas
     * de concurrencia.</p>
     * 
     */
    public BienvenidaFrame() {
        // Configuración básica de la ventana
        setTitle("Proyecto 2 - Análisis de Secuencias de ADN - Bienvenida");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Centrar la ventana
        
        // cargar la imagen de fondo
        try {
            imagenFondo = ImageIO.read(new File("src/Imagen/360_F_281966380_17LnnpANRTABwsBe38pGEOCLLjMPSqtJ.jpg"));
        } catch (Exception e) {
            System.out.println("No se pudo cargar la imagen de fondo: " + e.getMessage());
        }
        
        // Crear panel principal con imagen de fondo
        JPanel panelPrincipal = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (imagenFondo != null) {
                    // dibujar la imagen escalada al tamaño del panel
                    g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        
        // Usamos un layout que nos ayude a centrar los componentes vertical y horizontalmente.
        panelPrincipal.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Etiqueta de bienvenida, con una fuente un poco más grande para que destaque.
        JLabel bienvenidaLabel = new JLabel("Bienvenido al Analizador de Secuencias de ADN");
        bienvenidaLabel.setFont(new Font("Arial", Font.BOLD, 24));
        bienvenidaLabel.setForeground(Color.WHITE); // cambio el color a blanco para que se vea sobre la imagen
        
        // Botón para que el usuario pueda continuar.
        JButton entrarButton = new JButton("Entrar");
        
        // -- Lógica del botón --
        // Cuando el usuario haga clic, cerramos esta ventana y abrimos la de carga.
        entrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cerrar esta ventana
                dispose();
                // Abrir la ventana de carga
                SwingUtilities.invokeLater(() -> {
                    new CargarFrame().setVisible(true);
                });
            }
        });

        // -- Añadir componentes al panel usando GridBagConstraints para un mejor control --
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Un pequeño margen para que no esté pegado a los bordes.
        panelPrincipal.add(bienvenidaLabel, gbc);
        
        gbc.gridy = 1;
        panelPrincipal.add(entrarButton, gbc);
        
        // Añadir el panel principal a la ventana
        add(panelPrincipal);
    }
} 