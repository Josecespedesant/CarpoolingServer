package databases;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entities.Conductor;
import entities.Estudiante;
import manager.Conversion;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.*;

public class UserDB {

    private JsonParser jsonParser = new JsonParser();

    private final String pathConductores = "C:\\Users\\Kugelblitz\\Documents\\TEC\\Datos 1\\S2\\CarpoolingServer\\ConductoresRegistrados\\";
    private final String pathEstudiantes = "C:\\Users\\Kugelblitz\\Documents\\TEC\\Datos 1\\S2\\CarpoolingServer\\EstudiantesRegistrados\\";

    /**
     * Registra conductor guardando su información en disco por medio de xml.
     *
     * @param json
     * @throws IOException
     */
    public void RegistrarConductor(String json) throws IOException {
        Element conductor = new Element("conductor");
        Document doc = new Document();
        doc.setRootElement(conductor);

        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();

        String nombre = jsonObject.getAsJsonPrimitive("nombre").getAsString();
        Element nombreElment = new Element("nombre");
        nombreElment.addContent(nombre);

        String contrasena = jsonObject.getAsJsonPrimitive("contrasena").getAsString();
        Element contrasenaElement = new Element("contrasena");
        contrasenaElement.addContent(contrasena);

        String carnet = jsonObject.getAsJsonPrimitive("carnet").getAsString();
        Element carnetElement = new Element("carnet");
        carnetElement.addContent(carnet);

        Element amigosElement = new Element("amigos");
        Element promedioElement = new Element("promedio");
        promedioElement.addContent("0");

        conductor.addContent(nombreElment);
        conductor.addContent(contrasenaElement);
        conductor.addContent(carnetElement);
        conductor.addContent(amigosElement);
        conductor.addContent(promedioElement);

        // TODO hacer que no depende de un pathConductores especifico.
        Conversion.saveXMLToDisk(doc, this.pathConductores +carnet+".xml");
    }


    /**
     * Registra estudiante guardando su información en disco por medio de xml.
     *
     * @param json
     * @throws IOException
     */
    public void RegistrarEstudiante(String json) throws IOException {
        Element estudiante = new Element("estudiante");
        Document doc = new Document();
        doc.setRootElement(estudiante);

        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();

        String nombre = jsonObject.getAsJsonPrimitive("nombre").getAsString();
        Element nombreElment = new Element("nombre");
        nombreElment.addContent(nombre);

        String contrasena = jsonObject.getAsJsonPrimitive("contrasena").getAsString();
        Element contrasenaElement = new Element("contrasena");
        contrasenaElement.addContent(contrasena);

        String carnet = jsonObject.getAsJsonPrimitive("carnet").getAsString();
        Element carnetElement = new Element("carnet");
        carnetElement.addContent(carnet);

        Element amigosElement = new Element("amigos");
        Element promedioElement = new Element("promedio");
        promedioElement.addContent("0");
        Element viajesRealizadosElement = new Element("viajesRealizados");
        viajesRealizadosElement.addContent("0");

        estudiante.addContent(nombreElment);
        estudiante.addContent(contrasenaElement);
        estudiante.addContent(carnetElement);
        estudiante.addContent(amigosElement);
        estudiante.addContent(promedioElement);

        // TODO hacer que no depende de un pathConductores especifico.
        Conversion.saveXMLToDisk(doc, this.pathEstudiantes +carnet+".xml");
    }

