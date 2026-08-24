package org.data;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class XMLRepository {

    private String path;
    private static XMLRepository theInstance;

    public static XMLRepository instance() {
        if (theInstance == null) theInstance = new XMLRepository("data.xml");
        return theInstance;
    }

    private XMLRepository(String p) {
        path = p;
    }

    public Data load() throws Exception {
        File file = new File(path);
        if (!file.exists()) {
            return new Data(); // si no existe el archivo, devuelve un Data vacío
        }
        JAXBContext jaxbContext = JAXBContext.newInstance(Data.class);
        FileInputStream is = new FileInputStream(path);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Data result = (Data) unmarshaller.unmarshal(is);
        is.close();
        return result;
    }

    public void store(Data d) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(Data.class);
        FileOutputStream os = new FileOutputStream(path);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(d, os);
        os.flush();
        os.close();
    }
}