package org.data;

import jakarta.xml.bind.annotation.*;
import org.domain.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "data")
@XmlAccessorType(XmlAccessType.FIELD)
public class Data {
    @XmlElementWrapper(name = "employees")
    @XmlElement(name = "employee")
    private List<Employee> employees = new ArrayList<>();

    @XmlElementWrapper(name = "administrators")
    @XmlElement(name = "administrator")
    private List<Administrator> administrators = new ArrayList<>();


    @XmlElementWrapper(name = "categories")
    @XmlElement(name = "category")
    private List<Category> categories = new ArrayList<>();

    @XmlElementWrapper(name = "resources")
    @XmlElement(name = "resource")
    private List<Resource> resources = new ArrayList<>();

    @XmlElementWrapper(name = "reservations")
    @XmlElement(name = "reservation")
    private List<Reservation> reservations = new ArrayList<>();

    public List<Employee> getEmployees() { return employees; }
    public List<Administrator> getAdministrators() { return administrators; }
    public List<Category> getCategories() { return categories; }
    public List<Resource> getResources() { return resources; }
    public List<Reservation> getReservations() { return reservations; }
}