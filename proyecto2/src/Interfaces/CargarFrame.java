package Interfaces;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import EDD.HashTable;
import EDD.SecuenciaADN;
import EDD.ArbolBB;
import EDD.PatronADN;
import Interfaces.MenuPrincipalFrame;

/**
 * Interfaz especializada para la carga y procesamiento inicial de secuencias de ADN.
 * 
 * <p>Esta ventana constituye el núcleo del proceso de importación de datos genéticos,
 * proporcionando una experiencia de usuario intuitiva y robusta para la selección,
 * visualización y análisis preliminar de archivos de secuencias de ADN. La clase
 * integra múltiples componentes de validación y procesamiento que garantizan la
 * integridad de los datos antes de su análisis computacional.</p>
 * 
 * <p><strong>Funcionalidades implementadas:</strong></p>
 * <ul>
 *   <li><strong>Selección inteligente de archivos:</strong> Utiliza {@link JFileChooser} 
 *       con filtros específicos para archivos de texto (.txt), facilitando la navegación
 *       del usuario y reduciendo errores de selección</li>
 *   <li><strong>Previsualización en tiempo real:</strong> Muestra el contenido completo
 *       del archivo seleccionado en un área de texto desplazable, permitiendo al usuario
 *       verificar la información antes del procesamiento</li>
 *   <li><strong>Validación automática de secuencias:</strong> Implementa filtros de
 *       limpieza que eliminan caracteres no válidos y valida que la secuencia tenga
 *       la longitud mínima requerida para el análisis</li>
 *   <li><strong>Procesamiento computacional avanzado:</strong> Construye automáticamente
 *       las estructuras de datos optimizadas ({@link HashTable} y {@link ArbolBB})
 *       necesarias para el análisis posterior</li>
 * </ul>
 * 
 * <p><strong>Algoritmo de procesamiento:</strong></p>
 * <ol>
 *   <li>Limpieza de la secuencia usando {@link SecuenciaADN#limpiarSecuencia(String)}</li>
 *   <li>Partición en tripletes no solapados (codones) con indexación precisa</li>
 *   <li>Inserción optimizada en tabla hash con gestión automática de colisiones</li>
 *   <li>Construcción del árbol binario de búsqueda ordenado por frecuencias</li>
 *   <li>Transición automática al módulo de análisis principal</li>
 * </ol>
 * 
 * <p>La ventana incluye manejo exhaustivo de errores, proporcionando retroalimentación
 * clara al usuario en caso de archivos corruptos, secuencias inválidas o problemas
 * de E/O. La navegación es bidireccional, permitiendo regresar a la pantalla de
 * bienvenida si es necesario.</p>
 * 
 * @author Luis
 * @see BienvenidaFrame
 * @see MenuPrincipalFrame
 * @see HashTable
 * @see ArbolBB
 * @see SecuenciaADN
 */
public class CargarFrame extends JFrame {

    /**
     * Área de texto principal para la visualización del contenido de las secuencias de ADN cargadas.
     * 
     * <p>Este componente proporciona una vista previa completa y desplazable del archivo
     * seleccionado, permitiendo al usuario verificar el contenido antes del procesamiento.
     * Está configurada como solo lectura para mantener la integridad de los datos originales
     * y incluye capacidades de desplazamiento automático para manejar secuencias extensas.</p>
     * 
     * @see JTextArea
     * @see JScrollPane
     */
    private JTextArea areaDeTexto;
    
    /**
     * Etiqueta informativa que muestra la ruta completa del archivo seleccionado actualmente.
     * 
     * <p>Esta etiqueta se actualiza automáticamente cada vez que el usuario selecciona
     * un nuevo archivo, proporcionando retroalimentación visual clara sobre qué archivo
     * está siendo procesado. Facilita la verificación por parte del usuario y mejora
     * la transparencia del proceso de carga.</p>
     * 
     * @see JLabel
     * @see JFileChooser
     */
    private JLabel etiquetaRuta;

