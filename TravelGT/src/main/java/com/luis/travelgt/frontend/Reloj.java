/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.luis.travelgt.frontend;

import java.util.Calendar;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Luis
 */
@Getter
@Setter
public class Reloj implements Runnable {

    private int hora;
    private int minutos;
    private int segundos;

    private String tiempo;

    TravelGt ventana;

    public Reloj(TravelGt ventana, int hora, int minutos, int segundos) {
        this.ventana = ventana;
        this.hora = hora;
        this.minutos = minutos;
        this.segundos = segundos;
    }

    @Override
    public void run() {
        while (true) {
            if (ventana.isSeguir()) {
                segundos++;
                if (segundos > 59) {
                    ++minutos;
                    segundos = 0;
                    if (minutos > 59) {
                        hora++;
                        minutos = 0;
                        if (hora > 23) {
                            hora = 0;
                        }
                    }
                }
                ventana.hora = hora;
                ventana.minutos = minutos;
                ventana.segundos = segundos;
                ventana.getLabelHora().setText("Hora " + hora + ":" + minutos + ":" + segundos);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }

}
