/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.luis.travelgt.objetos;

import java.io.Serializable;
import lombok.*;

/**
 *
 * @author Luis
 */
@Data
@Getter
@Setter
public class Nodo implements Serializable, Comparable<Nodo> {

    String nombre;
    /**
     * Distancia representará la distancia que se acumula hacia otro nodo
     * ponderado
     */
    double distancia;
    int indice;

    public Nodo(String nombre, double distancia, int indice) {
        this.nombre = nombre;
        this.distancia = distancia;
        this.indice = indice;
    }

    @Override
    public int compareTo(Nodo otro) {
        return Double.compare(this.indice, otro.indice);
    }
}
