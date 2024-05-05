/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.luis.travelgt.objetos;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import lombok.Getter;

/**
 *
 * @author Luis
 */
@Getter
public class GrafoDirigido {

    private List<Nodo> nodos;
    private List<List<Arista>> aristas;
    private List<Integer> rutaMasCortaIndices;

    public GrafoDirigido() {
        this.nodos = new ArrayList<>();
        this.aristas = new ArrayList<>();
        this.rutaMasCortaIndices = new ArrayList<>();
    }

    private void verAristas() {
    }

    public void agregarVertice(String vertice) {

        nodos.add(new Nodo(vertice, 0, 0));
        aristas.add(new ArrayList<>());
    }

    public void agregarArista(String origen, String destino, Arista arista) {
        int indiceOrigen = encontrarIndiceNodo(origen);
        int indiceDestino = encontrarIndiceNodo(destino);

        arista.indiceDestino = indiceDestino;
        aristas.get(indiceOrigen).add(new Arista(arista));

        arista.setCaminable(true);
        arista.indiceDestino = indiceOrigen; // Intercambiamos los nodos origen y destino
        aristas.get(indiceDestino).add(new Arista(arista));

    }

    public String mostrarGrafo() {
        StringBuilder resultado = new StringBuilder();
        resultado.append("digraph G {\n");
        for (int i = 0; i < nodos.size(); i++) {
            String origen = nodos.get(i).nombre;
            List<Arista> destinos = aristas.get(i);
            for (Arista arista : destinos) {
                String destino = nodos.get(arista.indiceDestino).nombre;
                if (!arista.isCaminable()) {
                    resultado.append(origen).append(" -> ").
                            append(destino).append(" [label=\"[").append(arista.peso).append("]\", dir=none];\n");
                }
            }
        }
        resultado.append("}\n");
        return resultado.toString();
    }

    public String mostrarCaminos(String origen, String destino, String color) {
        encontrarCaminos(origen, destino);
        StringBuilder resultado = new StringBuilder();
        resultado.append("digraph G {\n");
        /*for (int i = 0; i < nodos.size(); i++) {
            String nodo = nodos.get(i).nombre;
            if (nodo.equals(origen) || nodo.equals(destino)) {
                resultado.append(nodo).append(" [style=filled, fillcolor=").append(color).append("];\n");
            }
            List<Arista> destinos = aristas.get(i);
            for (Arista arista : destinos) {
                String nodoDestino = nodos.get(arista.indiceDestino).nombre;
                String etiqueta = " [label=\"" + arista.peso + "\", color=\"black\"]";
                resultado.append(nodo).append(" -> ").append(nodoDestino).append(etiqueta).append(";\n");
            }
        }*/
        for (int i = 0; i < nodos.size(); i++) {
            String nodo = nodos.get(i).nombre;
            if (nodo.equals(origen) || nodo.equals(destino)) {
                resultado.append(nodo).append(" [style=filled, shape=doublecircle, fillcolor=").append("yellow").append("];\n");
            }
            if (nodo.equals(origen) && nodo.equals(destino)) {
                resultado.append(nodo).append(" [style=filled, shape=doublecircle, fillcolor=").append("yellow").append("];\n");
            }
            List<Arista> destinos = aristas.get(i);
            for (Arista arista : destinos) {
                String nodoDestino = nodos.get(arista.indiceDestino).nombre;
                String etiqueta;
                if (arista.indiceDestino == rutaMasCortaIndices.get(0)) {
                    if (nodo.equals(origen) && nodoDestino.equals(destino)) {
                        etiqueta = " [label=\"" + arista.peso + "\", color=\"blue\"]";
                    } else {
                        //etiqueta = " [label=\"" + arista.peso + "\", color=\"" + color + "\"]";
                        etiqueta = " [label=\"" + arista.peso + "\", color=\"black\"]";
                    }
                } else {
                    if (rutaMasCortaIndices.contains(i) && rutaMasCortaIndices.contains(arista.indiceDestino)) {
                        etiqueta = " [label=\"" + arista.peso + "\", color=\"" + color + "\", penwidth=3, style=bold,]";
                    } else {
                        etiqueta = " [label=\"" + arista.peso + "\", color=\"black\"]";
                    }
                }
                resultado.append(nodo).append(" -> ").append(nodoDestino).append(etiqueta).append(";\n");
            }
        }
        resultado.append("}\n");
        return resultado.toString();
    }

