package org.data;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class XMLRepository {

    private static XMLRepository instance;

    private XMLRepository() {

    }

    public static XMLRepository getInstance() {
        if (instance == null) {
            instance = new XMLRepository();
        }
        return instance;
    }


    public Document load(String fileName) {
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                return createEmptyDocument(fileName);
            }
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            return builder.parse(file);
        } catch (Exception e) {
            throw new RuntimeException("Error leyendo " + fileName, e);
        }
    }


    public void save(String fileName, Document document) {
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty("indent", "yes");
            transformer.transform(new DOMSource(document), new StreamResult(new File(fileName)));
        } catch (Exception e) {
            throw new RuntimeException("Error guardando " + fileName, e);
        }
    }


    private Document createEmptyDocument (String fileName) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            String raiz = fileName.replace(".xml", "");
            Element root = doc.createElement(raiz);
            doc.appendChild(root);
            return doc;
        } catch (Exception e) {
            throw new RuntimeException("Error creando documento nuevo", e);
        }
    }
}