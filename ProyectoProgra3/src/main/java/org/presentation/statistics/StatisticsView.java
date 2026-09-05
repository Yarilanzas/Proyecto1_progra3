package org.presentation.statistics;

import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;

public class StatisticsView {
    private JButton cargarButtonA;
    private JTable tableActividades;
    private JPanel panel;
    private JPanel principalPanel;
    private DatePicker desdeRecursos;
    private DatePicker hastaRecursos;
    private JButton cargarButton;
    private DatePicker desdeActividades;
    private DatePicker hastaActividades;
    private JTable tableCategorias;
    private JPanel graficoRecursos;
    private JPanel graficoActividades;

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
