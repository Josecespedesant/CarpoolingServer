package servlets.student;

import databases.UserDB;
import org.jdom2.JDOMException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Se encarga de recibir pedido de carpooling normal y envia pos de conductor e info.
 *
 * @author David Azofeifa H.
 */
public class CarpoolingNormalServlet extends HttpServlet {

    UserDB userDB = new UserDB();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String json = request.getParameter("json");

        try {
            userDB.habilitarCarpoolingNormal(json);
        } catch (JDOMException e) {
            e.printStackTrace();
        }
    }

}
