/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 * Representa la información de un patrón de ADN (triplete), y funciona como
 * un nodo para la HashTable.
 * 
 * Esta clase almacena el triplete, su frecuencia, una lista de sus posiciones
 * y una referencia a un nodo siguiente para manejar colisiones.
 * Utiliza un array de enteros con tamaño dinámico manual para ser eficiente
 * y cumplir con los requisitos del proyecto.
 * 
 * @author Jabri
 */
public class PatronADN {
    private String triplete;
    private int frecuencia;
    
    // Almacena las posiciones usando un array nativo - más eficiente que ArrayList
    private int[] posiciones;
    // Contador de posiciones válidas en el array
    private int posicionesCount;
    
    /**
     * Puntero al siguiente nodo en caso de colisión en la HashTable.
     */
    public PatronADN siguiente;
    
    /**
     * Constructor para crear un nuevo patrón.
     * @param triplete El triplete de ADN (ejemplo: "ATG")
     * @param posicionInicial La primera posición donde aparece
     */
    public PatronADN(String triplete, int posicionInicial) {
        this.triplete = triplete;
        this.frecuencia = 1;
        this.posiciones = new int[5]; // empezamos con 5, debería ser suficiente para la mayoría
        this.posiciones[0] = posicionInicial;
        this.posicionesCount = 1;
        this.siguiente = null;
    }
    
    /**
     * Agrega una nueva posición donde aparece este patrón.
     * Si el array de posiciones se llena, duplica su tamaño.
     * 
     * @param posicion La posición a agregar
     */
    public void agregarPosicion(int posicion) {
        // revisamos si necesitamos más espacio
        if (posicionesCount == posiciones.length) {
            // Redimensionar manualmente - duplicamos el tamaño
            int[] newPosiciones = new int[posiciones.length * 2];
            System.arraycopy(posiciones, 0, newPosiciones, 0, posicionesCount);
            posiciones = newPosiciones;
        }
        posiciones[posicionesCount++] = posicion;
        this.frecuencia++;
    }
    
    /**
     * Devuelve el triplete de ADN.
     */
    public String getTriplete() {
        return triplete;
    }
    
    /**
     * Obtiene la frecuencia del patrón.
     */
    public int getFrecuencia() {
        return frecuencia;
    }
    
    /**
     * Obtiene las posiciones como texto formateado.
     * @return String con las posiciones separadas por comas en formato "[pos1, pos2, ...]"
     */
    public String getPosicionesComoTexto() {
        if (posicionesCount == 0) {
            return "[]";
        }
        
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < posicionesCount; i++) {
            sb.append(posiciones[i]);
            if (i < posicionesCount - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
    
    /**
     * Obtiene el nombre del aminoácido correspondiente a este patrón.
     * 
     * Delega la lógica de conversión a la clase de utilidad MapaAminoacidos.
     * 
     * @return El nombre del aminoácido.
     */
    public String getAminoacido() {
        return MapaAminoacidos.obtenerAminoacido(triplete);
    }
    
    /**
     * Obtiene la abreviatura de 3 letras del aminoácido.
     */
    public String getAbreviatura3() {
        return MapaAminoacidos.obtenerAbreviatura3(triplete);
    }
    
    /**
     * Obtiene la abreviatura de 1 letra del aminoácido.
     */
    public String getAbreviatura1() {
        return MapaAminoacidos.obtenerAbreviatura1(triplete);
    }
    
    /**
     * Devuelve una representación completa del patrón en formato de texto.
     */
    @Override
    public String toString() {
        return "Patrón: " + triplete + 
               " | Frecuencia: " + frecuencia + 
               " | Posiciones: " + getPosicionesComoTexto() + 
               " | Aminoácido: " + getAminoacido();
    }
} 