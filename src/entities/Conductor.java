package entities;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

import java.util.LinkedList;

/**
 * Representa conductor.
 *
 * @author David Azofeifa H.
 */
public class Conductor {

    private String nombre;
    private String contrasena;
    private String carnet;
    @Expose(serialize = false)
    private LinkedList<String> amigos;
    private int numCalificaciones;
    private Posicion posicionHogar;
    private double promedio;

    public Conductor(String nombre, String contrasena, String carnet, Posicion posicionHogar) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.carnet = carnet;
        this.posicionHogar = posicionHogar;
        this.numCalificaciones = 0;
        this.amigos = new LinkedList<String>();
    }

    public Conductor(String name, String contrasena, int numCalificaciones, String carnet,
                     LinkedList<String> amigos, Posicion posicionHogar, double promedio) {
        this.nombre = name;
        this.numCalificaciones = numCalificaciones;
        this.contrasena = contrasena;
        this.carnet = carnet;
        this.amigos = amigos;
        this.posicionHogar = posicionHogar;
        this.promedio = promedio;
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

    public LinkedList<String> getAmigos() { return amigos; }

}
