package EDD;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

import EDD.HashTable;
import Interfaces.Bienvenida;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;

/**
 *
 * @author Jabri
 */

public class Main {
    private static HashTable tablaHash; // Variable estática declarada correctamente aquí
    
    public static void main(String[] args) {
        tablaHash = new HashTable(101); // Inicialización correcta
        
        JFrame frame = new JFrame("Analizador de ADN");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton cargarBtn = new JButton("Cargar Archivo");
        JTextArea resultadoArea = new JTextArea();

        cargarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    try {
                        String secuencia = leerArchivo(fileChooser.getSelectedFile().getPath());
                        procesarSecuencia(secuencia);
                        resultadoArea.setText("Archivo cargado.\n");

                        // Mostrar colisiones
                        String[] colisiones = tablaHash.reporteColisiones();
                        for (String colision : colisiones) {
                            resultadoArea.append(colision + "\n");
                        }
                    } catch (Exception ex) {
                        resultadoArea.setText("Error al leer el archivo.");
                    }
                }
            }
        });

        frame.add(cargarBtn, "North");
        frame.add(new JScrollPane(resultadoArea), "Center");
        frame.setVisible(true);
    }

    // Leer archivo sin BufferedReader
    private static String leerArchivo(String path) throws Exception {
        FileReader fr = new FileReader(path);
        StringBuilder sb = new StringBuilder();
        int c;
        while ((c = fr.read()) != -1) {
            sb.append((char) c);
        }
        fr.close();
        return sb.toString();
    }

    // Procesar secuencia
    private static void procesarSecuencia(String secuencia) {
        for (int i = 0; i <= secuencia.length() - 3; i++) {
            String triplete = secuencia.substring(i, i + 3);
            tablaHash.insertar(triplete, i);
        }
    }
}