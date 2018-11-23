package servlets;

import entities.Estudiante;
import manager.Conversion;
import org.jdom2.Document;
import org.jdom2.JDOMException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * Se encarga de enviar informacion sobre los cinco estiantes con mas viajes registrados.
 *
 * @author David Azofeifa H.
 */
public class Top5Servlet extends HttpServlet {

    private final String pathEstudiantes = "C:\\Users\\Kugelblitz\\Documents\\TEC\\Datos 1\\S2\\CarpoolingServer\\EstudiantesRegistrados\\";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        File dir = new File(this.pathEstudiantes);

        int[][] estudiantes;
        int temp = Integer.MIN_VALUE;

        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            estudiantes = new int[2][directoryListing.length];
            int i = 0;
            for (File child : directoryListing) {
                try {
                    Document doc = Conversion.getXMLFromDisk(child.getName());
                    estudiantes[0][i] = Integer.valueOf(doc.getRootElement().getChildText("viajesRealizados"));
                    estudiantes[1][i] = Integer.valueOf(doc.getRootElement().getChildText("carnet"));
                } catch (JDOMException e) {
                    e.printStackTrace();
                }
            }

            // Se ordena con insertion Sort
            int minIndex;
            for (int k = 0; k < estudiantes[0].length; k++) {
                minIndex = k;
                for (int j = k+1; j < estudiantes[0].length; j++) {
                    if (estudiantes[0][j] > estudiantes[0][minIndex])
                        minIndex = j;
                }
                int temp1 = estudiantes[0][minIndex];
                int temp2 = estudiantes[1][minIndex];
                estudiantes[0][minIndex] = estudiantes[0][k];
                estudiantes[1][minIndex] = estudiantes[1][k];
                estudiantes[0][k] = temp1;
                estudiantes[1][k] = temp2;
            }

            for (int k = 0; k < 5; k++) {
                // TODO terminar.
            }
        }
    }
}
