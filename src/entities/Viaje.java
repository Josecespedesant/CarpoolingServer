package entities;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import graph.Edge;
import graph.Graph;

import java.util.LinkedList;

/**
 * Representa el viaje que realizan conductores con junto estudiantes.
 *
 * @author David Azofeifa H.
 */
public class Viaje {

    private String id;
    private Posicion posConductor;
    private Graph graph;
    private Conductor conductor;
    private int maxEstudiantesRecogidos;
    @Expose(serialize = false)
    private LinkedList<Estudiante> estudiantesInteresados;
    @Expose(serialize = false)
    private LinkedList<Estudiante> estudiantesRecogidos;

    public Viaje(String id, int maxEstudiantesRecogidos, Conductor conductor, Posicion posConductor,
                 LinkedList<Estudiante> estudiantesInteresados, LinkedList<Estudiante> estudiantesRecogidos) {
        this.id = id;
        this.conductor = conductor;
        this.posConductor = posConductor;
        this.maxEstudiantesRecogidos = maxEstudiantesRecogidos;
        this.estudiantesInteresados = estudiantesInteresados;
        this.estudiantesRecogidos = estudiantesRecogidos;

        this.graph = new Graph(new LinkedList<>(), new LinkedList<Edge>());
        this.graph.generateThirtyRandomPlaces();
    }

    /**
     * Incluye a estudiante dentro del auto junto con conductor, llena un asiento disponible.
     *
     * @param estudiante
     */
    public void recogerEstudiante(Estudiante estudiante) {
        if (this.estudiantesRecogidos.size() <= this.maxEstudiantesRecogidos) {
            estudiante.setViajesRealizados(estudiante.getViajesRealizados()+1);
            this.estudiantesRecogidos.add(estudiante);
            this.estudiantesInteresados.remove(estudiante);
        }
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

    public Graph getGraph() {
        return graph;
    }

    public static void main(String[] args) {
        Conductor conductor = new Conductor("david", "algo", "1224", new Posicion(0,0));
        Viaje viaje = new Viaje("1", 4, conductor, conductor.getPosicionHogar(),
                new LinkedList<Estudiante>(), new LinkedList<Estudiante>());
        Gson gson = new Gson();
        String json = gson.toJson(viaje);
        System.out.println(json);
    }
}
