package org.presentation.resource;

import org.presentation.category.CategoryController;
import org.presentation.category.CategoryModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResourceView {
    private JButton imprimirButton2;
    private JButton buscarButton2;
    private JComboBox comboBox1;
    private JTextField textField4;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton guardarButton2;
    private JButton borrarButton2;
    private JButton limpiarButton2;
    private JTable table4;
    private JPanel panel;
    private JPanel principalPanel;

    private ResourceModel model;
    private ResourceController controller;


    public ResourceView() {
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public JPanel getPanel() {
        return principalPanel;
    }

    public void setPanel(JPanel panel) {
        this.principalPanel = panel;
    }

    public ResourceController getController() {
        return controller;
    }

    public void setController(ResourceController controller) {
        this.controller = controller;
    }

    public ResourceModel getModel() {
        return model;
    }

    public void setModel(ResourceModel model) {
        this.model = model;
    }

}

