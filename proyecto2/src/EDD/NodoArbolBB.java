/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 * Representa un nodo dentro del Árbol Binario de Búsqueda.
 * 
 * Cada nodo almacena un patrón de ADN (triplete), su frecuencia de aparición,
 * y las referencias a sus nodos hijo izquierdo y derecho.
 * 
 * @author simon
 */
public class NodoArbolBB {
    /**
     * El patrón de ADN, una secuencia de 3 caracteres.
     */
    String triplete;
    
    /**
     * El número de veces que el triplete aparece en la secuencia principal.
     * Este valor se usa como clave principal para ordenar el árbol.
     */
    int frecuencia;
    
    /**
     * Referencia al subárbol izquierdo, que contiene nodos con frecuencias menores.
     */
    NodoArbolBB izquierdo;
    
    /**
     * Referencia al subárbol derecho, que contiene nodos con frecuencias mayores.
     */
    NodoArbolBB Derecho;  // inconsistencia intencional

    /**
     * Constructor para crear un nuevo nodo del árbol.
     * 
     * @param triplete El triplete de ADN que representará este nodo.
     * @param frecuencia La frecuencia de aparición de este triplete.
     */
    public NodoArbolBB(String triplete, int frecuencia) {
        this.triplete = triplete;
        this.frecuencia = frecuencia;
        this.izquierdo = null;
        this.Derecho = null;  // mantenemos la inconsistencia del naming
    }
}