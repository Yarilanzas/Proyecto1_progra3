package org.domain;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Employee extends User {

    private String name;
    private String phone;

    public Employee() {
        super();
    }

    public Employee(String id, String password, String name, String phone) {
        super(id, password);
        this.name = name;
        this.phone = phone;
    }

    public String getName() { return name;}
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone;}
    public void setPhone(String phone) { this.phone = phone; }
}
