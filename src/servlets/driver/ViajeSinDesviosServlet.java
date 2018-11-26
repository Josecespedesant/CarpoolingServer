package servlets.driver;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import databases.UserDB;
import entities.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

/**
 * Se encarga de recibir un encargo de viaje sin desvio.
 * Envia instancia de viaje.
 *
 * @author David Azofeifa H.
 */
public class ViajeSinDesviosServlet extends HttpServlet {

    Gson gson = new Gson();
    JsonParser jsonParser = new JsonParser();
    UserDB userDB = new UserDB();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String json = request.getParameter("json");

        response.setContentType("text/plain");
        PrintWriter out=response.getWriter();

        // Se procesa informacion recibida
        JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();

        String idViaje = jsonObject.getAsJsonPrimitive("id").getAsString();

        String conductorString = gson.toJson(jsonObject.getAsJsonObject("conductor"));
        Conductor conductor = gson.fromJson(conductorString, Conductor.class);

        String posConductorString = gson.toJson(jsonObject.getAsJsonObject("posConductor"));
        Posicion posicion = gson.fromJson(posConductorString, Posicion.class);

        int maxEstudiantesRecogidos = jsonObject.getAsJsonPrimitive("maxEstudiantesRecogidos").getAsInt();

        // Se crea viaje y se guarda la instancia dentro de viajes.
        Viaje viaje = new Viaje(idViaje, maxEstudiantesRecogidos, conductor, posicion,
                new LinkedList<Estudiante>(), new LinkedList<Estudiante>());
        Viajes.getViajes().add(viaje);


        // Se envia json con informacion de viaje
        String viajeJson = gson.toJson(viaje);
        out.print(viajeJson);
    }

    public static void main(String[] args) {

    }

}
