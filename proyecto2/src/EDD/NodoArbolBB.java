/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *
 * @author simon
 */
public class NodoArbolBB {
    String triplete;
    int frecuencia;
    NodoArbolBB izquierdo;
    NodoArbolBB Derecho;

    public NodoArbolBB(String triplete, int frecuencia) {
        this.triplete = triplete;
        this.frecuencia = frecuencia;
        this.izquierdo = null;
        this.Derecho = null;
    }
}