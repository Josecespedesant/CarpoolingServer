package entities;

import java.util.LinkedList;

/**
 * Representa estudiante.
 *
 * @author David Azofeifa H.
 */
public class Estudiante {

    private String name;
    private String contrasena;
    private LinkedList<Conductor> amigos;
    private double promedio;

    public Estudiante(String name, String contrasena) {
        this.name = name;
        this.contrasena = contrasena;
    }

    public Estudiante(String name, String contrasena, LinkedList<Conductor> amigos, double promedio) {
        this.name = name;
        this.contrasena = contrasena;
        this.amigos = amigos;
        this.promedio = promedio;
    }

    public String getName() { return name; }

    public String getContrasena() { return contrasena; }

    public LinkedList<Conductor> getAmigos() { return amigos; }

    public void setPromedio(double promedio) { this.promedio = promedio; }

    public double getPromedio() { return promedio; }

}