    public List<List<String>> obtenerPosiblesCaminos(String origen, String destino) {
        List<List<String>> posiblesCaminos = new ArrayList<>();
        encontrarCaminos(origen, destino, new ArrayList<>(), posiblesCaminos);
        return posiblesCaminos;
    }

    private void encontrarCaminos(String origen, String destino, List<String> caminoActual, List<List<String>> posiblesCaminos) {
        int indiceOrigen = encontrarIndiceNodo(origen);
        int indiceDestino = encontrarIndiceNodo(destino);

        caminoActual.add(origen);

        if (indiceOrigen == indiceDestino) {
            posiblesCaminos.add(new ArrayList<>(caminoActual));
        } else {
            for (Arista arista : aristas.get(indiceOrigen)) {
                String siguienteNodo = nodos.get(arista.indiceDestino).nombre;
                if (!caminoActual.contains(siguienteNodo)) {
                    encontrarCaminos(siguienteNodo, destino, caminoActual, posiblesCaminos);
                }
            }
        }

        caminoActual.remove(caminoActual.size() - 1);
    }

    public String mostrarRutaMasCorta(String origen, String destino, String color) {
        encontrarRutaMasCorta(origen, destino);
        StringBuilder resultado = new StringBuilder();
        resultado.append("digraph G {\n");
        for (int i = 0; i < nodos.size(); i++) {
            String nodo = nodos.get(i).nombre;
            //mostrar ubicacion
            if (nodo.equals(origen) || nodo.equals(destino)) {
                resultado.append(nodo).append(" [style=filled, fillcolor=").append("yellow").append("];\n");
            }
            List<Arista> destinos = aristas.get(i);
            for (Arista arista : destinos) {
                String nodoDestino = nodos.get(arista.indiceDestino).nombre;
                String etiqueta = " [label=\"[" + arista.peso + "]\", color=\"" + color + "\"";
                if (rutaMasCortaIndices.contains(i) && rutaMasCortaIndices.contains(arista.indiceDestino) && arista.indiceDestino != encontrarIndiceNodo(origen)) {
                    etiqueta += " penwidth=3, style=bold, color=\"red\"]";
                } else {
                    etiqueta = " [label=\"[" + arista.peso + "]\", color=\"" + color + "\"]";
                }
                resultado.append(nodo).append(" -> ").append(nodoDestino).append(etiqueta).append(";\n");
            }
        }
        resultado.append("}\n");
        return resultado.toString();
    }

    private void encontrarRutaMasCorta(String origen, String destino) {
        int indiceOrigen = encontrarIndiceNodo(origen);
        int indiceDestino = encontrarIndiceNodo(destino);

        // Inicializar distancias
        double[] distancias = new double[nodos.size()];
        for (int i = 0; i < distancias.length; i++) {
            distancias[i] = Double.MAX_VALUE;
        }
        distancias[indiceOrigen] = 0;

        // Inicializar rutas anteriores
        int[] rutasAnteriores = new int[nodos.size()];
        for (int i = 0; i < rutasAnteriores.length; i++) {
            rutasAnteriores[i] = -1;
        }

        // Usar el algoritmo de Dijkstra para encontrar la ruta más corta
        PriorityQueue<Nodo> colaPrioridad = new PriorityQueue<>();
        colaPrioridad.offer(new Nodo("", 0, indiceOrigen));

        while (!colaPrioridad.isEmpty()) {
            Nodo nodoActual = colaPrioridad.poll();
            int indiceActual = nodoActual.indice;
            if (indiceActual == indiceDestino) {
                break;
            }
            for (Arista arista : aristas.get(indiceActual)) {
                double nuevaDistancia = distancias[indiceActual] + arista.peso;
                if (nuevaDistancia < distancias[arista.indiceDestino]) {
                    distancias[arista.indiceDestino] = nuevaDistancia;
                    rutasAnteriores[arista.indiceDestino] = indiceActual;
                    colaPrioridad.offer(new Nodo("", nuevaDistancia, arista.indiceDestino));
                }
            }
        }

        // Reconstruir la ruta más corta
        List<Integer> ruta = new ArrayList<>();
        int indiceActual = indiceDestino;
        while (indiceActual != -1) {
            ruta.add(0, indiceActual);
            indiceActual = rutasAnteriores[indiceActual];
        }

        rutaMasCortaIndices = ruta;
    }

