/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 * Clase de utilidad para mapear tripletes de ADN a sus correspondientes aminoácidos.
 * 
 * Esta clase contiene métodos estáticos para realizar la conversión de secuencias
 * de ADN a ARN y luego identificar el aminoácido que codifican, así como sus
 * abreviaturas, basándose en la tabla estándar del código genético.
 * No se puede instanciar, ya que todos sus métodos son estáticos.
 * 
 * @author simon
 */
public class MapaAminoacidos {
    
    /**
     * Constructor privado para evitar que la clase sea instanciada.
     */
    private MapaAminoacidos() {
        // Clase de utilidad, no debe ser instanciada.
    }

    /**
     * Convierte un triplete de ADN a su equivalente en ARN.
     * 
     * La principal diferencia es que la Timina (T) en el ADN se convierte en
     * Uracilo (U) en el ARN.
     * 
     * @param tripleteADN El triplete de ADN (ej. "ATG"). Debe tener 3 caracteres.
     * @return El triplete de ARN correspondiente (ej. "AUG"), o "INVALIDO" si la entrada no es válida.
     */
    public static String convertirADNaARN(String tripleteADN) {
        if (tripleteADN == null || tripleteADN.length() != 3) {
            return "INVALIDO";
        }
        
        // Convertir cada nucleótido de ADN a ARN
        // Se podría usar return tripleteADN.replace('T', 'U'); que es más corto y eficiente
        return tripleteADN.replace('T', 'U');
    }
    
    /**
     * Obtiene el nombre completo del aminoácido correspondiente a un triplete de ADN.
     * 
     * Primero convierte el triplete de ADN a ARN y luego busca en la tabla de codones
     * para encontrar el aminoácido. También identifica los codones de INICIO y PARADA.
     * 
     * @param tripleteADN El triplete de ADN a traducir.
     * @return El nombre del aminoácido o función especial ("STOP", "Metionina (Inicio)", etc.).
     */
    public static String obtenerAminoacido(String tripleteADN) {
        // Primero convertir ADN a ARN
        String tripleteARN = convertirADNaARN(tripleteADN);
        
        // Mapear ARN a aminoácidos según la tabla del código genético
        switch (tripleteARN) {
            // Fenilalanina (Phe, F)
            case "UUU":
            case "UUC":
                return "Fenilalanina";
                
            // Leucina (Leu, L)
            case "UUA":
            case "UUG":
            case "CUU":
            case "CUC":
            case "CUA":
            case "CUG":
                return "Leucina";
                
            // Serina (Ser, S)
            case "UCU":
            case "UCC":
            case "UCA":
            case "UCG":
            case "AGU":
            case "AGC":
                return "Serina";
                
            // Tirosina (Tyr, Y)
            case "UAU":
            case "UAC":
                return "Tirosina";
                
            // Códones de parada (STOP)
            case "UAA":
                return "STOP (Ocre)";
            case "UAG":
                return "STOP (Ámbar)";
            case "UGA":
                return "STOP (Ópalo)";
                
            // Cisteína (Cys, C)
            case "UGU":
            case "UGC":
                return "Cisteína";
                
            // Triptófano (Trp, W)
            case "UGG":
                return "Triptófano";
                
            // Prolina (Pro, P)
            case "CCU":
            case "CCC":
            case "CCA":
            case "CCG":
                return "Prolina";
                
            // Histidina (His, H)
            case "CAU":
            case "CAC":
                return "Histidina";
                
            // Glutamina (Gln, Q)
            case "CAA":
            case "CAG":
                return "Glutamina";
                
            // Arginina (Arg, R)
            case "CGU":
            case "CGC":
            case "CGA":
            case "CGG":
            case "AGA":
            case "AGG":
                return "Arginina";
                
            // Isoleucina (Ile, I)
            case "AUU":
            case "AUC":
            case "AUA":
                return "Isoleucina";
                
            // Metionina (Met, M) - También es codón de inicio
            case "AUG":
                return "Metionina (Inicio)";
                
            // Treonina (Thr, T)
            case "ACU":
            case "ACC":
            case "ACA":
            case "ACG":
                return "Treonina";
                
            // Asparagina (Asn, N)
            case "AAU":
            case "AAC":
                return "Asparagina";
                
            // Lisina (Lys, K)
            case "AAA":
            case "AAG":
                return "Lisina";
                
            // Valina (Val, V)
            case "GUU":
            case "GUC":
            case "GUA":
            case "GUG":
                return "Valina";
                
            // Alanina (Ala, A)
            case "GCU":
            case "GCC":
            case "GCA":
            case "GCG":
                return "Alanina";
                
            // Ácido Aspártico (Asp, D)
            case "GAU":
            case "GAC":
                return "Ácido Aspártico";
                
            // Ácido Glutámico (Glu, E)
            case "GAA":
            case "GAG":
                return "Ácido Glutámico";
                
            // Glicina (Gly, G)
            case "GGU":
            case "GGC":
            case "GGA":
            case "GGG":
                return "Glicina";
                
            default:
                return "Desconocido";
        }
    }
    
