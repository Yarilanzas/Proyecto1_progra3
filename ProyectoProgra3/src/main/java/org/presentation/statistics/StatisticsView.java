package org.presentation.statistics;

import org.presentation.resource.ResourceController;
import org.presentation.resource.ResourceModel;

import javax.swing.*;

public class StatisticsView {
    private JButton cargarButtonA;
    private JTable table2;
    private JPanel panel;
    private JPanel principalPanel;

    private StatisticsModel model;
    private StatisticsController controller;


    public JPanel getPanel() {
        return principalPanel;
    }

    public void setPanel(JPanel panel) {
        this.principalPanel = panel;
    }

    public StatisticsController getController() {
        return controller;
    }

    public void setController(StatisticsController controller) {
        this.controller = controller;
    }

    public StatisticsModel getModel() {
        return model;
    }

    public void setModel(StatisticsModel model) {
        this.model = model;
    }
}
