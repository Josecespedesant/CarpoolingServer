package servlets.driver;

import com.google.gson.*;
import entities.*;
import graph.Vertex;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

/**
 * Se encarga de recibir un pedido de viaje con amigos.
 * Envia instancia de viaje.
 *
 * @author David Azofeifa H.
 */
public class ViajeAmigosServlet extends HttpServlet {

    Gson gson = new Gson();
    JsonParser jsonParser = new JsonParser();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String json = request.getParameter("json");

        response.setContentType("text/plain");
        PrintWriter out=response.getWriter();

        // Se procesa informacion recibida
        JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();

        String conductorString = gson.toJson(jsonObject.getAsJsonObject("conductor"));
        Conductor conductor = gson.fromJson(conductorString, Conductor.class);

        String posConductorString = gson.toJson(jsonObject.getAsJsonObject("posConductor"));
        Posicion posicion = gson.fromJson(posConductorString, Posicion.class);

        int maxEstudiantesRecogidos = jsonObject.getAsJsonPrimitive("maxEstudiantesRecogidos").getAsInt();

        JsonArray jsonArray = jsonObject.getAsJsonArray("estudiantesInteresados");
        for (JsonElement jsonElement: jsonArray) {
            System.out.println(jsonElement.toString());
        }

             Viajes.getViajes().add(new Viaje("tempID", maxEstudiantesRecogidos, conductor, posicion,
                     new LinkedList<Estudiante>(), new LinkedList<Estudiante>()));
    }

    public static void main(String[] args) {
        Conductor conductor =  new Conductor("david", "algo", "32", new Posicion(0,0));
        Gson gson2 = new Gson();
        String json = gson2.toJson(conductor);
        System.out.println(json);

    }
}