    /**
     * Obtiene la abreviatura de 3 letras del aminoácido correspondiente a un triplete de ADN.
     * 
     * @param tripleteADN El triplete de ADN a traducir.
     * @return La abreviatura de 3 letras (ej. "Phe"), "---" para STOP, o "???" si es desconocido.
     */
    public static String obtenerAbreviatura3(String tripleteADN) {
        String aminoacido = obtenerAminoacido(tripleteADN);
        
        // Mapear nombres completos a abreviaturas de 3 letras
        switch (aminoacido) {
            case "Fenilalanina": return "Phe";
            case "Leucina": return "Leu";
            case "Serina": return "Ser";
            case "Tirosina": return "Tyr";
            case "Cisteína": return "Cys";
            case "Triptófano": return "Trp";
            case "Prolina": return "Pro";
            case "Histidina": return "His";
            case "Glutamina": return "Gln";
            case "Arginina": return "Arg";
            case "Isoleucina": return "Ile";
            case "Metionina (Inicio)": return "Met";
            case "Treonina": return "Thr";
            case "Asparagina": return "Asn";
            case "Lisina": return "Lys";
            case "Valina": return "Val";
            case "Alanina": return "Ala";
            case "Ácido Aspártico": return "Asp";
            case "Ácido Glutámico": return "Glu";
            case "Glicina": return "Gly";
            default: 
                if (aminoacido.startsWith("STOP")) {
                    return "---";
                }
                return "???";
        }
    }
    
    /**
     * Obtiene la abreviatura de 1 letra del aminoácido correspondiente a un triplete de ADN.
     * 
     * @param tripleteADN El triplete de ADN a traducir.
     * @return La abreviatura de 1 letra (ej. "F"), "-" para STOP, o "?" si es desconocido.
     */
    public static String obtenerAbreviatura1(String tripleteADN) {
        String aminoacido = obtenerAminoacido(tripleteADN);
        
        // Mapear nombres completos a abreviaturas de 1 letra
        switch (aminoacido) {
            case "Fenilalanina": return "F";
            case "Leucina": return "L";
            case "Serina": return "S";
            case "Tirosina": return "Y";
            case "Cisteína": return "C";
            case "Triptófano": return "W";
            case "Prolina": return "P";
            case "Histidina": return "H";
            case "Glutamina": return "Q";
            case "Arginina": return "R";
            case "Isoleucina": return "I";
            case "Metionina (Inicio)": return "M";
            case "Treonina": return "T";
            case "Asparagina": return "N";
            case "Lisina": return "K";
            case "Valina": return "V";
            case "Alanina": return "A";
            case "Ácido Aspártico": return "D";
            case "Ácido Glutámico": return "E";
            case "Glicina": return "G";
            default: 
                if (aminoacido.startsWith("STOP")) {
                    return "-";
                }
                return "?";
        }
    }
}
