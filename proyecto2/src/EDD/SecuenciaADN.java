/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 * Clase de utilidad para trabajar con secuencias de ADN.
 * 
 * Proporciona métodos estáticos para limpiar y validar cadenas que representan
 * secuencias de bases nitrogenadas (A, T, C, G).
 * No puede ser instanciada.
 * 
 * @author Jabri
 */
public final class SecuenciaADN {

    /**
     * Constructor privado para evitar la instanciación de esta clase de utilidad.
     */
    private SecuenciaADN() {
        // No instanciable.
    }

    /**
     * Limpia una secuencia de ADN eliminando todos los caracteres que no sean
     * bases nitrogenadas (A, T, C, G).
     * 
     * El método recorre la secuencia de entrada y construye una nueva cadena
     * que contiene únicamente los caracteres válidos, convertidos a mayúsculas.
     * Es útil para procesar archivos de texto que puedan contener saltos de línea,
     * números, o metadatos.
     * 
     * @param secuencia La cadena de ADN original, potencialmente con caracteres no válidos.
     * @return Una nueva cadena que contiene solo las bases A, T, C, G en mayúsculas.
     */
    public static String limpiarSecuencia(String secuencia) {
        char[] caracteres = secuencia.toCharArray();
        int longitud = 0;
       
        // recorremos caracter por caracter
        for (char c : caracteres) {
            if (esBaseNitrogenada(c)) {
                caracteres[longitud++] = Character.toUpperCase(c);
            }
        }
       
        return new String(caracteres, 0, longitud);
    }
   
    /**
     * Comprueba si un carácter es una base nitrogenada válida (A, T, C, G).
     * La comprobación no distingue entre mayúsculas y minúsculas.
     * 
     * @param c El carácter a comprobar.
     * @return true si el carácter es una de las cuatro bases, false en caso contrario.
     */
    private static boolean esBaseNitrogenada(char c) {
        char upperC = Character.toUpperCase(c);
        return upperC == 'A' || upperC == 'T' || upperC == 'C' || upperC == 'G';
    }
   
    /**
     * Valida si una cadena dada es un triplete de ADN válido.
     * 
     * Un triplete es válido si y solo si:
     * - Tiene una longitud exacta de 3 caracteres.
     * - Cada uno de sus caracteres es una base nitrogenada (A, T, C, G).
     * 
     * @param triplete La cadena a validar.
     * @return true si es un triplete válido, false en caso contrario.
     */
    public static boolean validarTriplete(String triplete) {
        if (triplete == null || triplete.length() != 3) {
            return false;
        }
        
        // validamos cada caracter del triplete
        for (int i = 0; i < 3; i++) {
            if (!esBaseNitrogenada(triplete.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
