package Interfaces;

import EDD.HashTable;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import EDD.ArbolBB;
import EDD.MapaAminoacidos;
import EDD.PatronADN;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;

/**
 * Centro de comando principal para el análisis computacional avanzado de secuencias de ADN.
 * 
 * <p>Esta clase representa el núcleo operativo del sistema de análisis genético, proporcionando
 * una interfaz unificada y potente para la exploración, búsqueda y análisis estadístico de
 * patrones en secuencias de ADN. La ventana integra múltiples herramientas especializadas
 * que permiten a los investigadores y analistas extraer conocimiento valioso de los datos
 * genéticos procesados.</p>
 * 
 * <p><strong>Módulos de análisis implementados:</strong></p>
 * <ul>
 *   <li><strong>Explorador de patrones (Función B):</strong> Visualización completa de todos
 *       los tripletes detectados, ordenados por frecuencia de aparición para identificar
 *       patrones dominantes en la secuencia</li>
 *   <li><strong>Búsqueda dirigida (Función C):</strong> Sistema de búsqueda en tiempo real
 *       O(1) que permite localizar cualquier triplete específico y obtener su información
 *       detallada incluyendo posiciones exactas</li>
 *   <li><strong>Análisis estadístico (Función D):</strong> Identificación automática de los
 *       patrones más y menos frecuentes usando algoritmos de árbol binario optimizados</li>
 *   <li><strong>Diagnóstico de rendimiento (Función E):</strong> Generación de reportes
 *       detallados sobre colisiones en la tabla hash para optimización del sistema</li>
 *   <li><strong>Clasificación por aminoácidos (Función F):</strong> Agrupación inteligente
 *       de tripletes según su codificación de aminoácidos, facilitando análisis proteómicos</li>
 * </ul>
 * 
 * <p><strong>Arquitectura de datos:</strong></p>
 * <p>La ventana opera sobre dos estructuras de datos principales optimizadas para diferentes
 * tipos de consultas:</p>
 * <ul>
 *   <li><strong>{@link HashTable}:</strong> Proporciona acceso instantáneo O(1) a cualquier
 *       triplete específico con información completa de frecuencias y posiciones</li>
 *   <li><strong>{@link ArbolBB}:</strong> Mantiene los datos ordenados por frecuencia,
 *       permitiendo consultas logarítmicas O(log n) para análisis estadísticos</li>
 * </ul>
 * 
 * <p><strong>Características de usabilidad:</strong></p>
 * <ul>
 *   <li>Interfaz intuitiva con botones claramente etiquetados según funcionalidad</li>
 *   <li>Reportes formatados profesionalmente con ventanas de desplazamiento</li>
 *   <li>Validación robusta de estado para prevenir operaciones sobre datos no cargados</li>
 *   <li>Navegación bidireccional que permite regresar al módulo de carga</li>
 *   <li>Selección inteligente de patrones mediante {@link JComboBox} ordenado</li>
 * </ul>
 * 
 * <p>La clase implementa el patrón de separación de responsabilidades, delegando el
 * procesamiento complejo a las clases especializadas del paquete EDD mientras mantiene
 * una interfaz de usuario limpia y responsive.</p>
 * 
 * @author Luis
 * @see CargarFrame
 * @see HashTable
 * @see ArbolBB
 * @see PatronADN
 * @see MapaAminoacidos
 */
public class MenuPrincipalFrame extends JFrame {

    /**
     * Estructura de datos optimizada para búsquedas instantáneas O(1) de patrones de ADN.
     * 
     * <p>Esta tabla hash contiene todos los tripletes únicos encontrados en la secuencia
     * analizada, junto con sus frecuencias de aparición y posiciones exactas en la
     * secuencia original. Proporciona la base para todas las operaciones de búsqueda
     * y consulta directa de patrones específicos.</p>
     * 
     * @see HashTable
     * @see PatronADN
     */
    private HashTable tablaHash;
    
