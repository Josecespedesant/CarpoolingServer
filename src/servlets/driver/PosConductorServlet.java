package servlets.driver;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entities.Posicion;
import entities.Viajes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Se encarga de recibir la posicion actual de un Conductor particular.
 *
 * @author David Azofeifa H.
 */
public class PosConductorServlet extends HttpServlet {

    JsonParser jsonParser = new JsonParser();
    Gson gson = new Gson();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    String json = request.getParameter("json");

    response.setContentType("text/plain");
    PrintWriter out=response.getWriter();

    JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();

    String viajeId = jsonObject.getAsJsonPrimitive("id").getAsString();

    Posicion posConductor = Viajes.getViajeById(viajeId).getPosConductor();
    String posConductorJson = gson.toJson(posConductor);

    out.print(posConductorJson);
    }
}
