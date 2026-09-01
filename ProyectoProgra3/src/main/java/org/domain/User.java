package org.domain;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
public abstract class User {
    private String id;
    private String password;

    public User() {
    }

    public User(String id, String password) {
        this.id = id;
        this.password = id;
    }


    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

/* esto va en user service
    public boolean login(String id, String password) {
        return this.id.equals(id) && this.password.equals(password);
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
    }*/
}