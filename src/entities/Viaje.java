package entities;

import graph.Vertex;

import java.util.LinkedList;

public class Viaje {

    private String id;
    private Posicion posConductor;
    private LinkedList<Vertex> nodosRuta;
    private Conductor conductor;
    private int maxEstudiantesRecogidos;
    private LinkedList<Estudiante> estudiantesInteresados;

    private LinkedList<Estudiante> estudiantesRecogidos;

    public Viaje(String id, int maxEstudiantesRecogidos, Conductor conductor, Posicion posConductor, LinkedList<Vertex> nodosRuta,
                 LinkedList<Estudiante> estudiantesInteresados, LinkedList<Estudiante> estudiantesRecogidos) {
        this.id = id;
        this.conductor = conductor;
        this.posConductor = posConductor;
        this.maxEstudiantesRecogidos = maxEstudiantesRecogidos;
        this.estudiantesInteresados = estudiantesInteresados;
        this.estudiantesRecogidos = estudiantesRecogidos;
        this.nodosRuta = nodosRuta;
    }

    public void calcularNodosRuta() {
        // TODO llamar a grafo para que calcule puntos de interes cercanos y defina ruta
    }

    public void recogerEstudiante() {
        // TODO hacer que estudiante pase de interesados a recogidos.
    }

    public Conductor getConductor() {
        return conductor;
    }

    public LinkedList<Estudiante> getEstudiantesInteresados() {
        return estudiantesInteresados;
    }

    public int getMaxEstudiantesRecogidos() {
        return maxEstudiantesRecogidos;
    }

    public Posicion getPosConductor() {
        return posConductor;
    }

    public String getId() {
        return id;
    }

    public void setPosConductor(Posicion posConductor) {
        this.posConductor = posConductor;
    }

    public LinkedList<Vertex> getNodosRuta() {
        return nodosRuta;
    }

    public void setNodosRuta(LinkedList<Vertex> nodosRuta) {
        this.nodosRuta = nodosRuta;
    }
}