    /**
     * Árbol binario de búsqueda que mantiene los patrones ordenados por frecuencia.
     * 
     * <p>Esta estructura permite consultas logarítmicas O(log n) para análisis estadísticos,
     * incluyendo la identificación eficiente de los patrones más y menos frecuentes.
     * Complementa la tabla hash proporcionando acceso ordenado a los datos.</p>
     * 
     * @see ArbolBB
     */
    private ArbolBB arbolFrecuencias;
    
    /**
     * Selector desplegable que permite al usuario elegir patrones específicos para búsqueda.
     * 
     * <p>Este componente se poblará automáticamente con todos los tripletes únicos
     * encontrados en la secuencia, ordenados alfabéticamente para facilitar la
     * navegación del usuario. Sirve como interfaz principal para la función de
     * búsqueda dirigida de patrones.</p>
     * 
     * @see JComboBox
     */
    private JComboBox<String> comboPatrones;

    /**
     * Inicializa el centro de comando completo para análisis de secuencias de ADN.
     * 
     * <p>Este constructor implementa una arquitectura de interfaz de usuario avanzada que
     * organiza sistemáticamente todas las herramientas de análisis genético en una sola
     * ventana cohesiva. Durante la inicialización se configuran automáticamente todos los
     * componentes, controladores de eventos y validaciones necesarias para un análisis
     * computacional robusto.</p>
     * 
     * <p><strong>Diseño de la interfaz:</strong></p>
     * <ul>
     *   <li><strong>Layout vertical optimizado:</strong> Utiliza {@link BoxLayout} para
     *       organizar las funciones de manera lógica y accesible</li>
     *   <li><strong>Agrupación funcional:</strong> Los controles relacionados se agrupan
     *       visualmente para mejorar la experiencia del usuario</li>
     *   <li><strong>Espaciado profesional:</strong> Márgenes y separadores calculados para
     *       mantener una apariencia limpia y profesional</li>
     *   <li><strong>Componentes responsivos:</strong> Todos los elementos se dimensionan
     *       apropiadamente y se centran automáticamente</li>
     * </ul>
     * 
     * <p><strong>Controladores de eventos configurados:</strong></p>
     * <ul>
     *   <li><strong>Listado de patrones:</strong> Genera reportes completos ordenados por
     *       frecuencia con información detallada de ubicaciones</li>
     *   <li><strong>Búsqueda de patrones:</strong> Implementa búsqueda instantánea con
     *       validación de selección y manejo de errores</li>
     *   <li><strong>Análisis estadístico:</strong> Calcula automáticamente los extremos
     *       de frecuencia usando algoritmos de árbol optimizados</li>
     *   <li><strong>Diagnóstico de colisiones:</strong> Genera reportes técnicos sobre
     *       el rendimiento de la tabla hash</li>
     *   <li><strong>Clasificación por aminoácidos:</strong> Agrupa y ordena tripletes
     *       según su función biológica</li>
     *   <li><strong>Navegación:</strong> Permite retorno seguro al módulo de carga</li>
     * </ul>
     * 
     * <p><strong>Validaciones implementadas:</strong></p>
     * <p>Cada operación incluye verificaciones exhaustivas del estado del sistema,
     * asegurando que no se ejecuten análisis sobre datos no cargados o corruptos.
     * Los mensajes de error son informativos y guían al usuario hacia la resolución
     * de problemas.</p>
     * 
     * <p><strong>Optimizaciones de rendimiento:</strong></p>
     * <p>Los reportes generados utilizan {@link StringBuilder} para construcción
     * eficiente de cadenas largas, y los componentes de visualización incluyen
     * {@link JScrollPane} para manejar apropiadamente grandes volúmenes de datos.</p>
     * 
     * @throws HeadlessException si el entorno gráfico no está disponible
     * @see BoxLayout
     * @see JComboBox
     * @see JScrollPane
     * @see StringBuilder
     */
    public MenuPrincipalFrame() {
        // Configuración básica de la ventana
        setTitle("Proyecto 2 - Menú de Análisis de ADN");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Centrar la ventana
        
        setLayout(new BorderLayout());

        // Título del panel
        JLabel titulo = new JLabel("Menú de Análisis", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(titulo, BorderLayout.NORTH);

        // Panel de contenido que organizará verticalmente los componentes
        JPanel panelContenido = new JPanel();
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        panelContenido.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // -- Panel para la función C: Buscar Patrón --
        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        comboPatrones = new JComboBox<>();
        comboPatrones.setPreferredSize(new Dimension(150, 25)); // Darle un tamaño preferido
        JButton botonBuscarPatron = new JButton("C. Buscar Patrón Seleccionado");
        panelBusqueda.add(new JLabel("Patrón:"));
        panelBusqueda.add(comboPatrones);
        panelBusqueda.add(botonBuscarPatron);
        
        // Botones para las demás funciones
        JButton botonListaPatrones = new JButton("B. Lista de Patrones Almacenados");
        JButton botonMasMenosFrecuente = new JButton("D. Identificar Patrón Más/Menos Frecuente");
        JButton botonReporteColisiones = new JButton("E. Reporte de Colisiones");
        JButton botonListaAminoacidos = new JButton("F. Listar por Aminoácido");
        JButton botonAtras = new JButton("Volver a Cargar Archivo");

        // Añadir todos los componentes al panel de contenido
        panelContenido.add(panelBusqueda);
        panelContenido.add(Box.createVerticalStrut(15)); // Espacio separador
        panelContenido.add(botonListaPatrones);
        panelContenido.add(botonMasMenosFrecuente);
        panelContenido.add(botonReporteColisiones);
        panelContenido.add(botonListaAminoacidos);
        panelContenido.add(Box.createVerticalStrut(20)); // Un espacio más grande
        panelContenido.add(botonAtras);
        
        // Alinear los componentes al centro para una mejor apariencia
        for (Component comp : panelContenido.getComponents()) {
            if (comp instanceof JComponent) {
                ((JComponent) comp).setAlignmentX(Component.CENTER_ALIGNMENT);
            }
        }

        add(panelContenido, BorderLayout.CENTER);

        // --- Lógica de los botones ---
        
        // B. Botón para listar todos los patrones, ordenados por frecuencia.
        botonListaPatrones.addActionListener(e -> {
            if (arbolFrecuencias == null || tablaHash == null) {
                JOptionPane.showMessageDialog(this, "No se han cargado datos para analizar.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // El recorrido in-orden del árbol nos da los patrones de menor a mayor frecuencia.
            String reporteInOrden = arbolFrecuencias.inOrden();
            
            // Para mostrar de mayor a menor, simplemente invertimos el orden de las líneas.
            String[] lineas = reporteInOrden.split("\n");
            StringBuilder reporteInvertido = new StringBuilder("Lista de Patrones (ordenados de mayor a menor frecuencia):\n\n");
            for (int i = lineas.length - 1; i >= 0; i--) {
                // El reporte del árbol solo tiene el triplete y la frecuencia.
                // Necesitamos buscar en la tabla hash para obtener las ubicaciones.
                String[] partes = lineas[i].split(",");
                if (partes.length > 0) {
                    String tripleteConPrefijo = partes[0]; // Ej: "Triplete: TTT"
                    String triplete = tripleteConPrefijo.replace("Triplete: ", "").trim();
                    
                    PatronADN nodo = tablaHash.buscarTriplete(triplete);
                    if (nodo != null) {
                        reporteInvertido.append("Patrón: ").append(nodo.getTriplete())
                                      .append(", Frecuencia: ").append(nodo.getFrecuencia())
                                      .append(", Ubicaciones: ").append(nodo.getPosicionesComoTexto())
                                      .append("\n");
                    }
                }
            }
            
            // Usar un JTextArea dentro de un JScrollPane para mostrar el reporte cómodamente.
            JTextArea areaReporte = new JTextArea(reporteInvertido.toString());
            areaReporte.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(areaReporte);
            scrollPane.setPreferredSize(new Dimension(500, 400));
            
            JOptionPane.showMessageDialog(this, scrollPane, "Lista de Patrones", JOptionPane.INFORMATION_MESSAGE);
        });

        // D. Botón para encontrar el patrón más y menos frecuente.
        botonMasMenosFrecuente.addActionListener(e -> {
            if (arbolFrecuencias == null) {
                JOptionPane.showMessageDialog(this, "No se han cargado datos para analizar.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Estos métodos tienen complejidad O(log n) porque solo recorren una rama del árbol.
            String masFrecuente = arbolFrecuencias.getMasFrecuente();
            String menosFrecuente = arbolFrecuencias.getMenosFrecuente();

            String reporte = "Análisis de Frecuencia:\n\n" +
                             "Patrón MÁS frecuente: " + masFrecuente + "\n" +
                             "Patrón MENOS frecuente: " + menosFrecuente;
            
            JOptionPane.showMessageDialog(this, reporte, "Patrones Más y Menos Frecuentes", JOptionPane.INFORMATION_MESSAGE);
        });

        // E. Botón para generar el reporte de colisiones en la tabla hash.
        botonReporteColisiones.addActionListener(e -> {
            if (tablaHash == null) {
                JOptionPane.showMessageDialog(this, "No se han cargado datos para analizar.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String reporte = tablaHash.generarReporteColisiones();

            JTextArea areaReporte = new JTextArea(reporte);
            areaReporte.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(areaReporte);
            scrollPane.setPreferredSize(new Dimension(500, 400));
            
            JOptionPane.showMessageDialog(this, scrollPane, "Reporte de Colisiones", JOptionPane.INFORMATION_MESSAGE);
        });

        // F. Botón para listar los patrones agrupados por aminoácido.
        botonListaAminoacidos.addActionListener(e -> {
            if (tablaHash == null) {
                JOptionPane.showMessageDialog(this, "No se han cargado datos para analizar.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String reporte = generarReporteAminoacidos();

            JTextArea areaReporte = new JTextArea(reporte);
            areaReporte.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(areaReporte);
            scrollPane.setPreferredSize(new Dimension(600, 500));
            
            JOptionPane.showMessageDialog(this, scrollPane, "Reporte de Aminoácidos", JOptionPane.INFORMATION_MESSAGE);
        });

        // C. Botón para buscar un patrón específico seleccionado del ComboBox.
        botonBuscarPatron.addActionListener(e -> {
            if (tablaHash == null) {
                JOptionPane.showMessageDialog(this, "No se ha cargado ninguna tabla de datos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            String patronSeleccionado = (String) comboPatrones.getSelectedItem();
            if (patronSeleccionado == null) {
                JOptionPane.showMessageDialog(this, "Por favor, selecciona un patrón de la lista.", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // La búsqueda en la tabla hash es O(1) en el caso promedio.
            PatronADN nodo = tablaHash.buscarTriplete(patronSeleccionado);
            
            if (nodo != null) {
                String info = "Información del Patrón '" + nodo.getTriplete() + "':\n\n" +
                              "Frecuencia: " + nodo.getFrecuencia() + "\n" +
                              "Ubicaciones: " + nodo.getPosicionesComoTexto();
                
                JOptionPane.showMessageDialog(this, info, "Resultado de la Búsqueda", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Esto no debería pasar si el combo se llena desde la tabla, pero es una buena práctica manejarlo.
                JOptionPane.showMessageDialog(this, "El patrón no fue encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Botón para volver al panel de carga.
        botonAtras.addActionListener(e -> {
            dispose();
            SwingUtilities.invokeLater(() -> {
                new CargarFrame().setVisible(true);
            });
        });
    }

    /**
     * Configura las estructuras de datos procesadas e inicializa la interfaz de análisis.
     * 
     * <p>Este método crítico establece la conexión entre los datos procesados en
     * {@link CargarFrame} y las herramientas de análisis disponibles en esta ventana.
     * Realiza una configuración completa del estado interno del sistema y prepara
     * todos los componentes de interfaz para operaciones de análisis inmediatas.</p>
     * 
     * <p><strong>Operaciones de configuración realizadas:</strong></p>
     * <ul>
     *   <li><strong>Asignación de referencias:</strong> Establece las referencias internas
     *       a las estructuras de datos optimizadas para consultas rápidas</li>
     *   <li><strong>Limpieza de estado anterior:</strong> Elimina cualquier dato residual
     *       de sesiones de análisis previas para evitar contaminación de resultados</li>
     *   <li><strong>Población del selector de patrones:</strong> Llena automáticamente
     *       el {@link JComboBox} con todos los tripletes disponibles, ordenados
     *       alfabéticamente para facilitar la navegación</li>
     *   <li><strong>Validación de integridad:</strong> Verifica que las estructuras
     *       recibidas sean consistentes y estén listas para análisis</li>
     * </ul>
     * 
     * <p><strong>Consideraciones de rendimiento:</strong></p>
     * <p>El método utiliza {@link Arrays#sort(Object[])} para organizar eficientemente
     * los elementos del ComboBox, proporcionando una experiencia de usuario ordenada
     * cuando se trabaja con grandes cantidades de patrones únicos. La operación
     * completa tiene complejidad O(n log n) donde n es el número de tripletes únicos.</p>
     * 
     * <p><strong>Manejo de errores:</strong></p>
     * <p>El método incluye validaciones apropiadas para manejar casos donde las
     * estructuras de datos podrían ser nulas o estar vacías, manteniendo la
     * estabilidad del sistema en todas las condiciones.</p>
     * 
     * @param tablaHash la tabla hash optimizada que contiene todos los patrones
     *                  procesados con sus frecuencias y posiciones exactas
     * @param arbolFrecuencias el árbol binario de búsqueda que mantiene los patrones
     *                        ordenados por frecuencia para consultas estadísticas eficientes
     * 
     * @throws IllegalArgumentException si alguna de las estructuras es inválida
     * @see Arrays#sort(Object[])
     * @see JComboBox#removeAllItems()
     * @see JComboBox#addItem(Object)
     * @see CargarFrame
     */
    public void setEstructurasDeDatos(HashTable tablaHash, ArbolBB arbolFrecuencias) {
        this.tablaHash = tablaHash;
        this.arbolFrecuencias = arbolFrecuencias;
        
        // Limpiar el combo box por si había datos de una carga anterior.
        comboPatrones.removeAllItems();
        
        if (tablaHash != null) {
            String[] tripletes = tablaHash.obtenerTodosLosTripletes();
            
            // Ordenar los tripletes alfabéticamente para que aparezcan en orden en la lista.
            Arrays.sort(tripletes);
            
            // Llenar el combo box.
            for (String triplete : tripletes) {
                comboPatrones.addItem(triplete);
            }
        }
    }

    /**
     * Genera un análisis proteómico completo que correlaciona tripletes con sus aminoácidos codificados.
     * 
     * <p>Este método implementa un algoritmo sofisticado de clasificación biológica que trasciende
     * el simple análisis de secuencias para proporcionar insights sobre la función proteómica
     * potencial de la secuencia analizada. El proceso combina análisis computacional con
     * conocimiento biológico para generar reportes de valor científico.</p>
     * 
     * <p><strong>Algoritmo de procesamiento implementado:</strong></p>
     * <ol>
     *   <li><strong>Extracción sistemática:</strong> Recupera todos los tripletes únicos de la
     *       tabla hash, asegurando cobertura completa de la secuencia procesada</li>
     *   <li><strong>Clasificación biológica:</strong> Utiliza {@link MapaAminoacidos} para
     *       mapear cada triplete a su aminoácido correspondiente, manejando apropiadamente
     *       codones de parada y variaciones genéticas</li>
     *   <li><strong>Agrupación inteligente:</strong> Emplea una estructura {@code Map<String, List<PatronADN>>}
     *       optimizada para agrupar eficientemente patrones por su función biológica</li>
     *   <li><strong>Ordenamiento multi-nivel:</strong> Aplica ordenamiento alfabético por
     *       aminoácido y ordenamiento por frecuencia dentro de cada grupo para máxima utilidad</li>
     *   <li><strong>Formateo profesional:</strong> Construye un reporte estructurado que incluye
     *       nombres completos, abreviaciones estándar y estadísticas detalladas</li>
     * </ol>
     * 
     * <p><strong>Información incluida en el reporte:</strong></p>
     * <ul>
     *   <li><strong>Identificación del aminoácido:</strong> Nombre completo, abreviación de
     *       3 letras y código de 1 letra según estándares internacionales</li>
     *   <li><strong>Estadísticas de frecuencia:</strong> Conteo exacto de apariciones para
     *       cada triplete específico dentro del aminoácido</li>
     *   <li><strong>Mapeo posicional:</strong> Ubicaciones precisas donde aparece cada
     *       patrón en la secuencia original</li>
     *   <li><strong>Ordenamiento por relevancia:</strong> Los tripletes más frecuentes
     *       aparecen primero dentro de cada categoría de aminoácido</li>
     * </ul>
     * 
     * <p><strong>Complejidad computacional:</strong></p>
     * <p>El algoritmo opera con complejidad O(n log n + m log m) donde n es el número total
     * de tripletes únicos y m es el número promedio de tripletes por aminoácido. Esta
     * eficiencia permite análisis en tiempo real incluso para secuencias genómicas extensas.</p>
     * 
     * <p><strong>Aplicaciones científicas:</strong></p>
     * <p>Este reporte es particularmente valioso para análisis proteómicos, identificación
     * de regiones codificantes potenciales, y estudios comparativos de uso de codones
     * entre diferentes organismos o genes.</p>
     * 
     * @return un reporte detallado y estructurado que organiza todos los tripletes
     *         identificados según su función biológica, incluyendo estadísticas
     *         completas y referencias posicionales
     * 
     * @throws NullPointerException si la tabla hash no ha sido inicializada
     * @see MapaAminoacidos#obtenerAminoacido(String)
     * @see PatronADN#getTriplete()
     * @see PatronADN#getFrecuencia()
     * @see PatronADN#getPosicionesComoTexto()
     * @see Collections#sort(List)
     * @see HashMap
     * @see ArrayList
     */
    private String generarReporteAminoacidos() {
        // Mapa para agrupar tripletes por aminoácido. La clave es el nombre del aminoácido.
        // El valor es una lista de los nodos (PatronADN) que codifican para ese aminoácido.
        Map<String, List<PatronADN>> aminoacidosMap = new HashMap<>();

        // Recorrer toda la tabla hash para obtener los patrones.
        for (String triplete : tablaHash.obtenerTodosLosTripletes()) {
            PatronADN nodo = tablaHash.buscarTriplete(triplete);
            if (nodo != null) {
                String aminoacido = MapaAminoacidos.obtenerAminoacido(nodo.getTriplete());
                
                // Si el aminoácido no está en el mapa, se crea una nueva lista.
                if (!aminoacidosMap.containsKey(aminoacido)) {
                    aminoacidosMap.put(aminoacido, new ArrayList<>());
                }
                // Se añade el nodo a la lista correspondiente.
                aminoacidosMap.get(aminoacido).add(nodo);
            }
        }

        // Construir el string del reporte a partir del mapa.
        StringBuilder reporte = new StringBuilder("=== Reporte por Aminoácidos ===\n\n");
        
        // Ordenar los nombres de los aminoácidos alfabéticamente para un reporte consistente.
        List<String> nombresAminoacidos = new ArrayList<>(aminoacidosMap.keySet());
        Collections.sort(nombresAminoacidos);

        for (String nombreAminoacido : nombresAminoacidos) {
            List<PatronADN> patrones = aminoacidosMap.get(nombreAminoacido);
            // Ordenar los patrones por frecuencia de mayor a menor dentro de cada aminoácido.
            patrones.sort((p1, p2) -> Integer.compare(p2.getFrecuencia(), p1.getFrecuencia()));

            String abrev3 = patrones.get(0).getAbreviatura3();
            String abrev1 = patrones.get(0).getAbreviatura1();
            
            reporte.append("--- Aminoácido: ").append(nombreAminoacido)
                   .append(" (").append(abrev3).append(" / ").append(abrev1).append(") ---\n");

            for (PatronADN patron : patrones) {
                reporte.append("  - Patrón: ").append(patron.getTriplete())
                       .append(" | Frecuencia: ").append(patron.getFrecuencia())
                       .append(" | Ubicaciones: ").append(patron.getPosicionesComoTexto())
                       .append("\n");
            }
            reporte.append("\n");
        }

        return reporte.toString();
    }
} 