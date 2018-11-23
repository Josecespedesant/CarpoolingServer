package servlets;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import databases.UserDB;
import entities.Estudiante;
import entities.Viaje;
import entities.Viajes;
import org.jdom2.JDOMException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.PrintWriter;

public class RecogerEstudianteServlet extends HttpServlet {

    UserDB userDB = new UserDB();
    JsonParser jsonParser = new JsonParser();
    Gson gson = new Gson();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String json = request.getParameter("json");

        Estudiante estudiante = null;
        try {
            String temp = userDB.enviarStudent(json);
            estudiante = gson.fromJson(temp, Estudiante.class);
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        Viajes.getViajeById("id").recogerEstudiante(estudiante);
    }
}
