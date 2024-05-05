/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.luis.travelgt.objetos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luis
 */
public class TreeB {

    private NodoTree raiz;
    private int tamaño;
    private List<List<String>> caminosPosibles;

    public TreeB(int t) {
        this.raiz = null;
        this.tamaño = t;
    }

    public void insertar(int clave) {
        if (raiz == null) {
            raiz = new NodoTree(true);
            raiz.getClaves().add(clave);
        } else {
            if (raiz.getClaves().size() == 2 * tamaño - 1) {
                NodoTree nuevaRaiz = new NodoTree(false);
                nuevaRaiz.getHijos().add(raiz);
                dividirHijo(nuevaRaiz, 0);
                raiz = nuevaRaiz;
            }
            insertarNoLleno(raiz, clave);
        }
    }

    public void setCaminos(List<List<String>> caminosPosibles) {
        this.caminosPosibles = new ArrayList<>(caminosPosibles);
    }

    /**
     *
     * Función auxiliar para insertar una clave en un nodo que no está lleno
     */
    private void insertarNoLleno(NodoTree nodo, int clave) {
        int i = nodo.getClaves().size() - 1;
        if (nodo.isEsHoja()) {
            while (i >= 0 && clave < nodo.getClaves().get(i)) {
                i--;
            }
            nodo.getClaves().add(i + 1, clave);
        } else {
            while (i >= 0 && clave < nodo.getClaves().get(i)) {
                i--;
            }
            i++;
            if (nodo.getHijos().get(i).getClaves().size() == 2 * tamaño - 1) {
                dividirHijo(nodo, i);
                if (clave > nodo.getClaves().get(i)) {
                    i++;
                }
            }
            insertarNoLleno(nodo.getHijos().get(i), clave);
        }
    }

    /**
     * Función auxiliar para dividir un nodo hijo de un nodo no hoja
     *
     * @param nodoPadre
     * @param indiceHijo
     */
    private void dividirHijo(NodoTree nodoPadre, int indiceHijo) {
        NodoTree hijo = nodoPadre.getHijos().get(indiceHijo);
        NodoTree nuevoHijo = new NodoTree(hijo.isEsHoja());
        for (int j = 0; j < tamaño - 1; j++) {
            nuevoHijo.getClaves().add(hijo.getClaves().remove(tamaño));
        }
        if (!hijo.isEsHoja()) {
            for (int j = 0; j < tamaño; j++) {
                nuevoHijo.getHijos().add(hijo.getHijos().remove(tamaño));
            }
        }
        nodoPadre.getClaves().add(indiceHijo, hijo.getClaves().remove(tamaño - 1));
        nodoPadre.getHijos().add(indiceHijo + 1, nuevoHijo);
    }

    /**
     * Función para generar una representación Graphviz del árbol B
     *
     * @return
     */
    public String grafica() {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph ArbolB {\n");
        sb.append("  node [shape=record];\n");
        sb.append(aGraphviz(raiz));
        sb.append("}\n");
        return sb.toString();
    }

    private String aGraphviz(NodoTree nodo) {
        StringBuilder sb = new StringBuilder();
        sb.append("  nodo").append(nodo.hashCode()).append(" [label=\"");
        for (int i = 0; i < nodo.getClaves().size(); i++) {
            sb.append("<f").append(i).append(">|").append(nodo.getClaves().get(i)).append("|");
        }
        sb.append("<f").append(nodo.getClaves().size()).append(">\"];\n");

        if (!nodo.isEsHoja()) {
            for (int i = 0; i < nodo.getHijos().size(); i++) {
                sb.append(aGraphviz(nodo.getHijos().get(i)));
                sb.append("  nodo").append(nodo.hashCode()).append(":f").append(i).append(" -> nodo").append(nodo.getHijos().get(i).hashCode()).append(";\n");
            }
        }

        return sb.toString();
    }

    public String graficaList() {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph ArbolB {\n");
        sb.append("  node [shape=record];\n");
        sb.append(aGraphvizList(raiz));
        sb.append("}\n");
        return sb.toString();
    }

    private String aGraphvizList(NodoTree nodo) {
        StringBuilder sb = new StringBuilder();
        sb.append("  nodo").append(nodo.hashCode()).append(" [label=\"");
        for (int i = 0; i < nodo.getClaves().size(); i++) {
            sb.append("<f").append(i).append(">|").append(contentList(caminosPosibles.get(nodo.getClaves().get(i)))).append("|");
        }
        sb.append("<f").append(nodo.getClaves().size()).append(">\"];\n");

        if (!nodo.isEsHoja()) {
            for (int i = 0; i < nodo.getHijos().size(); i++) {
                sb.append(aGraphvizList(nodo.getHijos().get(i)));
                sb.append("  nodo").append(nodo.hashCode()).append(":f").append(i).append(" -> nodo").append(nodo.getHijos().get(i).hashCode()).append(";\n");
            }
        }

        return sb.toString();
    }

    /**
     *
     * @param rutas
     * @return
     */
    private String contentList(List<String> rutas) {
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < rutas.size() - 1; i++) {
            content.append("[").append(rutas.get(i)).append("]-");
        }
        content.append("[").append(rutas.get(rutas.size() - 1));
        content.append("]");
        return content.toString();
    }
}
