/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *
 * @author Luis
 */
public class Arbol {
    public NodoArbol raiz;
    public int cant;
    public NodoArbol [] vertice;
    

    
    /**
     * 
     * @param length
     * @param dato constructor del &aaccuterbol binario
     */
    public Arbol (int length, String dato){
        NodoArbol nuevo = new NodoArbol(dato);
        this.raiz= nuevo;
        this.cant= 1;    
    }
    /**
     * 
     * @param dato
     * @return valor en Ascii de la letra
     */
    public int casteoASCCI (String dato){
        char[] caracteres = dato.toCharArray();
        char caracter = caracteres[0];
        int valorAscii = caracter; // No es necesario el cast explícito
        
        return valorAscii;
        
    }
    /**
     * 
     * @param dato
     * @return la suma de los valores en ascii de cada letra de la palabra
     */
    public int casteoPalabraASCCI (String dato){
        char[] caracteres = dato.toCharArray();
        int suma =0;
        for (int i = 0; i < caracteres.length;i++){
            suma = suma + this.casteoASCCI(String.valueOf(caracteres[i]));
            
        }
        return suma;
        
    }
    
    /**
     * 
     * @param dato ES UNA LETRA
     */
    public void agregar (String dato){
        NodoArbol aux= new NodoArbol (dato);
        NodoArbol temp= this.raiz;
        
        while(true ){     
            if(this.casteoASCCI(temp.dato) > this.casteoASCCI(dato) ){
                if (temp.hizq== null){
                    temp.hizq= aux;
                    aux.padre= temp;
                   break; 
                }else{
                    temp= temp.hizq;
                }        
            }else if(this.casteoASCCI(temp.dato) == this.casteoASCCI(dato)){
                break;
            }else{
                if (temp.hder== null){
                    temp.hder= aux;
                    aux.padre= temp;
                    break;
                }else{
                    temp=temp.hder;
                }               
            }   
        
    }
}
    /**
     * 
     * @param dato es una palabra
     */
    public void agregarPalabra (String dato){
        NodoArbol aux= new NodoArbol (dato);
        NodoArbol temp= this.raiz;
        
        while(true ){     
            if(this.casteoPalabraASCCI(temp.dato) > this.casteoPalabraASCCI(dato) ){
                if (temp.hizq== null){
                    temp.hizq= aux;
                    aux.padre= temp;
                   break; 
                }else{
                    temp= temp.hizq;
                }        
            }else if(this.casteoPalabraASCCI(temp.dato) == this.casteoPalabraASCCI(dato)){
                break;
            }else{
                if (temp.hder== null){
                    temp.hder= aux;
                    aux.padre= temp;
                    break;
                }else{
                    temp=temp.hder;
                }               
            }   
        
    }
}
    /**
     * Búsqueda en profundidad (DFS) sin lista
     * @param dato elemento a buscar
     * @return true si encuentra el elemento, false en caso contrario
     */
    public boolean profundo(String dato) {
        if (raiz == null || dato == null) {
            return false;
        }
        
        Pila pila = new Pila();
        pila.apilar(raiz);

        while (!pila.estaVacia()) {
            NodoArbol actual = (NodoArbol) pila.desapilar();

            if (actual.dato.equals(dato)) {
                return true;
            }

            // Apilar primero el hijo derecho y luego el izquierdo
            if (actual.hder != null) {
                pila.apilar(actual.hder);
            }
            if (actual.hizq != null) {
                pila.apilar(actual.hizq);
            }
        }
        return false;
    }

    /**
     * Búsqueda en amplitud (BFS) con lista de nodos visitados
     * @param dato elemento a buscar
     * @param lista lista donde se almacenan los nodos visitados
     * @return true si encuentra el elemento, false en caso contrario
     */
    public boolean ancho(String dato, Lista lista) {
        if (raiz == null || dato == null) {
            return false;
        }

        Cola cola = new Cola();
        cola.encolar(raiz);

        while (!cola.estaVacia()) {
            NodoArbol actual = (NodoArbol) cola.desencolar();
            System.out.print(actual.dato + " ");
            lista.agregar(actual);

            if (actual.dato.equals(dato)) {
                return true;
            }
            
            if (actual.hizq != null) {
                cola.encolar(actual.hizq);
            }
            if (actual.hder != null) {
                cola.encolar(actual.hder);
            }
        }
        return false;
    }

    /**
     * Búsqueda en amplitud (BFS) sin lista
     * @param dato elemento a buscar
     * @return true si encuentra el elemento, false en caso contrario
     */
    public boolean ancho(String dato) {
        if (raiz == null || dato == null) {
            return false;
        }

        Cola cola = new Cola();
        cola.encolar(raiz);

        while (!cola.estaVacia()) {
            NodoArbol actual = (NodoArbol) cola.desencolar();

            if (actual.dato.equals(dato)) {
                return true;
            }
            
            if (actual.hizq != null) {
                cola.encolar(actual.hizq);
            }
            if (actual.hder != null) {
                cola.encolar(actual.hder);
            }
        }
        return false;
    }
    
}
