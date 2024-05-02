/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.luis.travelgt.objetos.utiles;

import java.io.IOException;
import java.util.logging.*;

/**
 *
 * @author Luis
 */
public class Graphiz {

    private Archivo archivo;

    public Graphiz(Archivo archivo) {
        this.archivo = archivo;
    }

    public void dibujar(String content, String rutaImagen, String dotFile) {
        try {
            this.archivo.writeFile(dotFile, content);

            ProcessBuilder processBuilder = new ProcessBuilder(
                    "dot",
                    "-Tpng",
                    "-o",
                    rutaImagen,
                    dotFile);
            processBuilder.start();
        } catch (IOException ex) {
            Logger.getLogger(Graphiz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
