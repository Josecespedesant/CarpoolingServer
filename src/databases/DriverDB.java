package databases;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entities.Conductor;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DriverDB {

    public void RegistrarConductor(String json) throws IOException {
        System.out.println("aqui");
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

        conductor.addContent(nombre);
        conductor.addContent(contrasena);
        conductor.addContent(carnet);

        XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
        xmlOutputter.output(doc, new FileOutputStream(new File("ConductoresRegistrados/"+carnet+".xml")));

    }


    public static void main(String[] args) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        Conductor conductor = new Conductor("david", "1234");
        String json = gson.toJson(conductor);
        System.out.println(json);


        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();
        String nombre = jsonObject.getAsJsonPrimitive("nombre").getAsString();
        System.out.println(nombre);
    }

}
