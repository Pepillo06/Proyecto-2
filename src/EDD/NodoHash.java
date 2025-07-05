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
    public final String triplete;
    public int frecuencia;
    private int[] posiciones;
    public int contadorPosiciones;
    public NodoHash siguiente;
    
    public NodoHash(String triplete, int posicion) {
        this.triplete = triplete;
        this.frecuencia = 1;
        this.posiciones = new int[5];  // Tamaño inicial de 5
        this.posiciones[0] = posicion;
        this.contadorPosiciones = 1;
        this.siguiente = null;
    }

    public void agregarPosicion(int posicion) {
        if (contadorPosiciones == posiciones.length) {
            // Redimensionar manualmente
            int[] nuevoArray = new int[posiciones.length * 2];
            for (int i = 0; i < posiciones.length; i++) {
                nuevoArray[i] = posiciones[i];
            }
            posiciones = nuevoArray;
        }
        posiciones[contadorPosiciones++] = posicion;
        frecuencia++;
    }

    // Nuevo método para obtener la primera posición
    public int obtenerPrimeraPosicion() {
        if (contadorPosiciones > 0) {
            return posiciones[0];
        }
        return -1;  // Valor por defecto si no hay posiciones
    }

    // Método para obtener todas las posiciones
    public String obtenerPosicionesFormateadas() {
        String resultado = "[";
        for (int i = 0; i < contadorPosiciones; i++) {
            resultado += posiciones[i];
            if (i < contadorPosiciones - 1) {
                resultado += ", ";
            }
        }
        return resultado + "]";
    }
}