package entities;

import java.util.LinkedList;

/**
 * Representa conductor
 *
 * @author David Azofeifa H.
 */
public class Conductor {

        private String name;
        private String contrasena;
        private LinkedList<Estudiante> amigos;
        private double promedio;

        public Conductor(String name, String contrasena) {
            this.name = name;
            this.contrasena = contrasena;
        }

        public Conductor(String name, String contrasena, LinkedList<Estudiante> amigos, double promedio) {
            this.name = name;
            this.contrasena = contrasena;
            this.amigos = amigos;
            this.promedio = promedio;
        }

        public String getName() { return name; }

        public String getContrasena() { return contrasena; }

        public LinkedList<Estudiante> getAmigos() { return amigos; }

        public void setPromedio(double promedio) { this.promedio = promedio; }

        public double getPromedio() { return promedio; }

}
