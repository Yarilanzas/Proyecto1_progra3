package org.presentation.login;

import org.presentation.category.*;
import org.presentation.employees.*;
import org.presentation.reservations.*;
import org.presentation.scheduling.*;
import org.presentation.activities.*;
import org.presentation.statistics.*;
import org.presentation.resource.*;

import javax.swing.*;

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
            if (id.toUpperCase().startsWith("ADM")) {
                buildAdminWindow();
            } else if (id.toUpperCase().startsWith("FUN")) {
                buildEmployeeWindow();
            } else {
                JOptionPane.showMessageDialog(view, "Usuario no reconocido", "Error de Autenticación", JOptionPane.ERROR_MESSAGE);
                return;
            }

            view.dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buildAdminWindow() {
        JFrame adminFrame = new JFrame("Panel de Administración");
        JTabbedPane tabbedPane = new JTabbedPane();

        // 1. Módulo Funcionarios
        EmployeeModel empModel = new EmployeeModel();
        EmployeeView empView = new EmployeeView();
        EmployeeController empController = new EmployeeController(empModel);
        tabbedPane.addTab("Funcionarios", empView.getPanel());

        // 2. Módulo Categorías
        CategoryModel catModel = new CategoryModel();
        CategoryView catView = new CategoryView();
        CategoryController catController = new CategoryController(catView, catModel);
        tabbedPane.addTab("Categorías", catView.getPanel());

        // 3. Módulo Recursos
        ResourceModel resModel = new ResourceModel();
        ResourceView resView = new ResourceView();
        ResourceController resController = new ResourceController(resView, resModel);
        tabbedPane.addTab("Recursos", resView.getPanel());

        // 4. Módulo Calendarización (Compartido)
        SchedulingModel schedModel = new SchedulingModel();
        SchedulingView schedView = new SchedulingView();
        SchedulingController schedController = new SchedulingController(schedView, schedModel);
        tabbedPane.addTab("Calendarización", schedView.getPanel());

        // 5. Módulo Actividades (Compartido)
        ActivityModel actModel = new ActivityModel();
        ActivityView actView = new ActivityView();
        ActivityController actController = new ActivityController(actView, actModel);
        tabbedPane.addTab("Actividades", actView.getPanel());

        // 6. Módulo Estadísticas (Compartido)
        StatisticsModel statModel = new StatisticsModel();
        StatisticsView statView = new StatisticsView();
        StatisticsController statController = new StatisticsController(statView, statModel);
        tabbedPane.addTab("Estadísticas", statView.getPanel());

        // Configuración del JFrame
        adminFrame.add(tabbedPane);
        adminFrame.setSize(900, 700);
        adminFrame.setLocationRelativeTo(null);
        adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminFrame.setVisible(true);
    }

    private void buildEmployeeWindow() {
        JFrame employeeFrame = new JFrame("Panel de Funcionario");
        JTabbedPane tabbedPane = new JTabbedPane();

        // 1. Módulo Reservas (Exclusivo de Funcionario)
        ReservationModel resModel = new ReservationModel();
        ReservationView resView = new ReservationView();
        ReservationController resController = new ReservationController(resView, resModel);
        tabbedPane.addTab("Reservas", resView.getPanel());

        // 2. Módulo Calendarización (Compartido)
        SchedulingModel schedModel = new SchedulingModel();
        SchedulingView schedView = new SchedulingView();
        SchedulingController schedController = new SchedulingController(schedView, schedModel);
        tabbedPane.addTab("Calendarización", schedView.getPanel());

        // 3. Módulo Actividades (Compartido)
        ActivityModel actModel = new ActivityModel();
        ActivityView actView = new ActivityView();
        ActivityController actController = new ActivityController(actView, actModel);
        tabbedPane.addTab("Actividades", actView.getPanel());

        // 4. Módulo Estadísticas (Compartido)
        StatisticsModel statModel = new StatisticsModel();
        StatisticsView statView = new StatisticsView();
        StatisticsController statController = new StatisticsController(statView, statModel);
        tabbedPane.addTab("Estadísticas", statView.getPanel());

        // Configuración del JFrame
        employeeFrame.add(tabbedPane);
        employeeFrame.setSize(900, 700);
        employeeFrame.setLocationRelativeTo(null);
        employeeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        employeeFrame.setVisible(true);
    }
}