    /**
     * Determina si un conductor existe.
     *
     * @return boolean, conductor existe.
     */
    public boolean ConductorExiste(String json) {

        JsonObject jsonObject = this.jsonParser.parse(json).getAsJsonObject();

        File f = new File(this.pathConductores);
        File[] archivosCorrespondientes = f.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.startsWith(jsonObject.getAsJsonPrimitive("carnet").getAsString())
                        && name.endsWith("xml");
            }
        });
        return (archivosCorrespondientes.length != 0);
    }

    /**
     * Determina si un estudiante existe
     *
     * @param json
     * @return
     */
    public boolean EstudianteExiste(String json) {
        JsonObject jsonObject = this.jsonParser.parse(json).getAsJsonObject();

        File f = new File(this.pathEstudiantes);
        File[] archivosCorrespondientes = f.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.startsWith(jsonObject.getAsJsonPrimitive("carnet").getAsString())
                        && name.endsWith("xml");
            }
        });
        return (archivosCorrespondientes.length != 0);
    }


    /**
     * Comprueba la contrasena de un conductor determinado.
     *
     * @param json
     * @return
     */
    public boolean IngresarConductor(String json) {
        boolean ingresoExitoso = false;

        JsonObject jsonObject = this.jsonParser.parse(json).getAsJsonObject();

        // Obtiene archivo de disco y lo convierte a objeto jdom
        SAXBuilder builder = new SAXBuilder();
        Document doc = null;

        try {
            doc = builder.build(new File(this.pathConductores + jsonObject.getAsJsonPrimitive("carnet").getAsString()
                    + ".xml"));
        }
        catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Comprueba si informacion de usuario ya registrado es corresponde a informacion de inicio de sesion.
        if (doc.getRootElement().getChildText("carnet").equals(jsonObject.getAsJsonPrimitive("carnet").getAsString())
                && doc.getRootElement().getChildText("contrasena").equals(jsonObject.getAsJsonPrimitive("contrasena").getAsString())) {
            ingresoExitoso = true;
        }

        return ingresoExitoso;
    }

    /**
     * Agrega un amigo a un perfil de conductor.
     * El amigo también registrara que fue anadido.
     *
     * @param json, canet de conductor y amigo a registrar
     * @throws JDOMException
     * @throws IOException
     */
    public boolean anadirAmigo(String json) throws JDOMException, IOException {
        boolean anadido = false;

        JsonObject jsonObject = this.jsonParser.parse(json).getAsJsonObject();

        String conductorPath = jsonObject.getAsJsonPrimitive("miCarnet").getAsString() + ".xml";
        String estudiantePath = jsonObject.getAsJsonPrimitive("carnetAmigo").getAsString() + ".xml";

        // Crea jsons para comprobar que los usuarios esten registrados en el sistema
        String miCarnet = jsonObject.getAsJsonPrimitive("miCarnet").getAsString();
        miCarnet = "{\"carnet\":"+miCarnet+"}";
        String carnetAmigo = jsonObject.getAsJsonPrimitive("carnetAmigo").getAsString();
        carnetAmigo = "{\"carnet\":"+carnetAmigo+"}";

        if (this.EstudianteExiste(carnetAmigo) && this.ConductorExiste(miCarnet)) {
            // Anade amigo en perfil de conductor
            Document docConductor = Conversion.getXMLFromDisk(this.pathConductores +conductorPath);
            Element amigoElementConductor = new Element("amigo");
            amigoElementConductor.addContent(jsonObject.getAsJsonPrimitive("carnetAmigo").getAsString());
            docConductor.getRootElement().getChild("amigos").setContent(amigoElementConductor);

            Conversion.saveXMLToDisk(docConductor, this.pathConductores + conductorPath);

            // Anade amigo en perfil de estudiante
            Document docEstudiante = Conversion.getXMLFromDisk(this.pathEstudiantes + estudiantePath);
            Element amigoElementEstudiante = new Element("amigo");
            amigoElementEstudiante.addContent(jsonObject.getAsJsonPrimitive("miCarnet").getAsString());
            docEstudiante.getRootElement().getChild("amigos").setContent(amigoElementEstudiante);

            Conversion.saveXMLToDisk(docEstudiante, this.pathEstudiantes + estudiantePath);

            anadido = true;
        }

        return anadido;

    }

    public static void main(String[] args) throws IOException, JDOMException {

        UserDB userDB = new UserDB();
        Gson gson = new Gson();

        Estudiante estudiante = new Estudiante("carlos", "1234", "20164321");
        userDB.RegistrarEstudiante(gson.toJson(estudiante));
        Conductor conductor = new Conductor("panchita", "something","20117294830");
        userDB.RegistrarConductor(gson.toJson(conductor));

        System.out.println(userDB.anadirAmigo("{\"miCarnet\": 20117294830, \"carnetAmigo\": 20164321}"));

    }

}