    /**
     * Inicializa la interfaz completa de carga y procesamiento de secuencias de ADN.
     * 
     * <p>Este constructor implementa una arquitectura de interfaz de usuario sofisticada
     * que combina funcionalidad robusta con una experiencia de usuario intuitiva. Durante
     * la inicialización se configuran todos los componentes necesarios para el ciclo
     * completo de carga y procesamiento de datos genéticos.</p>
     * 
     * <p><strong>Componentes configurados automáticamente:</strong></p>
     * <ul>
     *   <li><strong>Selector de archivos:</strong> {@link JFileChooser} configurado con
     *       filtros específicos para archivos .txt y navegación optimizada</li>
     *   <li><strong>Vista previa de contenido:</strong> {@link JTextArea} de solo lectura
     *       con capacidades de desplazamiento para secuencias largas</li>
     *   <li><strong>Panel de control:</strong> Botones de acción con validaciones
     *       integradas y manejo de estados</li>
     *   <li><strong>Sistema de retroalimentación:</strong> Etiquetas informativas y
     *       diálogos de progreso para guiar al usuario</li>
     * </ul>
     * 
     * <p><strong>Lógica de eventos implementada:</strong></p>
     * <ul>
     *   <li><strong>Exploración de archivos:</strong> Apertura del selector con filtros
     *       automáticos y carga inmediata del contenido seleccionado</li>
     *   <li><strong>Navegación hacia atrás:</strong> Retorno seguro a {@link BienvenidaFrame}
     *       con limpieza apropiada de recursos</li>
     *   <li><strong>Procesamiento principal:</strong> Análisis completo de la secuencia
     *       con construcción de estructuras de datos optimizadas y transición automática
     *       al módulo de análisis</li>
     * </ul>
     * 
     * <p>El constructor incluye validaciones robustas que manejan desde archivos vacíos
     * hasta secuencias demasiado cortas, asegurando que solo datos válidos procedan
     * al análisis computacional. Todas las operaciones de E/O están envueltas en
     * bloques try-catch apropiados para proporcionar mensajes de error informativos.</p>
     * 
     * @throws HeadlessException si el entorno gráfico no está disponible
     * @throws SecurityException si no se tienen permisos para acceder al sistema de archivos
     * @see JFileChooser
     * @see FileNameExtensionFilter
     * @see SecuenciaADN#limpiarSecuencia(String)
     * @see HashTable#HashTable(int)
     * @see ArbolBB#ArbolBB(Object)
     */
    public CargarFrame() {
        // Configuración básica de la ventana
        setTitle("Proyecto 2 - Cargar Archivo de ADN");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Centrar la ventana
        
        setLayout(new BorderLayout(10, 10)); // Layout principal con un poco de espaciado.
        
        // Crear un panel principal con margen
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margen general

        // Panel superior para la selección de archivo
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton botonBuscar = new JButton("Buscar Archivo...");
        etiquetaRuta = new JLabel("Ningún archivo seleccionado.");
        panelSuperior.add(botonBuscar);
        panelSuperior.add(etiquetaRuta);

        // Área de texto para mostrar el contenido del archivo. Se añade a un JScrollPane
        // para que se pueda desplazar si el texto es muy largo.
        areaDeTexto = new JTextArea();
        areaDeTexto.setEditable(false); // El usuario no debe poder modificar el contenido.
        JScrollPane scrollPane = new JScrollPane(areaDeTexto);

        // Panel inferior para los botones de acción (Atrás y Cargar)
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton botonCargar = new JButton("Cargar y Analizar");
        JButton botonAtras = new JButton("Atrás");
        panelInferior.add(botonAtras);
        panelInferior.add(botonCargar);

        // Añadir los paneles al layout principal
        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);
        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);
        
        add(panelPrincipal);

        // --- Lógica de los botones ---

        // Botón para buscar el archivo. Abre un JFileChooser.
        botonBuscar.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos de Texto (*.txt)", "txt");
            fileChooser.setFileFilter(filtro);
            
            int resultado = fileChooser.showOpenDialog(this);
            if (resultado == JFileChooser.APPROVE_OPTION) {
                File archivoSeleccionado = fileChooser.getSelectedFile();
                etiquetaRuta.setText(archivoSeleccionado.getAbsolutePath());
                try {
                    // Leer y mostrar el contenido del archivo en el área de texto.
                    String contenido = new String(Files.readAllBytes(archivoSeleccionado.toPath()));
                    areaDeTexto.setText(contenido);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error al leer el archivo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Botón para volver a la pantalla de bienvenida.
        botonAtras.addActionListener(e -> {
            dispose();
            SwingUtilities.invokeLater(() -> {
                new BienvenidaFrame().setVisible(true);
            });
        });

        // Botón para cargar los datos y pasar al menú principal.
        // Aquí ocurre la magia: se lee el texto, se procesa y se crean las estructuras de datos.
        botonCargar.addActionListener(e -> {
            String contenido = areaDeTexto.getText();
            if (contenido.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay texto para analizar. Por favor, busca y carga un archivo.", "Archivo Vacío", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            try {
                // Limpiar la secuencia de cualquier caracter que no sea A, T, C, o G.
                String secuenciaLimpia = SecuenciaADN.limpiarSecuencia(contenido);
                
                if (secuenciaLimpia.length() < 3) {
                    JOptionPane.showMessageDialog(this, "La secuencia de ADN debe tener al menos 3 nucleótidos.", "Secuencia muy corta", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // 1. Crear la tabla hash. Un tamaño un poco más grande que la cantidad de tripletes ayuda a reducir colisiones.
                int tamañoTabla = (secuenciaLimpia.length() / 3) + 100;
                HashTable tablaHash = new HashTable(tamañoTabla);
                
                // Procesar la secuencia en tripletes no solapados (0-2, 3-5, 6-8, etc.)
                for (int i = 0; i <= secuenciaLimpia.length() - 3; i += 3) {
                    String triplete = secuenciaLimpia.substring(i, i + 3);
                    
                    // La posición es el índice donde comienza el triplete.
                    tablaHash.insertar(triplete, i);
                }
                
                // 2. Ahora, crear y llenar el Árbol Binario de Búsqueda para ordenar por frecuencia.
                ArbolBB arbolFrecuencias = new ArbolBB(null);
                for (String triplete : tablaHash.obtenerTodosLosTripletes()) {
                    PatronADN nodo = tablaHash.buscarTriplete(triplete);
                    if (nodo != null) {
                        arbolFrecuencias.insertar(nodo.getTriplete(), nodo.getFrecuencia());
                    }
                }

                // Informar al usuario del éxito y la data procesada.
                JOptionPane.showMessageDialog(this, 
                    "¡Archivo cargado y procesado exitosamente!\n" +
                    "Secuencia procesada: " + secuenciaLimpia.length() + " nucleótidos\n" +
                    "Tripletes encontrados: " + (secuenciaLimpia.length() / 3), 
                    "Carga exitosa", 
                    JOptionPane.INFORMATION_MESSAGE);
                
                // 3. Cerrar esta ventana y abrir la ventana del menú principal con los datos
                dispose();
                SwingUtilities.invokeLater(() -> {
                    MenuPrincipalFrame menuFrame = new MenuPrincipalFrame();
                    menuFrame.setEstructurasDeDatos(tablaHash, arbolFrecuencias);
                    menuFrame.setVisible(true);
                });
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Ocurrió un error al procesar la secuencia: " + ex.getMessage(), "Error de Procesamiento", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
} 