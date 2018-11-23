package servlets.student;

import databases.UserDB;
import org.jdom2.JDOMException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Se encarga un pedido de carpooling de amigos y enviar conductor e info.
 *
 * @author David Azofeifa H.
 */
public class CarpoolingAmigo extends HttpServlet {

    UserDB userDB = new UserDB();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String json = request.getParameter("json");

        try {
            userDB.habilitarCarpoolingAmigos(json);
        } catch (JDOMException e) {
            e.printStackTrace();
        }
    }

}
