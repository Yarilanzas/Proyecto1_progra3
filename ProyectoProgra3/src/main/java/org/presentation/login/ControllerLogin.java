package org.presentation.login;

import org.domain.User;
import org.logic.loginLogic.LoginService;
import org.presentation.category.*;
import org.presentation.changePassword.ChangePasswordController;
import org.presentation.changePassword.ChangePasswordModel;
import org.presentation.changePassword.ChangePasswordView;
import org.presentation.employees.*;
import org.presentation.reservations.*;
import org.presentation.scheduling.*;
import org.presentation.activities.*;
import org.presentation.statistics.*;
import org.presentation.resource.*;

import javax.swing.*;
import java.awt.*;

public class ControllerLogin {
    private Login view;
    private ModelLogin model;

    public ControllerLogin(Login view, ModelLogin model) {
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }

    public void login(String id, String password) {
        try {
            User user = LoginService.instance().read(id, password);

            model.setCurrent(user);

            String idUpper = user.getId().toUpperCase();
            if (idUpper.startsWith("ADM")) {
                buildAdminWindow();
            } else if (idUpper.startsWith("FUN")) {
                buildEmployeeWindow();
            }

            view.dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, e.getMessage(), "Error de Autenticación", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void changePassword(String id) {
        JDialog frameChangePassword = new JDialog((Frame) null, "Cambiar Contraseña", true);

        ChangePasswordModel model = new ChangePasswordModel();
        ChangePasswordView view = new ChangePasswordView();
        ChangePasswordController controller = new ChangePasswordController(view, model, id);

        frameChangePassword.setContentPane(view.getPanel());

        frameChangePassword.setSize(400, 300);
        frameChangePassword.setLocationRelativeTo(null);
        frameChangePassword.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        frameChangePassword.setVisible(true);
    }



    private void buildAdminWindow() {
        JFrame adminFrame = new JFrame("Panel de Administración");
        JTabbedPane tabbedPane = new JTabbedPane();

        //funcionarios
        EmployeeModel empModel = new EmployeeModel();
        EmployeeView empView = new EmployeeView();
        EmployeeController empController = new EmployeeController(empModel,empView);
        tabbedPane.addTab("Funcionarios", empView.getPanel());

        // Categorías
        CategoryModel catModel = new CategoryModel();
        CategoryView catView = new CategoryView();
        CategoryController catController = new CategoryController(catView, catModel);
        tabbedPane.addTab("Categorías", catView.getPanel());

        // Recursos
        ResourceModel resModel = new ResourceModel();
        ResourceView resView = new ResourceView();
        ResourceController resController = new ResourceController(resView, resModel);
        tabbedPane.addTab("Recursos", resView.getPanel());

        // Calendarización
        SchedulingModel schedModel = new SchedulingModel();
        SchedulingView schedView = new SchedulingView();
        SchedulingController schedController = new SchedulingController(schedView, schedModel);
        tabbedPane.addTab("Calendarización", schedView.getPanel());

        // Actividades
        ActivityModel actModel = new ActivityModel();
        ActivityView actView = new ActivityView();
        ActivityController actController = new ActivityController(actView, actModel);
        tabbedPane.addTab("Actividades", actView.getPanel());

        // Estadísticas
        StatisticsModel statModel = new StatisticsModel();
        StatisticsView statView = new StatisticsView();
        StatisticsController statController = new StatisticsController(statView, statModel);
        tabbedPane.addTab("Estadísticas", statView.getPanel());

        // vista
        adminFrame.add(tabbedPane);
        adminFrame.setSize(900, 700);
        adminFrame.setLocationRelativeTo(null);
        adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminFrame.setVisible(true);
    }

    private void buildEmployeeWindow() {
        JFrame employeeFrame = new JFrame("Panel de Funcionario");
        JTabbedPane tabbedPane = new JTabbedPane();

        // Reservas
        ReservationModel resModel = new ReservationModel();
        ReservationView resView = new ReservationView();
        ReservationController resController = new ReservationController(resView, resModel);
        tabbedPane.addTab("Reservas", resView.getPanel());

        // Calendarización
        SchedulingModel schedModel = new SchedulingModel();
        SchedulingView schedView = new SchedulingView();
        SchedulingController schedController = new SchedulingController(schedView, schedModel);
        tabbedPane.addTab("Calendarización", schedView.getPanel());

        //Actividades
        ActivityModel actModel = new ActivityModel();
        ActivityView actView = new ActivityView();
        ActivityController actController = new ActivityController(actView, actModel);
        tabbedPane.addTab("Actividades", actView.getPanel());

        // Estadísticas
        StatisticsModel statModel = new StatisticsModel();
        StatisticsView statView = new StatisticsView();
        StatisticsController statController = new StatisticsController(statView, statModel);
        tabbedPane.addTab("Estadísticas", statView.getPanel());

        // vista
        employeeFrame.add(tabbedPane);
        employeeFrame.setSize(900, 700);
        employeeFrame.setLocationRelativeTo(null);
        employeeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        employeeFrame.setVisible(true);
    }
}