/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 * Implementación de un Árbol Binario de Búsqueda (ABB).
 * 
 * Este árbol está diseñado para almacenar patrones de ADN (tripletes) y ordenarlos
 * principalmente por su frecuencia de aparición. Esto permite realizar búsquedas
 * de los patrones más y menos frecuentes de manera eficiente (complejidad O(log n)).
 * 
 * Si dos patrones tienen la misma frecuencia, se ordenan alfabéticamente por el
 * triplete para mantener un orden consistente.
 * 
 * @author simon
 */
public class ArbolBB {
    private NodoArbolBB raiz;

    /**
     * Constructor del Árbol Binario de Búsqueda.
     * Inicializa el árbol con una raíz nula, es decir, un árbol vacío.
     * 
     * @param inicial No se utiliza actualmente, el árbol siempre empieza vacío.
     */
    public ArbolBB(NodoArbolBB inicial) {
        this.raiz = null;  // siempre empezamos con árbol vacío
    }
    
    /**
     * Inserta un nuevo patrón (triplete) con su frecuencia en el árbol.
     * 
     * Este es el método público que inicia el proceso de inserción recursiva.
     * 
     * @param triplete La secuencia de 3 caracteres del patrón de ADN.
     * @param frecuencia El número de veces que este patrón aparece en la secuencia principal.
     */
    public void insertar(String triplete, int frecuencia) {
        raiz = insertarRec(raiz, triplete, frecuencia);
    }

    /**
     * Método auxiliar recursivo para insertar un nodo en el árbol.
     * 
     * Compara la frecuencia del nuevo nodo con el nodo actual para decidir si debe
     * ir a la izquierda (menor frecuencia) o a la derecha (mayor frecuencia).
     * Si las frecuencias son iguales, compara los tripletes alfabéticamente.
     * 
     * @param nodo El nodo actual en el recorrido del árbol.
     * @param triplete El triplete a insertar.
     * @param frecuencia La frecuencia del triplete a insertar.
     * @return El nodo raíz del subárbol modificado.
     */
    private NodoArbolBB insertarRec(NodoArbolBB nodo, String triplete, int frecuencia) {
        // caso base: si el nodo es null, creamos uno nuevo
        if (nodo == null) {
            return new NodoArbolBB(triplete, frecuencia);
        }
       
        if (frecuencia < nodo.frecuencia) {
            nodo.izquierdo = insertarRec(nodo.izquierdo, triplete, frecuencia);
        } else if (frecuencia > nodo.frecuencia) {
            nodo.Derecho = insertarRec(nodo.Derecho, triplete, frecuencia);  // mantenemos inconsistencia
        } else {
            // Si las frecuencias son iguales, desempata alfabéticamente por el triplete
            if (triplete.compareTo(nodo.triplete) < 0) {
                nodo.izquierdo = insertarRec(nodo.izquierdo, triplete, frecuencia);
            } else {
                nodo.Derecho = insertarRec(nodo.Derecho, triplete, frecuencia);
            }
        }
        return nodo;
    }

    /**
     * Devuelve un String con el recorrido in-orden del árbol.
     * 
     * El recorrido in-orden visita los nodos de menor a mayor valor (en este caso,
     * de menor a mayor frecuencia), lo que es útil para listados ordenados.
     * 
     * @return Una cadena de texto con cada nodo en una nueva línea, ordenado por frecuencia.
     */
    public String inOrden() {
        StringBuilder sb = new StringBuilder();
        inOrdenRec(raiz, sb);
        return sb.toString();
    }

    /**
     * Método auxiliar recursivo para realizar el recorrido in-orden.
     * 
     * @param nodo El nodo actual que se está visitando.
     * @param sb El StringBuilder donde se va construyendo el resultado.
     */
    private void inOrdenRec(NodoArbolBB nodo, StringBuilder sb) {
        if (nodo != null) {
            inOrdenRec(nodo.izquierdo, sb);  // primero izquierda
            sb.append("Triplete: ").append(nodo.triplete)
              .append(", Frecuencia: ").append(nodo.frecuencia).append("\n");
            inOrdenRec(nodo.Derecho, sb);    // luego derecha
        }
    }

    /**
     * Encuentra el patrón con la frecuencia más alta en el árbol.
     * 
     * Lo hace buscando el nodo que está más a la derecha, ya que el árbol está
     * ordenado por frecuencia de forma ascendente. Complejidad O(log n).
     * 
     * @return Un String con la información del triplete más frecuente, o una cadena vacía si el árbol está vacío.
     */
    public String getMasFrecuente() {
        if (raiz == null) return "";
        
        NodoArbolBB actual = raiz;
        // vamos hacia la derecha hasta llegar al final
        while (actual.Derecho != null) {
            actual = actual.Derecho;
        }
        return actual.triplete + " (Frecuencia: " + actual.frecuencia + ")";
    }

    /**
     * Encuentra el patrón con la frecuencia más baja en el árbol.
     * 
     * Lo hace buscando el nodo que está más a la izquierda. Complejidad O(log n).
     * 
     * @return Un String con la información del triplete menos frecuente, o una cadena vacía si el árbol está vacío.
     */
    public String getMenosFrecuente() {
        if (raiz == null) return "";
        
        NodoArbolBB actual = raiz;
        // vamos hacia la izquierda hasta llegar al final  
        while (actual.izquierdo != null) {
            actual = actual.izquierdo;
        }
        return actual.triplete + " (Frecuencia: " + actual.frecuencia + ")";
    }
}
