package org.presentation;

import org.domain.Employee;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class EmployeeModel {
    public static final String LIST = "list";
    public static final String CURRENT = "current";

    private List<Employee> list;
    private Employee current = new Employee();

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener l) {
        support.addPropertyChangeListener(l);
    }

    public List<Employee> getList() { return list; }
    public void setList(List<Employee> list) {
        this.list = list;
        support.firePropertyChange(LIST, null, list);
    }

    public Employee getCurrent() { return current; }
    public void setCurrent(Employee current) {
        this.current = current;
        support.firePropertyChange(CURRENT, null, current);
    }
}