package org.presentation.resource;

import org.domain.Category;
import org.presentation.category.CategoryModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ResourceView implements PropertyChangeListener {
    private JButton imprimirButton2;
    private JButton buscarButton2;
    private JComboBox comboBoxCategorias;
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
        comboBoxCategorias.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        buscarButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String descripcion = (textField4.getText().trim()).toUpperCase();

                if (descripcion.isEmpty()){
                    JOptionPane.showMessageDialog(principalPanel,"Debe una descripcion");
                    return;
                }else {
                    controller.searchbyDescription(descripcion);
                }


                if (model.getCategories().isEmpty()){
                    JOptionPane.showMessageDialog(principalPanel,"No se encontro ninguna categoria con esa descripcion");
                }

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
        this.model.addPropertyChangeListener(this);
    }

    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case ResourceModel.CATEGORIES:
                DefaultComboBoxModel<Category> modelo = new DefaultComboBoxModel<>();
                for (Category c : model.getCategories()){
                    modelo.addElement(c);
                }
                comboBoxCategorias.setModel(modelo);
                break;
           /* case ResourceModel.CURRENT:
                Category curr = model.getCurrent();
                textField4.setText(curr.getId() == null ? "" : curr.getId());
                descripcionCategoriaFld.setText(curr.getDescription() == null ? "" : curr.getDescription());
                break;*/
        }
        this.principalPanel.revalidate();
    }
}

