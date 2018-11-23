package entities;

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

    public static Viaje getViajeById(String id) {
        Viaje tempViaje = null;
        for (Viaje viaje: viajes) {
            if (viaje.getId().equals(id)) {
                tempViaje = viaje;
            }
        }
        return tempViaje;
    }

}
