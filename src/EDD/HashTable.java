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

    public HashTable(int size) {
        this.size = size;
        this.tabla = new NodoHash[size];
    }

    // Función hash (base 4 para A, T, C, G)
    public int hashTriplete(String triplete) {
        int hash = 0;
        int base = 4;
        for (int i = 0; i < triplete.length(); i++) {
            char c = triplete.charAt(i);
            int value = 0;
            switch (c) {
                case 'A': value = 0; break;
                case 'T': value = 1; break;
                case 'C': value = 2; break;
                case 'G': value = 3; break;
                default: throw new Error("Carácter inválido: " + c);
            }
            hash = hash * base + value;
        }
        return Math.abs(hash % size);
    }

    // Insertar triplete sin usar List
    public void insertar(String triplete, int posicion) {
        int index = hashTriplete(triplete);
        NodoHash nodoActual = tabla[index];

        if (nodoActual == null) {
            tabla[index] = new NodoHash (triplete, posicion);
            return;
        }

        // Buscar el triplete en la lista enlazada
        NodoHash previo = null;
        while (nodoActual != null) {
            if (nodoActual.triplete.equals(triplete)) {
                nodoActual.agregarPosicion(posicion);
                return;
            }
            previo = nodoActual;
            nodoActual = nodoActual.siguiente;
        }

        // Si no existe, agregar al final
        previo.siguiente = new NodoHash (triplete, posicion);
    }

    // Buscar triplete
    public NodoHash buscar(String triplete) {
        int index = hashTriplete(triplete);
        NodoHash nodoActual = tabla[index];

        while (nodoActual != null) {
            if (nodoActual.triplete.equals(triplete)) {
                return nodoActual;
            }
            nodoActual = nodoActual.siguiente;
        }
        return null;
    }

    // Reporte de colisiones (sin List)
    public String[] reporteColisiones() {
        int count = 0;
        // Contar colisiones
        for (int i = 0; i < size; i++) {
            if (tabla[i] != null && tabla[i].siguiente != null) {
                count++;
            }
        }

        String[] reporte = new String[count];
        int idx = 0;
        for (int i = 0; i < size; i++) {
            if (tabla[i] != null && tabla[i].siguiente != null) {
                reporte[idx++] = "Índice " + i + ": " + contarColisiones(tabla[i]) + " colisiones";
            }
        }
        return reporte;
    }

    private int contarColisiones(NodoHash nodo) {
        int count = 0;
        while (nodo != null) {
            count++;
            nodo = nodo.siguiente;
        }
        return count - 1;
    }
}


