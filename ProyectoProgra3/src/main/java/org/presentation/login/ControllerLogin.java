package org.presentation.login;
import org.domain.Employee;
import org.domain.User;
import org.logic.loginLogic.LoginService;
import org.presentation.administrator.AdministratorView;
import org.presentation.administrator.ControllerAdministrator;
import org.presentation.administrator.ModelAdministrator;
import org.presentation.employee.EmployeeController;
import org.presentation.employee.EmployeeModel;
import org.presentation.employee.EmployeeView;

import javax.swing.*;

public class ControllerLogin {
    Login view;
    ModelLogin model;

    public ControllerLogin(Login view, ModelLogin model) {
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }


    public void login(String id, String password) {
        try {
            if (id.toUpperCase().startsWith("ADM")) {

                AdministratorView adminWindow = new AdministratorView();
                JFrame adminFrame = new JFrame("Administradores");

                adminFrame.add(adminWindow.getPanel());

                adminFrame.setSize(800, 600);
                adminFrame.setLocationRelativeTo(null);

                adminFrame.setVisible(true);

                ModelAdministrator adminModel = new ModelAdministrator();
                ControllerAdministrator controllerAdm = new ControllerAdministrator(adminWindow, adminModel);

            } else if (id.toUpperCase().startsWith("FUN")) {
                EmployeeView employeeWindow = new EmployeeView();
                JFrame employeeFrame = new JFrame("Funcionarios");

                employeeFrame.add(employeeWindow.getPanel());

                employeeFrame.setSize(800, 600);
                employeeFrame.setLocationRelativeTo(null);

                employeeFrame.setVisible(true);


                EmployeeModel EmpModel = new EmployeeModel();
                EmployeeController controllerEmp= new EmployeeController(EmpModel);

            }

            view.dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
