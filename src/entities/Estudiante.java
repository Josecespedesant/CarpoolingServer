package entities;

import java.util.LinkedList;

/**
 * Representa estudiante.
 *
 * @author David Azofeifa H.
 */
public class Estudiante {

    private String nombre;
    private String contrasena;
    private String carnet;
    private LinkedList<Conductor> amigos;
    private int viajesRealizados;
    private double promedio;

    public Estudiante(String nombre, String contrasena, String carnet) {
        this.nombre = nombre;
        this.carnet =carnet;
        this.viajesRealizados = 0;
        this.amigos = new LinkedList<Conductor>();
        this.contrasena = contrasena;
    }

    public Estudiante(String nombre, String contrasena, String carnet, int viajesRealizados, LinkedList<Conductor> amigos, double promedio) {
        this.nombre = nombre;
        this.carnet = carnet;
        this.viajesRealizados = viajesRealizados;
        this.contrasena = contrasena;
        this.amigos = amigos;
        this.promedio = promedio;
    }

    public String getNombre() { return nombre; }

    public String getContrasena() { return contrasena; }

    public LinkedList<Conductor> getAmigos() { return amigos; }

    public void setPromedio(double promedio) { this.promedio = promedio; }

    public double getPromedio() { return promedio; }

}

