/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *
 * @author Jabri
 */
public class ArbolBB {
    private NodoArbolBB raiz;

    public ArbolBB() {
        this.raiz = null;
    }

    public void insertar(String triplete, int frecuencia) {
        raiz = insertarRec(raiz, triplete, frecuencia);
    }

    private NodoArbolBB insertarRec(NodoArbolBB nodo, String triplete, int frecuencia) {
        if (nodo == null) {
            return new NodoArbolBB(triplete, frecuencia);
        }
        
        if (frecuencia < nodo.frecuencia) {
            nodo.izquierdo = insertarRec(nodo.izquierdo, triplete, frecuencia);
        } else if (frecuencia > nodo.frecuencia) {
            nodo.derecho = insertarRec(nodo.derecho, triplete, frecuencia);
        } else {
            // Si frecuencias son iguales, ordenamos por triplete
            if (triplete.compareTo(nodo.triplete) < 0) {
                nodo.izquierdo = insertarRec(nodo.izquierdo, triplete, frecuencia);
            } else {
                nodo.derecho = insertarRec(nodo.derecho, triplete, frecuencia);
            }
        }
        return nodo;
    }

    public String inOrden() {
        StringBuilder sb = new StringBuilder();
        inOrdenRec(raiz, sb);
        return sb.toString();
    }

    private void inOrdenRec(NodoArbolBB nodo, StringBuilder sb) {
        if (nodo != null) {
            inOrdenRec(nodo.izquierdo, sb);
            sb.append("Triplete: ").append(nodo.triplete)
              .append(", Frecuencia: ").append(nodo.frecuencia).append("\n");
            inOrdenRec(nodo.derecho, sb);
        }
    }

    public String getMasFrecuente() {
        if (raiz == null) return "";
        NodoArbolBB actual = raiz;
        while (actual.derecho != null) {
            actual = actual.derecho;
        }
        return actual.triplete + " (Frecuencia: " + actual.frecuencia + ")";
    }

    public String getMenosFrecuente() {
        if (raiz == null) return "";
        NodoArbolBB actual = raiz;
        while (actual.izquierdo != null) {
            actual = actual.izquierdo;
        }
        return actual.triplete + " (Frecuencia: " + actual.frecuencia + ")";
    }
}