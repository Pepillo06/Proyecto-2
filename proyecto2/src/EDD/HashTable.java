/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 * Implementación de una Tabla de Dispersión (Hash Table) con manejo de colisiones.
 * 
 * Esta estructura de datos almacena los patrones de ADN (tripletes) y gestiona
 * sus ocurrencias (frecuencia y posiciones) en la secuencia principal.
 * Las colisiones se manejan mediante encadenamiento, es decir, cada posición
 * de la tabla puede contener una lista enlazada de nodos (PatronADN).
 * 
 * @author Jabri
 */
public class HashTable {
    private int size;
    private PatronADN[] tabla;

    /**
     * Constructor de la HashTable.
     * 
     * @param size El tamaño inicial del array que servirá como tabla. Un tamaño
     *             adecuado ayuda a minimizar colisiones.
     */
    public HashTable(int size) {
        this.size = size;
        this.tabla = new PatronADN[size];
    }
 
    /**
     * Calcula el valor hash para un triplete de ADN.
     * 
     * Utiliza un algoritmo común de hashing para strings que produce una buena
     * distribución de valores. Se asegura de que el resultado sea siempre positivo.
     * 
     * @param triplete El string (patrón de 3 letras) para el cual se calculará el hash.
     * @return Un valor entero no negativo que representa el hash del triplete.
     */
    public int calcularHash(String triplete) {
        int hash = 0;
        for (int i = 0; i < triplete.length(); i++) {
            hash = (hash * 31) + triplete.charAt(i);
        }
        return hash < 0 ? -hash : hash; // Asegurar que el hash es positivo
    }

    /**
     * Inserta un triplete en la tabla hash.
     * 
     * Calcula el índice usando el valor hash del triplete.
     * Si el triplete ya existe en la tabla, simplemente actualiza su frecuencia y
     * añade la nueva posición. Si no existe, crea un nuevo PatronADN y lo
     * añade a la tabla, manejando la colisión si es necesario (añadiéndolo al
     * final de la lista enlazada en ese índice).
     * 
     * @param triplete El patrón de ADN a insertar.
     * @param posicion La ubicación (índice) donde se encontró este patrón en la secuencia principal.
     */
    public void insertar(String triplete, int posicion) {
        int indice = calcularHash(triplete) % size;
       
        if (tabla[indice] == null) {
            // Si no hay nada en este índice, creamos el primer nodo
            tabla[indice] = new PatronADN(triplete, posicion);
        } else {
            // Si ya hay algo, hay una posible colisión. Recorremos la lista enlazada
            PatronADN actual = tabla[indice];
            while (actual != null) {
                if (actual.getTriplete().equals(triplete)) {
                    // El triplete ya existe, solo añadimos la nueva posición y aumentamos la frecuencia
                    actual.agregarPosicion(posicion);
                    return; // Terminamos la ejecución del método
                }
                if (actual.siguiente == null) {
                    // Llegamos al final de la lista, así que aquí insertamos el nuevo nodo
                    actual.siguiente = new PatronADN(triplete, posicion);
                    return; // Terminamos
                }
                actual = actual.siguiente;
            }
        }
    }

    /**
     * Genera un reporte completo con frecuencias y colisiones.
     * 
     * Es un método de conveniencia que une los reportes de frecuencia y colisiones.
     * 
     * @return Un String con el reporte formateado.
     * @deprecated Este método puede ser confuso. Es mejor usar los métodos específicos
     *             de reporte como generarReporteColisiones() o recorrer
     *             la tabla para obtener las frecuencias.
     */
    @Deprecated
    public String generarReporte() {
        String reporte = "=== REPORTE ===\n";
        reporte += "Total tripletes: " + contarTripletes() + "\n\n";
        reporte += generarFrecuencias();
        reporte += "\n" + generarColisiones();
        return reporte;
    }

    /**
     * Cuenta el número total de tripletes únicos almacenados en la tabla.
     */
    private int contarTripletes() {
        int total = 0;
        for (int i = 0; i < size; i++) {
            PatronADN actual = tabla[i];
            while (actual != null) {
                total++;
                actual = actual.siguiente;
            }
        }
        return total;
    }

    /**
     * Genera un listado de todos los tripletes con sus frecuencias y posiciones.
     */
    private String generarFrecuencias() {
        String resultado = "FRECUENCIAS:\n";
        for (int i = 0; i < size; i++) {
            PatronADN actual = tabla[i];
            while (actual != null) {
                resultado += actual.getTriplete() + ": " + actual.getFrecuencia() + " veces " +
                           actual.getPosicionesComoTexto() + "\n";
                actual = actual.siguiente;
            }
        }
        return resultado;
    }

    /**
     * Genera un reporte simple que cuenta el número de colisiones por casilla.
     */
    private String generarColisiones() {
        String resultado = "COLISIONES:\n";
        int total = 0;
       
        for (int i = 0; i < size; i++) {
            int colisiones = 0;
            PatronADN actual = tabla[i];
           
            if (actual != null) {
                while (actual.siguiente != null) {
                    colisiones++;
                    actual = actual.siguiente;
                }
            }
           
            if (colisiones > 0) {
                total += colisiones;
                resultado += "Casilla " + i + ": " + colisiones + " colisiones\n";
            }
        }
       
        return resultado + "\nTotal colisiones: " + total;
    }
    
