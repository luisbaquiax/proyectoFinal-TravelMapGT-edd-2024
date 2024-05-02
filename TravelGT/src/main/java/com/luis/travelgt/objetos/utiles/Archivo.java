/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.luis.travelgt.objetos.utiles;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import static javax.swing.JFileChooser.APPROVE_OPTION;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Luis
 */
public class Archivo {

    private JFileChooser choser;
    public static final String RUTA_ARCHIVO_DOT = "img/mapa.dot";
    public static final String DOT_CAMINOS = "img/caminos.dot";
    public static final String DOT_CORTA = "img/caminoCorto.dot";
    public static final String RUTA_IMAGEN_MAPA = "img/Graphiz.png";
    public static final String IMG_CAMINO_CORTO = "img/caminoCorto.png";
    public static final String IMG_CAMINOS = "img/caminos.png";
    public static final String RUTA_MANUAL = "../documentacion/Manual de usuario.pdf";
    public static final String DOT_TREE = "img/tree.dot";   
    public static final String IMG_TREE = "img/tree.png";

    public String pathChoserOpen() {
        choser = new JFileChooser();
        int seleccionado = choser.showOpenDialog(null);
        if (seleccionado == APPROVE_OPTION) {
            return (choser.getSelectedFile().getAbsolutePath());
        }
        return "";
    }

    public String readFile(String ruta) {
        String content = "";
        try {
            FileInputStream fileInputStream = new FileInputStream(ruta);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "ISO-8859-1");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder contentBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.isBlank()) {
                    contentBuilder.append(line.strip()).append("\n");
                }
            }
            content = contentBuilder.toString();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return content;
    }

    /**
     * Write a simple text file
     *
     * @param path
     * @param content
     */
    public void writeFile(String path, String content) {
        try {
            File file = new File(path);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.write(content);
            bw.close();
        } catch (IOException e) {
            System.out.println("fallo al escribir el archivo");
            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     *
     * @param path
     */
    public void openFile(String path) {
        try {
            File file = new File(path);
            if (Desktop.isDesktopSupported() && file.exists()) {
                Desktop.getDesktop().open(file);
            } else {
                System.out.println("Desktop API no es compatible o el archivo no existe.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
