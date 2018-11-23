package servlets.driver;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import databases.UserDB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Se encarga de recibir la informacion de inicio de sesion de un conductor.
 * Envia treinta posibles destinos cercanos al conductor.
 *
 * @author David Azofeifa H.
 */
public class IngresoServlet extends HttpServlet {

    private UserDB userDB = new UserDB();
    private Gson gson = new Gson();
    private String path = "/home/david/Documents/CarpoolingServer/formatos/";

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String json = request.getParameter("json");

        System.out.println(json);
        response.setContentType("text/plain");
        PrintWriter out=response.getWriter();

        if (this.userDB.ConductorExiste(json)) {
            if (this.userDB.IngresarConductor(json)) {
                System.out.println("exito");

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("exitoso", true);
                String respuesta = gson.toJson(jsonObject);
                System.out.println(respuesta);

                out.print(respuesta);
            }
            else {
                System.out.println("false");

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("exitoso", false);
                String respuesta = gson.toJson(jsonObject);

                out.print(respuesta);
            }
        }
        else {
            System.out.println("noExiste");

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("exitoso", false);
            String respuesta = gson.toJson(jsonObject);

            out.print(respuesta);
        }
    }

}
