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
    private int valor;
    private NodoArbolBB HijoIzq;
    private NodoArbolBB HijoDer;

    public NodoArbolBB(int valor) {
        this.valor = valor;
        this.HijoIzq = null;
        this.HijoDer = null;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public NodoArbolBB getHijoIzq() {
        return HijoIzq;
    }

    public NodoArbolBB getHijoDer() {
        return HijoDer;
    }
    
    //Inserta el número ingresado por el usuario en el árbol ordenandolo por su valor
    public void insertar(int numero) {
        if (numero < this.valor)
            if (this.HijoIzq == null)
                this.HijoIzq = new NodoArbolBB(numero);
            else
                this.HijoIzq.insertar(numero);
        else if (numero > this.valor)
            if (this.HijoDer == null)
                this.HijoDer = new NodoArbolBB(numero);
            else
                this.HijoDer.insertar(numero);
        else
            return;
    }

}
