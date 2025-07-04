/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *
 * @author simon
 */
public class ArbolBB {
    NodoArbolBB inicial;

    public ArbolBB(NodoArbolBB inicial) {
        this.inicial = null;
    }
    
    //Inserta un valor seleccionado por el usuario en el árbol
    public void insertar(int valor) {
        if (this.inicial == null)
            this.inicial = new NodoArbolBB(valor);
        else
            this.inicial.insertar(valor);
    }
    
    public NodoArbolBB llamarPreoder() {
        this.preorder(this.inicial);
        return this.inicial;
    }
    
    public void preorder(NodoArbolBB nodo) {
        if (nodo == null)
            return;
        else
            //Recordatorio: Aqui falta una funcion que marque las visitas, los prints no estan permitidos
            preorder(nodo.getHijoIzq());
            preorder(nodo.getHijoDer());
    }
    
    public NodoArbolBB llamarInoder() {
        this.inorder(this.inicial);
        return this.inicial;
    }
    
    public void inorder(NodoArbolBB nodo) {
        if (nodo == null)
            return;
        else
            inorder(nodo.getHijoIzq());
            //Recordatorio: Aqui falta una funcion que marque las visitas, los prints no estan permitidos
            inorder(nodo.getHijoDer());
    }
    
    public NodoArbolBB llamarPostoder() {
        this.postorder(this.inicial);
        return this.inicial;
    }
    
    public void postorder(NodoArbolBB nodo) {
        if (nodo == null)
            return;
        else
            postorder(nodo.getHijoIzq());
            postorder(nodo.getHijoDer());
            //Recordatorio: Aqui falta una funcion que marque las visitas, los prints no estan permitidos
    }
}
