/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *
 * @author Jabri
 */
public class HashTable {
    private int size;
    private NodoHash[] tabla;
    private NodoHash cabeza;

    public HashTable(int size) {
        this.size = size;
        this.tabla = new NodoHash[size];
    }
 
    public int calcularHash(String triplete) {
        int hash = 0;
        for (int i = 0; i < triplete.length(); i++) {
            hash = (hash * 31) + triplete.charAt(i);
        }
        return hash < 0 ? -hash : hash; // Valor absoluto manual
    }

    public void insertar(String triplete, int posicion) {
        int indice = calcularHash(triplete) % size;
        NodoHash nuevo = new NodoHash(triplete, posicion);
       
        if (tabla[indice] == null) {
            tabla[indice] = nuevo;
        } else {
            NodoHash actual = tabla[indice];
            while (actual.siguiente != null) {
                if (actual.triplete.equals(triplete)) {
                    actual.agregarPosicion(posicion);
                    return;
                }
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
    }

    public String generarReporte() {
        String reporte = "=== REPORTE ===\n";
        reporte += "Total tripletes: " + contarTripletes() + "\n\n";
        reporte += generarFrecuencias();
        reporte += "\n" + generarColisiones();
        return reporte;
    }

    private int contarTripletes() {
        int total = 0;
        for (int i = 0; i < size; i++) {
            NodoHash actual = tabla[i];
            while (actual != null) {
                total++;
                actual = actual.siguiente;
            }
        }
        return total;
    }

    private String generarFrecuencias() {
        String resultado = "FRECUENCIAS:\n";
        for (int i = 0; i < size; i++) {
            NodoHash actual = tabla[i];
            while (actual != null) {
                resultado += actual.triplete + ": " + actual.frecuencia + " veces " +
                           actual.obtenerPosicionesFormateadas() + "\n";
                actual = actual.siguiente;
            }
        }
        return resultado;
    }

    private String generarColisiones() {
        String resultado = "COLISIONES:\n";
        int total = 0;
       
        for (int i = 0; i < size; i++) {
            int colisiones = 0;
            NodoHash actual = tabla[i];
           
            while (actual != null && actual.siguiente != null) {
                colisiones++;
                actual = actual.siguiente;
            }
           
            if (colisiones > 0) {
                total += colisiones;
                resultado += "Casilla " + i + ": " + colisiones + " colisiones\n";
            }
        }
       
        return resultado + "\nTotal colisiones: " + total;
    }
    public int contarColisiones() {
        int contador = 0;
        NodoHash actual = cabeza;
        while (actual != null) {
            contador++;
            actual = actual.siguiente;
        }
        return contador > 1 ? contador - 1 : 0;
    }
}