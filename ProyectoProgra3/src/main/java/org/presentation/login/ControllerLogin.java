package org.presentation.login;
import org.domain.User;
import org.logic.loginLogic.LoginService;
import org.presentation.administrator.AdministratorView;
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


    public void login(String id) {
        try {
            User usuarioLogueado = LoginService.instance().read(id); //aqui lo esta buscando en la base de datos
            if (id.toUpperCase().startsWith("ADM")) {

                AdministratorView adminWindow = new AdministratorView();
                JFrame adminFrame = new JFrame("Administradores");

                adminFrame.add(adminWindow.getPanel());

                adminFrame.setSize(600, 400);
                adminFrame.setLocationRelativeTo(null);

                adminFrame.setVisible(true);


            } else if (id.toUpperCase().startsWith("FUN")) {
                EmployeeView employeeWindow = new EmployeeView();
                JFrame employeeFrame = new JFrame("Funcionarios");

                employeeFrame.add(employeeWindow.getPanel());

                employeeFrame.setSize(600, 400);
                employeeFrame.setLocationRelativeTo(null);

                employeeFrame.setVisible(true);
            }

            view.dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
