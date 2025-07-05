/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *
 * @author Jabri
 */
public class SecuenciaADN {
    public static String limpiarSecuencia(String secuencia) {
        char[] caracteres = secuencia.toCharArray();
        int longitud = 0;
        
        for (char c : caracteres) {
            if (esBaseNitrogenada(c)) {
                caracteres[longitud++] = Character.toUpperCase(c);
            }
        }
        
        return new String(caracteres, 0, longitud);
    }
    
    private static boolean esBaseNitrogenada(char c) {
        return c == 'A' || c == 'T' || c == 'C' || c == 'G' ||
               c == 'a' || c == 't' || c == 'c' || c == 'g';
    }
    
    public static boolean validarTriplete(String triplete) {
        if (triplete.length() != 3) return false;
        for (int i = 0; i < 3; i++) {
            if (!esBaseNitrogenada(triplete.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}