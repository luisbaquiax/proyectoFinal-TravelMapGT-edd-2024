/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.luis.travelgt.objetos;

import java.io.Serializable;
import java.util.ArrayList;
import lombok.Getter;

/**
 *
 * @author Luis
 */
@Getter
public class NodoTree implements Serializable {

    private ArrayList<Integer> claves;
    private ArrayList<NodoTree> hijos;
    private boolean esHoja;

    public NodoTree(boolean esHoja) {
        this.claves = new ArrayList<>();
        this.hijos = new ArrayList<>();
        this.esHoja = esHoja;
    }
}
