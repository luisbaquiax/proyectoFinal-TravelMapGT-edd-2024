/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.luis.travelgt.objetos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Luis
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Trafico {

    private String origen;
    private String destino;
    private int horaInicio;
    private int horaFin;
    private double probabilidad;

    public Trafico(Trafico trafico) {
        this.origen = trafico.getOrigen();
        this.destino = trafico.getDestino();
        this.horaInicio = trafico.getHoraInicio();
        this.horaFin = trafico.getHoraFin();
        this.probabilidad = trafico.getProbabilidad();
    }

    @Override
    public String toString() {
        return "Trafico{" + "origen=" + origen + ", destino=" + destino + ", horaInicio=" + horaInicio + ", horaFin=" + horaFin + ", probabilidad=" + probabilidad + "}\n";
    }

}
