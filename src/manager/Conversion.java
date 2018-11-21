package manager;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Clase encargada de guardar y recuperar XML en disco
 *
 * @author David Azofeifa H.
 */
public class Conversion {

    public static void saveXMLToDisk(Document doc, String path) throws IOException {
        XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
        xmlOutputter.output(doc, new FileOutputStream(new File(path)));
    }

    public static Document getXMLFromDisk(String path) throws JDOMException, IOException {
        SAXBuilder builder = new SAXBuilder();
        Document doc = null;
        
        try {
            doc = builder.build(new File(path));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        return doc;

    }

}