    private void encontrarCaminos(String origen, String destino) {
        int indiceOrigen = encontrarIndiceNodo(origen);
        int indiceDestino = encontrarIndiceNodo(destino);

        // Inicializar rutas
        List<Integer> ruta = new ArrayList<>();
        boolean[] visitados = new boolean[nodos.size()];

        // Buscar caminos 
        buscarCaminosRecursivo(indiceOrigen, indiceDestino, ruta, visitados);
    }

    private void buscarCaminosRecursivo(int origen, int destino, List<Integer> ruta, boolean[] visitados) {
        visitados[origen] = true;
        ruta.add(origen);

        if (origen == destino) {
            rutaMasCortaIndices = new ArrayList<>(ruta);
        } else {
            for (Arista arista : aristas.get(origen)) {
                if (!visitados[arista.indiceDestino]) {
                    buscarCaminosRecursivo(arista.indiceDestino, destino, ruta, visitados);
                }
            }
        }

        visitados[origen] = false;
        ruta.remove(ruta.size() - 1);
    }

    private int encontrarIndiceNodo(String vertice) {
        for (int i = 0; i < nodos.size(); i++) {
            if (nodos.get(i).nombre.equals(vertice)) {
                return i;
            }
        }
        return -1;
    }

    public Ruta getRutaCorta(String origen, String destino) {
        encontrarRutaMasCorta(origen, destino);
        List<Arista> rutaMasCorta = new ArrayList<>();
        for (int indice : rutaMasCortaIndices) {
            rutaMasCorta.add(new Arista(aristas.get(indice).get(0)));
        }
        return new Ruta(rutaMasCorta);
    }

    /**
     *
     * @param origen
     * @param destino
     * @return
     */
    public List<Ruta> obtenerRutaMasCorta(String origen, String destino) {
        encontrarRutaMasCorta(origen, destino);
        List<Ruta> ruta = new ArrayList<>();
        for (int indice : rutaMasCortaIndices) {
            ruta.add(new Ruta(nodos.get(indice).nombre, calcularPesoArista(indice)));
        }
        return ruta;
    }

    private double calcularPesoArista(int indiceNodo) {
        double peso = 0.0;
        List<Arista> aristasNodo = aristas.get(indiceNodo);
        for (Arista arista : aristasNodo) {
            if (rutaMasCortaIndices.contains(arista.indiceDestino)) {
                peso = arista.peso;
                break;
            }
        }
        return peso;
    }

    public List<Ruta> obtenerTodasLasRutas(String origen, String destino) {
        List<Ruta> todasLasRutas = new ArrayList<>();
        int indiceOrigen = encontrarIndiceNodo(origen);
        int indiceDestino = encontrarIndiceNodo(destino);
        if (indiceOrigen != -1 && indiceDestino != -1) {
            List<Arista> rutaActual = new ArrayList<>();
            boolean[] visitados = new boolean[nodos.size()];
            encontrarTodasLasRutasRecursivo(indiceOrigen, indiceDestino, rutaActual, todasLasRutas, visitados);
        }
        return todasLasRutas;
    }

    private void encontrarTodasLasRutasRecursivo(int origen, int destino, List<Arista> rutaActual, List<Ruta> todasLasRutas, boolean[] visitados) {
        visitados[origen] = true;

        if (origen == destino) {
            todasLasRutas.add(new Ruta(new ArrayList<>(rutaActual)));
        } else {
            for (Arista arista : aristas.get(origen)) {
                if (!visitados[arista.indiceDestino]) {
                    rutaActual.add(new Arista(arista));
                    encontrarTodasLasRutasRecursivo(arista.indiceDestino, destino, rutaActual, todasLasRutas, visitados);
                    rutaActual.remove(rutaActual.size() - 1);
                }
            }
        }

        visitados[origen] = false;
    }

}
