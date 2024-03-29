package databases;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entities.Conductor;
import entities.Estudiante;
import entities.Posicion;
import manager.Conversion;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class UserDB {

    private JsonParser jsonParser = new JsonParser();

    private final String pathConductores = "/home/david/Documents/CarpoolingServer/ConductoresRegistrados/";
    private final String pathEstudiantes = "/home/david/Documents/CarpoolingServer/EstudiantesRegistrados/";

    /**
     * Registra conductor guardando su información en disco por medio de xml.
     *
     * @param json
     * @throws IOException
     */
    public boolean RegistrarConductor(String json) throws IOException {
       boolean registrado = false;

        Element conductor = new Element("conductor");
        Document doc = new Document();
        doc.setRootElement(conductor);

        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();

        if (!this.ConductorExiste(json)) {

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
            Element numCalificaciones = new Element("numCalificaciones");
            numCalificaciones.addContent("0");

            Element posicionHogarElement = new Element("posicionHogar");
            JsonObject posicionHogar = jsonObject.getAsJsonObject("posicionHogar");
            double longitud = posicionHogar.getAsJsonPrimitive("lon").getAsDouble();
            double latitud = posicionHogar.getAsJsonPrimitive("lat").getAsDouble();
            Element longitudElement = new Element("lon");
            longitudElement.addContent(""+longitud);
            Element latitudElement = new Element("lat");
            latitudElement.addContent(""+latitud);
            posicionHogarElement.addContent(longitudElement);
            posicionHogarElement.addContent(latitudElement);

            conductor.addContent(nombreElment);
            conductor.addContent(contrasenaElement);
            conductor.addContent(carnetElement);
            conductor.addContent(amigosElement);
            conductor.addContent(promedioElement);
            conductor.addContent(numCalificaciones);
            conductor.addContent(posicionHogarElement);

            // TODO hacer que no depende de un pathConductores especifico.
            Conversion.saveXMLToDisk(doc, this.pathConductores +carnet+".xml");

            registrado = true;
        }

        return registrado;
    }


    /**
     * Registra estudiante guardando su información en disco por medio de xml.
     *
     * @param json
     * @throws IOException
     */
    public boolean RegistrarEstudiante(String json) throws IOException {
        boolean registrado = false;

        Element estudiante = new Element("estudiante");
        Document doc = new Document();
        doc.setRootElement(estudiante);

        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();

        if (!this.EstudianteExiste(json)) {

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
            Element numCalificaciones = new Element("numCalificaciones");
            numCalificaciones.addContent("0");

            Element posicionHogarElement = new Element("posicionHogar");
            JsonObject posicionHogar = jsonObject.getAsJsonObject("posicionHogar");
            double longitud = posicionHogar.getAsJsonPrimitive("lon").getAsDouble();
            double latitud = posicionHogar.getAsJsonPrimitive("lat").getAsDouble();
            Element longitudElement = new Element("lon");
            longitudElement.addContent(""+longitud);
            Element latitudElement = new Element("lat");
            latitudElement.addContent(""+latitud);
            posicionHogarElement.addContent(longitudElement);
            posicionHogarElement.addContent(latitudElement);

            estudiante.addContent(nombreElment);
            estudiante.addContent(contrasenaElement);
            estudiante.addContent(carnetElement);
            estudiante.addContent(amigosElement);
            estudiante.addContent(viajesRealizadosElement);
            estudiante.addContent(promedioElement);
            estudiante.addContent(numCalificaciones);
            estudiante.addContent(posicionHogarElement);

            // TODO hacer que no dependa de un pathConductores especifico.
            Conversion.saveXMLToDisk(doc, this.pathEstudiantes +carnet+".xml");

            registrado = true;
        }

        return registrado;
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
     * @return exito de ingresar conductor
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
     * Comprueba la contrasena de un estudiante determinado.
     *
     * @param json
     * @return exito de ingresar estudiante
     */
    public boolean IngresarEstudiante(String json) {
        boolean ingresoExitoso = false;

        JsonObject jsonObject = this.jsonParser.parse(json).getAsJsonObject();

        // Obtiene archivo de disco y lo convierte a objeto jdom
        SAXBuilder builder = new SAXBuilder();
        Document doc = null;

        try {
            doc = builder.build(new File(this.pathEstudiantes + jsonObject.getAsJsonPrimitive("carnet").getAsString()
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
            docConductor.getRootElement().getChild("amigos").addContent(amigoElementConductor);

            Conversion.saveXMLToDisk(docConductor, this.pathConductores + conductorPath);

            // Anade amigo en perfil de estudiante
            Document docEstudiante = Conversion.getXMLFromDisk(this.pathEstudiantes + estudiantePath);
            Element amigoElementEstudiante = new Element("amigo");
            amigoElementEstudiante.addContent(jsonObject.getAsJsonPrimitive("miCarnet").getAsString());
            docEstudiante.getRootElement().getChild("amigos").addContent(amigoElementEstudiante);

            Conversion.saveXMLToDisk(docEstudiante, this.pathEstudiantes + estudiantePath);

            anadido = true;
        }
        return anadido;
    }

    /**
     * Ingresa calificacion de conductor, cambia su promedio y numero de calificaiones.
     *
     * @param json
     * @throws JDOMException
     * @throws IOException
     */
    public void calificarConductor(String json) throws JDOMException, IOException {
        JsonObject jsonObject = this.jsonParser.parse(json).getAsJsonObject();

        String carnet = jsonObject.getAsJsonPrimitive("carnet").getAsString();

        Document doc = Conversion.getXMLFromDisk(this.pathConductores+carnet+".xml");

        String promedio = doc.getRootElement().getChildText("promedio");
        String numCalificaciones = doc.getRootElement().getChildText("numCalificaciones");

        int calificacion = jsonObject.getAsJsonPrimitive("calificacion").getAsInt();
        double promedioDouble = Double.parseDouble(promedio);
        int numCalificacionesInt = Integer.parseInt(numCalificaciones);

        double nuevoPromedio = ((promedioDouble*numCalificacionesInt) + calificacion) / (numCalificacionesInt+1);
        numCalificacionesInt++;

        doc.getRootElement().getChild("promedio").removeContent();
        doc.getRootElement().getChild("promedio").addContent(String.valueOf(nuevoPromedio));
        doc.getRootElement().getChild("numCalificaciones").removeContent();
        doc.getRootElement().getChild("numCalificaciones").addContent(String.valueOf(numCalificacionesInt));

        Conversion.saveXMLToDisk(doc, this.pathConductores+carnet+".xml");
    }

    /**
     * Ingresa calificacion de un estudiante, cambia su ponderado y numero de calificaciones.
     *
     * @param json
     * @throws JDOMException
     * @throws IOException
     */
    public void calificarEstudiante(String json) throws JDOMException, IOException {

        JsonObject jsonObject = this.jsonParser.parse(json).getAsJsonObject();

        String carnet = jsonObject.getAsJsonPrimitive("carnet").getAsString();

        Document doc = Conversion.getXMLFromDisk(this.pathEstudiantes+carnet+".xml");

        String promedio = doc.getRootElement().getChildText("promedio");
        String numCalificaciones = doc.getRootElement().getChildText("numCalificaciones");

        int calificacion = jsonObject.getAsJsonPrimitive("calificacion").getAsInt();
        double promedioDouble = Double.parseDouble(promedio);
        int numCalificacionesInt = Integer.parseInt(numCalificaciones);

        double nuevoPromedio = ((promedioDouble*numCalificacionesInt) + calificacion) / (numCalificacionesInt+1);
        numCalificacionesInt++;

        doc.getRootElement().getChild("promedio").removeContent();
        doc.getRootElement().getChild("promedio").addContent(String.valueOf(nuevoPromedio));
        doc.getRootElement().getChild("numCalificaciones").removeContent();
        doc.getRootElement().getChild("numCalificaciones").addContent(String.valueOf(numCalificacionesInt));

        Conversion.saveXMLToDisk(doc, this.pathEstudiantes+carnet+".xml");

    }

    /**
     * Ingresa en el sistema la disponibilidad de un estudiante para que lo recoja cualquier conductor
     *
     * @param json
     * @throws JDOMException
     * @throws IOException
     */
    public void habilitarCarpoolingNormal(String json) throws JDOMException, IOException {
        if (this.EstudianteExiste(json)) {
            JsonObject jsonObject = this.jsonParser.parse(json).getAsJsonObject();
            Document doc = Conversion.getXMLFromDisk(
                    this.pathEstudiantes + jsonObject.getAsJsonPrimitive("carnet") + ".xml");

            doc.getRootElement().getChild("carpoolingNormal").setText("true");

            Conversion.saveXMLToDisk(doc,
                    this.pathEstudiantes+jsonObject.getAsJsonPrimitive("carnet")+".xml");
        }
    }

    /**
     * Ingresa en el sistema un mensaje a un conductor especifica para que recoje al etudiante
     *
     * @param json
     * @throws JDOMException
     * @throws IOException
     */
    public void habilitarCarpoolingAmigos(String json) throws JDOMException, IOException {
        if (this.EstudianteExiste(json)) {
            JsonObject jsonObject = this.jsonParser.parse(json).getAsJsonObject();
            Document doc = Conversion.getXMLFromDisk(
                    this.pathEstudiantes + jsonObject.getAsJsonPrimitive("carnet") + ".xml");

            doc.getRootElement().getChild("carpoolingAmigos").setText("true");

            Conversion.saveXMLToDisk(doc,
                    this.pathEstudiantes+jsonObject.getAsJsonPrimitive("carnet")+".xml");
        }
    }

    /**
     * Envia toda la informacion en disco de un estudiante particular.
     *
     * @param json
     * @return
     * @throws JDOMException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public String enviarStudent(String json) throws JDOMException, IOException, ParserConfigurationException {
        String student = "";
        if (this.EstudianteExiste(json)) {
            JsonObject jsonObject = this.jsonParser.parse(json).getAsJsonObject();
            Document doc = Conversion.getXMLFromDisk(
                    this.pathEstudiantes+jsonObject.getAsJsonPrimitive("carnet")+".xml");

            String nombre = doc.getRootElement().getChildText("nombre");
            String contrasena = doc.getRootElement().getChildText("contrasena");
            String carnet = doc.getRootElement().getChildText("carnet");
            int viajesRealizados = Integer.valueOf(doc.getRootElement().getChildText("viajesRealizados"));
            int numCalificaciones = Integer.valueOf(doc.getRootElement().getChildText("numCalificaciones"));
            double promedio = Double.valueOf(doc.getRootElement().getChildText("promedio"));
            double lat = Double.valueOf(doc.getRootElement().getChild("posicionHogar").getChildText("lat"));
            double lon = Double.valueOf(doc.getRootElement().getChild("posicionHogar").getChildText("lon"));
            Posicion posicion = new Posicion(lat, lon);

            LinkedList<String> amigos = new LinkedList<>();

            Gson gson = new Gson();

            List<Element> elements = doc.getRootElement().getChild("amigos").getChildren();
            for (Element element: elements) {
                amigos.add(element.getText());
            }

            Estudiante estudiante = new Estudiante(nombre, contrasena, carnet, numCalificaciones, viajesRealizados,
                    amigos, promedio, posicion);
            student = gson.toJson(estudiante);
        }
        return student;
    }

    /**
     * Envia toda la informacion en disco de un conductor particular.
     *
     * @param json
     * @return
     * @throws JDOMException
     * @throws IOException
     */
    public String enviarDriver(String json) throws JDOMException, IOException {
        String driver = "";
        if (this.ConductorExiste(json)) {
            JsonObject jsonObject = this.jsonParser.parse(json).getAsJsonObject();
            Document doc = Conversion.getXMLFromDisk(
                    this.pathConductores+jsonObject.getAsJsonPrimitive("carnet")+".xml");

            String nombre = doc.getRootElement().getChildText("nombre");
            String contrasena = doc.getRootElement().getChildText("contrasena");
            String carnet = doc.getRootElement().getChildText("carnet");
            int numCalificaciones = Integer.valueOf(doc.getRootElement().getChildText("numCalificaciones"));
            double promedio = Double.valueOf(doc.getRootElement().getChildText("promedio"));
            double lat = Double.valueOf(doc.getRootElement().getChild("posicionHogar").getChildText("lat"));
            double lon = Double.valueOf(doc.getRootElement().getChild("posicionHogar").getChildText("lon"));
            Posicion posicion = new Posicion(lat, lon);

            LinkedList<String> amigos = new LinkedList<>();

            Gson gson = new Gson();

            List<Element> elements = doc.getRootElement().getChild("amigos").getChildren();
            System.out.println(elements.size());
            for (Element element: elements) {
                amigos.add(element.getText());
            }

            Conductor conductor = new Conductor(nombre, contrasena, numCalificaciones, carnet, amigos,
                    posicion, promedio);
            driver = gson.toJson(conductor);
        }
        return driver;
    }

    public static void main(String[] args) throws IOException, JDOMException, ParserConfigurationException {

        UserDB userDB = new UserDB();
        Gson gson = new Gson();

        JsonParser jsonParser = new JsonParser();
        String json = userDB.enviarStudent("{\"carnet\":20164321}");
        System.out.println(json);

        Conductor conductor = new Conductor("David", "contrasena", "20171234", new Posicion(2.45,7.74));
        String jsonconductor = gson.toJson(conductor);
        JsonObject jsonObject = jsonParser.parse(jsonconductor).getAsJsonObject();
        jsonObject.remove("amigos");
        System.out.println(gson.toJson(jsonObject));

        Estudiante estudiante = new Estudiante("carlos", "1234", "20164321",
                new Posicion(3, 3));
        userDB.RegistrarEstudiante(gson.toJson(estudiante));

        userDB.anadirAmigo("{\"carnetAmigo\": 20164321, \"miCarnet\":1776}");
        userDB.anadirAmigo("{\"carnetAmigo\": 20164321, \"miCarnet\":20171234}");

        System.out.println(userDB.enviarStudent("{\"carnet\": 20164321}"));

        /*
        Conductor conductor = new Conductor("panchita", "something","20117294830");
        userDB.RegistrarConductor(gson.toJson(conductor));

        userDB.anadirAmigo("{\"miCarnet\": 20117294830, \"carnetAmigo\": 20164321}");

        userDB.calificarEstudiante("{\"carnet\": 20164321, \"calificacion\": 5}");
        userDB.calificarConductor("{\"carnet\": 20117294830, \"calificacion\": 5}");
        userDB.calificarConductor("{\"carnet\": 20117294830, \"calificacion\": 2}");
        */

    }

}
