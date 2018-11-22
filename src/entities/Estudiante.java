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
    private int numCalificaciones;
    private Posicion posicionHogar;
    private double promedio;

    public Estudiante(String nombre, String contrasena, String carnet, Posicion posicionHogar) {
        this.nombre = nombre;
        this.carnet =carnet;
        this.viajesRealizados = 0;
        this.numCalificaciones = 0;
        this.amigos = new LinkedList<Conductor>();
        this.contrasena = contrasena;
        this.posicionHogar = posicionHogar;
    }

    public Estudiante(String nombre, String contrasena, String carnet, int numCalificaciones, int viajesRealizados,
                      LinkedList<Conductor> amigos, double promedio, Posicion posicionHogar) {
        this.nombre = nombre;
        this.carnet = carnet;
        this.numCalificaciones = numCalificaciones;
        this.viajesRealizados = viajesRealizados;
        this.contrasena = contrasena;
        this.amigos = amigos;
        this.promedio = promedio;
        this.posicionHogar = posicionHogar;
    }

    public int getViajesRealizados() {
        return viajesRealizados;
    }

    public void setViajesRealizados(int viajesRealizados) {
        this.viajesRealizados = viajesRealizados;
    }

    public int getNumCalificaciones() {
        return numCalificaciones;
    }

    public void setNumCalificaciones(int numCalificaciones) {
        this.numCalificaciones = numCalificaciones;
    }

    public Posicion getPosicionHogar() {
        return posicionHogar;
    }

    public void setPosicionHogar(Posicion posicionHogar) {
        this.posicionHogar = posicionHogar;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getCarnet() {
        return carnet;
    }

    public LinkedList<Conductor> getAmigos() {
        return amigos;
    }
}

