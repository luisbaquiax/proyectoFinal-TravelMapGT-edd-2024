/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.luis.travelgt.objetos.utiles;

import com.luis.travelgt.objetos.Arista;
import java.awt.Image;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

/**
 *
 * @author Luis
 */
public class Utiles {

    private static final int TAM = 20;

    public void setIconMenuItem(JMenuItem menuItem, String ruta) {
        ImageIcon icono1 = new ImageIcon(getClass().getResource(ruta));
        menuItem.setIcon(new ImageIcon(icono1.getImage().getScaledInstance(TAM,
                TAM, Image.SCALE_SMOOTH)));
    }

    public void setIconLabel(JLabel label, String ruta, int tam) {
        ImageIcon icono1 = new ImageIcon(ruta);
        label.setIcon(new ImageIcon(icono1.getImage().getScaledInstance(tam,
                label.getSize().height, Image.SCALE_SMOOTH)));
    }

    public void setIconLabel(JLabel label, String ruta, int ancho, int alto) {
        ImageIcon icono1 = new ImageIcon(ruta);
        label.setIcon(new ImageIcon(icono1.getImage().getScaledInstance(ancho,
                alto, Image.SCALE_SMOOTH)));
    }

    public ImageIcon getIcon(String ruta, int tam) {
        ImageIcon icono1 = new ImageIcon(ruta);
        return new ImageIcon(icono1.getImage().getScaledInstance(tam, tam, Image.SCALE_SMOOTH));
    }

    public String getRuta(List<Arista> aristas, String origen, String destino) {
        String ruta = "";
        ruta += origen + "-" + aristas.get(0).getPeso() + "->";
        for (int i = 0; i < aristas.size() - 1; i++) {
            ruta += aristas.get(i).getDestino() + "-" + aristas.get(i + 1).getPeso() + "->";
        }
        ruta += destino;
        return ruta;
    }
}
