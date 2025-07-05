/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *
 * @author Jabri
 */
public class MapaAminoacidos {
    private static final String[] TRIPLETES = {
        "TTT", "TTC", "TTA", "TTG", "TCT", "TCC", "TCA", "TCG",
        // ... completar con todos los tripletes posibles
        "GGG"
    };
    
    private static final String[] AMINOACIDOS = {
        "Fenilalanina", "Fenilalanina", "Leucina", "Leucina",
        // ... completar con los aminoácidos correspondientes
        "Glicina"
    };
    
    public static String obtenerAminoacido(String triplete) {
        for (int i = 0; i < TRIPLETES.length; i++) {
            if (TRIPLETES[i].equals(triplete)) {
                return AMINOACIDOS[i];
            }
        }
        return "Desconocido";
    }
}