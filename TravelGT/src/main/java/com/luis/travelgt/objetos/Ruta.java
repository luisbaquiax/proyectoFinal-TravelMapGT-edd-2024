/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.luis.travelgt.objetos;

import java.util.List;
import lombok.Getter;

/**
 *
 * @author Luis
 */
@Getter
public class Ruta {

    String nombreNodo;
    double distanciaTotal;
    double desgasteTotal;
    double desgasteDistanciaTotal;
    double rapidezPromedioApie;

    private int id;

    public static int ID = 0;

    List<Arista> aristas;

    public Ruta(List<Arista> aristas) {
        this.aristas = aristas;
        this.distanciaTotal = calDistanciaTotal();
        this.desgasteTotal = calDesgasteTotal();
        this.desgasteDistanciaTotal = calDesgasteDistanciaTotal();
        this.rapidezPromedioApie = calRapidezPromedioApie();

        ++ID;
        System.out.println("ID: " + ID);
        this.id = ID;
    }

    public Ruta(String nombreNodo, double peso) {
        this.nombreNodo = nombreNodo;
        this.distanciaTotal = peso;
    }

    private double calDistanciaTotal() {
//        double total = 0;
//        for (Arista arista : aristas) {
//            total += arista.getPeso();
//        }
        return aristas.stream()
                .mapToDouble(Arista::getPeso)
                .sum();
    }

    private double calDesgasteTotal() {
        return aristas.stream()
                .mapToDouble(Arista::getDesgaste)
                .sum();
    }

    private double calDesgasteDistanciaTotal() {
        double auxi = 0;
        auxi += getDistanciaTotal();
        auxi += calDesgasteTotal();
        return auxi;
    }

    private double calRapidezPromedioApie() {
        return calDistanciaTotal() / calTiempoTotalApie();
    }

    private double calTiempoTotalApie() {
        return aristas.stream()
                .mapToDouble(Arista::getTiempoApie)
                .sum();
    }

    @Override
    public String toString() {
        return "Ruta{" + "nombreNodo=" + nombreNodo + ", peso=" + distanciaTotal + "}";
    }

}
