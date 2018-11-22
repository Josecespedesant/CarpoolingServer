package servlets.driver;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import databases.UserDB;
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
 * Se encarga de recibir un encargo de viaje sin desvio.
 * Envia la mejor ruta calculada.
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

        String conductorString = gson.toJson(jsonObject.getAsJsonObject("conductor"));
        Conductor conductor = gson.fromJson(conductorString, Conductor.class);

        String posConductorString = gson.toJson(jsonObject.getAsJsonObject("posConductor"));
        Posicion posicion = gson.fromJson(posConductorString, Posicion.class);

        int maxEstudiantesRecogidos = jsonObject.getAsJsonPrimitive("maxEstudiantesRecogidos").getAsInt();

        String estudiantesInteresados = jsonObject.getAsJsonArray("estudiantesInteresados").getAsString();

        // TODO convertir estudiantes interesados en array de estudiantes.
        System.out.println(estudiantesInteresados);

        Viajes.getViajes().add(new Viaje("tempID", maxEstudiantesRecogidos, conductor, posicion,
                new LinkedList<Vertex>(), new LinkedList<Estudiante>(), new LinkedList<Estudiante>()));




    }

}
