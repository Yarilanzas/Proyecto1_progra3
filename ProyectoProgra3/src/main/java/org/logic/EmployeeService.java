package org.logic;

import org.data.Data;
import org.data.XMLRepository;
import org.domain.Employee;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeService {
    public List<Employee> findAll() throws Exception{
        Data data = XMLRepository.instance().load();
        return data.getEmployees();
    }

    public Employee findById(String id) throws Exception{
        Data data = XMLRepository.instance().load();
        for (Employee e : data.getEmployees()){
            if (e.getId().equals(id)){
                return e;
            }
        }
        return null;
    }

    public List<Employee> findByName(String name) throws Exception{
        Data data = XMLRepository.instance().load();
        return data.getEmployees().stream().
                filter(e-> e.getName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
    }

    public void save(Employee employee) throws Exception{
        Data data = XMLRepository.instance().load();
        Employee exist = findByIdIn(data.getEmployees(), employee.getId());
        if (exist != null){
            data.getEmployees().remove(exist);
        } else {
            String nuevo = generateId(data.getEmployees());
            employee.setId(nuevo);
            employee.setPassword(employee.getId());
        }
        data.getEmployees().add(employee);
        XMLRepository.instance().store(data);
    }

    public void delete(String id) throws Exception{
        Data data = XMLRepository.instance().load();
        Employee exist = findByIdIn(data.getEmployees(),id);
        if (exist != null){
            data.getEmployees().remove(exist);
            XMLRepository.instance().store(data);
        }
    }

    private Employee findByIdIn(List<Employee> list, String id){
        for (Employee e : list){
            if (e.getId().equals(id)){
                return e;
            }
        }
        return null;
    }

    private String generateId(List<Employee> emp){
        int max = 0;
        for (Employee e : emp){
            String num = e.getId().replace("FUN-","");
            try{
                int valor = Integer.parseInt(num);
                if (valor > max){
                    max = valor;
                }
            } catch (NumberFormatException ignored) {}
        }
        int numNuevo = max +1;
        String numeroText = String.valueOf(numNuevo);
        while (numeroText.length() <3){
            numeroText = "0" + numeroText;
        }
        return "FUN-" + numeroText;
    }
}