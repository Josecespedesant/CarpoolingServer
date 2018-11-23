package servlets;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import databases.UserDB;
import entities.Viaje;
import entities.Viajes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Envia informacion sobre una instancia particular de Viaje.
 *
 * @author David Azofeifa H.
 */
public class EstadoViajeServlet extends HttpServlet {

    Gson gson = new Gson();
    JsonParser jsonParser = new JsonParser();
    UserDB userDB = new UserDB();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String json = request.getParameter("json");

        response.setContentType("text/plain");
        PrintWriter out=response.getWriter();

        // Se procesa informacion
        JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();
        String id = jsonObject.getAsJsonPrimitive("id").getAsString();
        Viaje viaje = Viajes.getViajeById(id);
        String viajeJson = gson.toJson(viaje);

        // Se envia viaje
        out.print(viajeJson);
    }

}
