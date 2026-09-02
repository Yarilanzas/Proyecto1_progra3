package org.presentation.activities;

import org.presentation.resource.ResourceController;
import org.presentation.resource.ResourceModel;

import javax.swing.*;

public class ActivityView {
    private ActivityModel model;
    private ActivityController controller;
    private JPanel panel;
    private JPanel principalPanel;


    public JPanel getPanel() {
        return principalPanel;
    }

    public void setPanel(JPanel panel) {
        this.principalPanel = panel;
    }

    public ActivityController getController() {
        return controller;
    }

    public void setController(ActivityController controller) {
        this.controller = controller;
    }

    public ActivityModel getModel() {
        return model;
    }

    public void setModel(ActivityModel model) {
        this.model = model;
    }
}
