package org.presentation.employee;

import org.domain.Employee;
import org.logic.EmployeeService;

public class EmployeeController {
    private final EmployeeService service = new EmployeeService();
    private final EmployeeModel model;

    public EmployeeController(EmployeeModel model) {
        this.model = model;
        list();
    }

    public void list() {
        try {
            model.setList(service.findAll());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void create(Employee e) throws Exception {
        service.save(e);
        list();
        model.setCurrent(new Employee());
    }

    public void edit(int row) {
        model.setCurrent(model.getList().get(row));
    }

    public void search(String nombre) {
        try {
            model.setList(nombre.isEmpty() ? service.findAll() : service.findByName(nombre));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void delete(String id) throws Exception {
        service.delete(id);
        list();
        model.setCurrent(new Employee());
    }

    public void clear() {
        model.setCurrent(new Employee());
    }
}