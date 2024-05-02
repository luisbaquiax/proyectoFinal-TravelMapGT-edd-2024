/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.luis.travelgt;

import com.luis.travelgt.frontend.TravelGt;
import com.luis.travelgt.objetos.GrafoDirigido;
import com.luis.travelgt.objetos.utiles.Archivo;
import com.luis.travelgt.objetos.utiles.ManejoDatos;

public class Main {

    public static void main(String[] args) {
        System.setProperty("file.encoding", "ISO-8859-1");
        TravelGt travelGt = new TravelGt();
        travelGt.setVisible(true);
    }
}
