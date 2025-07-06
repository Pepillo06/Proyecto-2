/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *
 * @author Jabri
 */
public class NodoHash {
    public String triplete;
    public int frecuencia;
    private int[] posiciones; // Array estático para posiciones (sin ArrayList)
    public int posicionesCount; // Contador manual
    public NodoHash siguiente; // Encadenamiento

    public NodoHash(String triplete, int posicion) {
        this.triplete = triplete;
        this.frecuencia = 1;
        this.posiciones = new int[5]; // Tamaño inicial
        this.posiciones[0] = posicion;
        this.posicionesCount = 1;
        this.siguiente = null;
    }

    // Método para agregar una posición (sin List)
    public void agregarPosicion(int posicion) {
        if (posicionesCount == posiciones.length) {
            // Redimensionar manualmente
            int[] newPosiciones = new int[posiciones.length * 2];
            for (int i = 0; i < posicionesCount; i++) {
                newPosiciones[i] = posiciones[i];
            }
            posiciones = newPosiciones;
        }
        posiciones[posicionesCount++] = posicion;
        frecuencia++;
    }
    
    // Método para obtener todas las posiciones
    public String obtenerPosicionesFormateadas() {
        String resultado = "[";
        for (int i = 0; i < posicionesCount; i++) {
            resultado += posiciones[i];
            if (i < posicionesCount - 1) {
                resultado += ", ";
            }
        }
        return resultado + "]";
    }
}

