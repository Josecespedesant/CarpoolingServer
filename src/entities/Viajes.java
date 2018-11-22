package entities;

import graph.Vertex;

import java.util.LinkedList;

/**
 * Sostiene viajes en el sistema de manera estática, de tal manera siempre son accessibles por cualquiera.
 *
 * @author David Azofeifa H.
 */
public class Viajes {

    private static LinkedList<Viaje> viajes = new LinkedList<Viaje>();

    public static LinkedList<Viaje> getViajes() {
        return viajes;
    }

}
