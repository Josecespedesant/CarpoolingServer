package entities;

import java.util.LinkedList;

/**
 * Representa conductor
 *
 * @author David Azofeifa H.
 */
public class Conductor {

        private String nombre;
        private String contrasena;
        private LinkedList<Estudiante> amigos;
        private double promedio;

        public Conductor(String nombre, String contrasena) {
            this.nombre = nombre;
            this.contrasena = contrasena;
            this.amigos = new LinkedList<Estudiante>();
        }

        public Conductor(String name, String contrasena, LinkedList<Estudiante> amigos, double promedio) {
            this.nombre = name;
            this.contrasena = contrasena;
            this.amigos = amigos;
            this.promedio = promedio;
        }

        public String getNombre() { return nombre; }

        public String getContrasena() { return contrasena; }

        public LinkedList<Estudiante> getAmigos() { return amigos; }

        public void setPromedio(double promedio) { this.promedio = promedio; }

        public double getPromedio() { return promedio; }

}