    /**
     * Genera un reporte detallado que muestra qué tripletes colisionaron en cada índice.
     * 
     * @return Un String con el reporte de colisiones detallado.
     */
    public String generarReporteColisiones() {
        StringBuilder reporte = new StringBuilder("=== Reporte de Colisiones ===\n\n");
        boolean huboColisiones = false;

        for (int i = 0; i < size; i++) {
            PatronADN actual = tabla[i];
            // Una colisión ocurre si hay más de un nodo en el mismo índice
            if (actual != null && actual.siguiente != null) {
                huboColisiones = true;
                reporte.append("Índice ").append(i).append(":\n");
                
                // Recorrer la lista enlazada en este índice
                while (actual != null) {
                    reporte.append("  -> Triplete: ").append(actual.getTriplete()).append("\n");
                    actual = actual.siguiente;
                }
                reporte.append("\n");
            }
        }

        if (!huboColisiones) {
            reporte.append("¡Felicidades! No se encontraron colisiones en la tabla hash.");
        }

        return reporte.toString();
    }

    /**
     * Busca un triplete específico en la tabla hash.
     * 
     * Esta operación tiene una complejidad promedio de O(1), que es la principal
     * ventaja de usar una tabla hash.
     * 
     * @param triplete El triplete a buscar.
     * @return El PatronADN que contiene el triplete, o null si no se encuentra.
     */
    public PatronADN buscarTriplete(String triplete) {
        int indice = calcularHash(triplete) % size;
        PatronADN actual = tabla[indice];
        
        while (actual != null) {
            if (actual.getTriplete().equals(triplete)) {
                return actual;
            }
            actual = actual.siguiente;
        }
        return null;
    }
    
    /**
     * Devuelve un array con todos los tripletes únicos almacenados en la tabla.
     * 
     * El array resultante está ordenado alfabéticamente para facilitar su uso
     * en componentes de UI como un JComboBox.
     * 
     * @return Un array de String con todos los tripletes únicos, ordenado.
     */
    public String[] obtenerTodosLosTripletes() {
        // Usamos una lista dinámica para que sea más fácil añadir los tripletes
        java.util.List<String> listaTripletes = new java.util.ArrayList<>();
        for (int i = 0; i < size; i++) {
            PatronADN actual = tabla[i];
            while (actual != null) {
                listaTripletes.add(actual.getTriplete());
                actual = actual.siguiente;
            }
        }
        
        // Convertimos la lista a un array y lo ordenamos
        String[] tripletesArray = listaTripletes.toArray(new String[0]);
        java.util.Arrays.sort(tripletesArray);
        
        return tripletesArray;
    }
    
    /**
     * Obtiene el triplete más frecuente recorriendo toda la tabla.
     * 
     * Nota: Esta operación es O(n), donde n es el número de elementos en la tabla.
     * Para una búsqueda más eficiente de frecuencias, se debe usar el ArbolBB.
     * 
     * @return Un String con información del triplete más frecuente.
     */
    public String getTripleteMasFrecuente() {
        String masFrecuente = "";
        int maxFrecuencia = 0;
        
        for (int i = 0; i < size; i++) {
            PatronADN actual = tabla[i];
            while (actual != null) {
                if (actual.getFrecuencia() > maxFrecuencia) {
                    maxFrecuencia = actual.getFrecuencia();
                    masFrecuente = actual.getTriplete();
                }
                actual = actual.siguiente;
            }
        }
        
        return masFrecuente + " (Frecuencia: " + maxFrecuencia + ")";
    }
    
    /**
     * Obtiene el triplete menos frecuente recorriendo toda la tabla.
     * 
     * Nota: Esta operación es O(n). Para una búsqueda más eficiente, usar el ArbolBB.
     * 
     * @return Un String con información del triplete menos frecuente.
     */
    public String getTripleteMenosFrecuente() {
        String menosFrecuente = "";
        int minFrecuencia = Integer.MAX_VALUE;
        
        if (this.contarTripletes() == 0) {
            return "No hay datos.";
        }
        
        for (int i = 0; i < size; i++) {
            PatronADN actual = tabla[i];
            while (actual != null) {
                if (actual.getFrecuencia() < minFrecuencia) {
                    minFrecuencia = actual.getFrecuencia();
                    menosFrecuente = actual.getTriplete();
                }
                actual = actual.siguiente;
            }
        }
        
        return menosFrecuente + " (Frecuencia: " + minFrecuencia + ")";
    }
    
    /**
     * Cuenta el número total de colisiones en la tabla.
     * Una colisión se cuenta si un nodo tiene un nodo siguiente.
     */
    public int contarColisiones() {
        int contador = 0;
        for (int i = 0; i < size; i++) {
            PatronADN actual = tabla[i];
            if (actual != null) {
                while(actual.siguiente != null) {  // pequeño error de spacing intencional
                    contador++;
                    actual = actual.siguiente;
                }
            }
        }
        return contador;
    }
}