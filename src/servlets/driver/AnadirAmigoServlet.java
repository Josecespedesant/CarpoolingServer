package servlets.driver;

import databases.UserDB;
import org.jdom2.JDOMException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Se encarga de recibir info de amigo que conductor quiere anadir y envia si se anadio exitosamente
 *
 * @author David Azofeifa H.
 */
public class AnadirAmigoServlet extends HttpServlet {

    private UserDB userDB = new UserDB();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String json = request.getParameter("json");

        response.setContentType("text/plain");
        PrintWriter out=response.getWriter();

        try {
            if(userDB.anadirAmigo(json)) {
                out.print("{\"exitoso\": true}");
            }
            else {
                out.print("{\"exitoso\": false}");
            }
        } catch (JDOMException e) {
            e.printStackTrace();
        }


    }

}
