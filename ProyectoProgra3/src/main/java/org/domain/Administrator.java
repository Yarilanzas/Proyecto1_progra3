package org.domain;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Administrator extends User {

    public Administrator() {
        super();
    }

    public Administrator(String id, String password) {
        super(id, password);
    }


}