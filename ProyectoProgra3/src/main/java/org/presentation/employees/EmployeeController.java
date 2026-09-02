package org.presentation.employees;

import org.domain.Employee;
import org.logic.EmployeeService;

import java.util.List;

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

    public void search(String txt, boolean porid) {
        try {
            if (porid){
                Employee e = service.findById(txt);
                model.setList(e != null ? List.of(e) : List.of());
            } else {
                model.setList(txt.isEmpty() ? service.findAll() : service.findByName(txt));
            }
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