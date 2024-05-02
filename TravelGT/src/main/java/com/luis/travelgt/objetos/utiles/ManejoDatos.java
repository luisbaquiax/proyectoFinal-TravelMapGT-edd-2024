/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.luis.travelgt.objetos.utiles;

import com.luis.travelgt.objetos.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

/**
 *
 * @author Luis
 */
@Getter
public class ManejoDatos {

    private Archivo archivo;
    private List<Arista> aristasDatos;
    private List<Trafico> traficoDatos;
    private List<Nodo> lugares;
    private List<String> mensajesError;

    public ManejoDatos() {
        this.archivo = new Archivo();
        this.traficoDatos = new ArrayList<>();
        this.lugares = new ArrayList<>();
        this.mensajesError = new ArrayList<>();
        this.aristasDatos = new ArrayList<>();
    }

    public void processData(String content) {
        String[] lineas = content.split("\n");
        for (int i = 0; i < lineas.length; i++) {
            String[] data = lineas[i].split("\\|");
            if (data.length < 7) {
                String mensaje = "No estan completos los datos, deben ser 7, linea: " + (i + 1) + "\n";
                mensajesError.add(mensaje);
            } else {
                String origen = data[0];
                String destino = data[1];
                String tVehiculo = data[2];
                String tPie = data[3];
                String gasolina = data[4];
                String desgaste = data[5];
                String distancia = data[6];

                if (!tVehiculo.matches("[0-9]+")
                        || !tPie.matches("[0-9]+")
                        || !gasolina.matches("[0-9]+")
                        || !distancia.matches("[0-9]+")) {
                    String mensaje = "Los datos deben ser númericos, linea: " + (i + 1) + "\n";
                    mensajesError.add(mensaje);
                } else {
                    aristasDatos.add(new Arista(
                            origen.replaceAll(" ", ""), destino.replaceAll(" ", ""),
                            Integer.parseInt(tVehiculo),
                            Integer.parseInt(tPie),
                            Integer.parseInt(gasolina),
                            Integer.parseInt(desgaste),
                            Integer.parseInt(distancia),
                            0));
                }
            }
        }
        procesarLugares();
    }

    private void procesarLugares() {
        for (int i = 0; i < aristasDatos.size(); i++) {
            if (!existNode(aristasDatos.get(i).getOrigen())) {
                lugares.add(new Nodo(aristasDatos.get(i).getOrigen(), 0, 0));
            }
            if (!existNode(aristasDatos.get(i).getDestino())) {
                lugares.add(new Nodo(aristasDatos.get(i).getDestino(), 0, 0));
            }
        }
    }

    private boolean existNode(String name) {
        for (int i = 0; i < lugares.size(); i++) {
            if (lugares.get(i).getNombre().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void processDataTrafico(String content) {
        String[] lineas = content.split("\n");
        for (int i = 0; i < lineas.length; i++) {
            String[] data = lineas[i].split("\\|");
            if (data.length < 5) {
                String mensaje = "No estan completos los datos, deben ser 7, linea: " + (i + 1) + "\n";
                mensajesError.add(mensaje);
            } else {
                String origen = data[0];
                String destino = data[1];
                String horaInicio = data[2];
                String horaFin = data[3];
                String probabilidad = data[4];

                if (!horaInicio.matches("[0-9]+")
                        || !horaFin.matches("[0-9]+")
                        || !probabilidad.matches("[0-9]+")) {
                    String mensaje = "Los datos deben ser númericos, linea: " + (i + 1) + "\n";
                    mensajesError.add(mensaje);
                } else {
                    traficoDatos.add(new Trafico(
                            origen.replaceAll(" ", ""),
                            destino.replaceAll(" ", ""),
                            Integer.parseInt(horaInicio),
                            Integer.parseInt(horaFin),
                            Integer.parseInt(probabilidad)));
                }
            }
        }
    }

    public String getMensaje() {
        String aux = "Errores en el archivo de entrada:\n";
        for (String string : mensajesError) {
            aux += string;
        }
        return aux;
    }

    public void reinicar() {
        lugares.clear();
        aristasDatos.clear();
        traficoDatos.clear();
        mensajesError.clear();
    }
}
