/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.luis.travelgt.objetos;

import java.util.Objects;
import lombok.*;

/**
 *
 * @author Luis
 */
@Getter
@Setter
public class Arista {

    private String origen;
    private String destino;
    private int tiempoVehiculo;
    private int tiempoApie;
    private int cantidadGasolina;
    private int desgaste;
    /**
     * representara la distancia entre nodos
     */
    int peso;

    int indiceDestino;

    private boolean caminable;

    public Arista(String origen, String destino, int tiempoVehiculo, int tiempoApie, int cantidadGasolina, int desgaste, int peso, int indiceDestino) {
        this.origen = origen;
        this.destino = destino;
        this.tiempoVehiculo = tiempoVehiculo;
        this.tiempoApie = tiempoApie;
        this.cantidadGasolina = cantidadGasolina;
        this.desgaste = desgaste;
        this.peso = peso;
        this.indiceDestino = indiceDestino;
        this.caminable = false;
    }

    public Arista(Arista arista) {
        this.origen = arista.getOrigen();
        this.destino = arista.getDestino();
        this.tiempoVehiculo = arista.getTiempoVehiculo();
        this.tiempoApie = arista.getTiempoApie();
        this.cantidadGasolina = arista.getCantidadGasolina();
        this.desgaste = arista.getDesgaste();
        this.peso = arista.getPeso();
        this.indiceDestino = arista.getIndiceDestino();
        this.caminable = arista.isCaminable();
    }

    @Override
    public String toString() {
        return "Arista{" + "origen=" + origen + ", destino=" + destino + ", tiempoVehiculo=" + tiempoVehiculo + ", tiempoApie=" + tiempoApie + ", cantidadGasolina=" + cantidadGasolina + ", desgaste=" + desgaste + ", peso=" + peso + ", indiceDestino=" + indiceDestino + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Arista otraArista = (Arista) obj;
        return origen.equals(otraArista.origen) && destino.equals(otraArista.destino) && Double.compare(otraArista.peso, peso) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(origen, destino, peso);
    }

}
